package com.zl.lqian.config;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.net.ssl.SSLException;

@Configuration
@EnableScheduling
public class HttpClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientConfig.class);

    // Determines the timeout in milliseconds until a connection is established.
    private static final int CONNECT_TIMEOUT = 30000;

    // The timeout when requesting a connection from the connection manager.
    private static final int REQUEST_TIMEOUT = 30000;

    // The timeout for waiting for data
    private static final int SOCKET_TIMEOUT = 60000;

    // The max connections
    private static final int MAX_TOTAL_CONNECTIONS = 50;

    //single route max count
    private static final int MAX_PER_ROUTE = 50;

    //connection keepalive millis
    private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 20 * 1000;

    //wait times  for close connection
    private static final int CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS = 30;

    //retry times
    private static final int RETRY_TIMES = 3;

    @Bean
    @Primary
    public PoolingHttpClientConnectionManager poolingConnectionManager(){

        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            LOGGER.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
        }
        SSLConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(builder.build());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            LOGGER.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create().register("https",sslsf)
                .register("http",new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager poolingConnectionManager =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        poolingConnectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        return poolingConnectionManager;
    }

    @Bean
    @Primary
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy(){
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator
                        (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();

                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return DEFAULT_KEEP_ALIVE_TIME_MILLIS;
            }
        };
    }
    @Bean
    @Primary
    public HttpRequestRetryHandler httpRequestRetryHandler(){
        //请求重试

        return new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
                // Do not retry if over max retry count,如果重试次数超过了retryTime,则不再重试请求
                if (executionCount >= RETRY_TIMES) {
                    return false;
                }
                // 服务端断掉客户端的连接异常
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                // time out 超时重试
                if (exception instanceof InterruptedIOException) {
                    return true;
                }
                // Unknown host
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                // Connection refused
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                // SSL handshake exception
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }

                return false;
            }
        };

    }

}
