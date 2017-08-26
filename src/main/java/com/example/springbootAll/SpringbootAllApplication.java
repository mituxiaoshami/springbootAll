package com.example.springbootAll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



// 启动类启用定时
@EnableScheduling
// 扫描servlet
@ServletComponentScan
@SpringBootApplication
// 一开始如果不想要进行多余Bean的配置 就把下面的代码注释去掉 并且每个bean都要显示配置
/*@Configuration
@Import({
		DispatcherServletAutoConfiguration.class,
		EmbeddedServletContainerAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class,
		HttpEncodingAutoConfiguration.class,
		HttpMessageConvertersAutoConfiguration.class,
		JacksonAutoConfiguration.class,
		MultipartAutoConfiguration.class,
		ServerPropertiesAutoConfiguration.class,
		PropertyPlaceholderAutoConfiguration.class,
		ThymeleafAutoConfiguration.class,
		WebMvcAutoConfiguration.class,
})*/
public class SpringbootAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAllApplication.class, args);
	}


}
