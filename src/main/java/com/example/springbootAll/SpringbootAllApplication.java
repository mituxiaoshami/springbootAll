package com.example.springbootAll;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//扫描mapper 还有一种方法 直接在Mapper加注解@Mapper 但是建议第一种
@MapperScan("com.example.springbootAll.mapper")
// 启动类启用定时
@EnableScheduling
public class SpringbootAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAllApplication.class, args);
	}

}
