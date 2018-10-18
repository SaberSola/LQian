package com.zl.lqian.service.impl;

import com.zl.lqian.service.HttpClientService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class HttpClientServiceImpl implements HttpClientService {

    private final Logger log = LoggerFactory.getLogger(HttpClientServiceImpl.class);

    private final CloseableHttpClient httpClient;

    public HttpClientServiceImpl(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public String post(String url, String param) {

            String resultStr = null;
            Integer statusCode = -1;
            HttpPost post;
            try {
                post = new HttpPost(url.trim());
            } catch (Exception e) {
                log.error("url error " + url, e.getMessage());
                throw new RuntimeException(e);
            }
            CloseableHttpResponse response = null;

            try {
                if (param != null)
                    post.setEntity(new StringEntity(param,"utf-8"));
                //post.setHeader("Accept", contextType);
                response = httpClient.execute(post);
                statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    log.error("connect " + url + " error httpCode : " + statusCode);
                    return null;
                }
                resultStr = EntityUtils.toString(response.getEntity());
                log.info("post response : " + resultStr);

            } catch (UnsupportedEncodingException e) {
                log.error("sendDataByPost UnsupportedEncodingException ", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                closeResponse(response);
            }

            return resultStr;
        }

    private void closeResponse(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            log.error("closeableHttpResponse close error", e);
        }
    }

}
