package com.xmg.springboot.p2p.mgrsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.xmg.springboot.p2p.CoreAppConfig;

/**
 * 后台应用总控制器
 * @author 1
 *
 */
@SpringBootApplication
@Import(CoreAppConfig.class)
@PropertySource("classpath:application-mgrsite.properties")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
