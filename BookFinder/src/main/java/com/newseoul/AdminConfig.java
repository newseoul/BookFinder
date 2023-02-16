package com.newseoul;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/upload/**").addResourceLocations("C:\\Users\\A\\git\\BookFinder\\BookFinder\\src\\main\\resources\\public\\upload\\");
	}
	
}
