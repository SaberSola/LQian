package com.zl.lqian.readwrite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(scanBasePackages = {"com.zl.lqian.*"},
	exclude = {DataSourceAutoConfiguration.class })
@MapperScan("com.zl.lqian.readwrite.mapper")
@ServletComponentScan
public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("#main() App启动开始");
		SpringApplication.run(App.class, args);
		logger.info("#main() App启动结束");
	}
}
