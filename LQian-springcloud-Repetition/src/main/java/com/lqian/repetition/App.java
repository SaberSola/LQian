package com.lqian.repetition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(scanBasePackages = {"com.lqian.repetition.*"},
	exclude = {DataSourceAutoConfiguration.class })
@MapperScan("com.lqian.repetition.mapper")
@ComponentScan(basePackages = "com.lqian.*")
@ServletComponentScan
public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("#main() App启动开始");
		SpringApplication.run(App.class, args);
		logger.info("#main() App启动结束");
	}
}
