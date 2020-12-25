package com.spring.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.spring.basic.Application;
import com.spring.basic.appconfig.AppConfig;

// sadece appconfig classları tanımasını sağlıyor
 @SpringBootApplication(scanBasePackages = "com.spring.basic.appconfig")
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
 
 // tüm package ları dolaşır
//@SpringBootApplication
@ImportResource("applicationContext.xml") //xml tabanlı
@Import(AppConfig.class) // class tabanlı (Spring Configuration class)

  //class Tabanlı Configurasyon
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

}
