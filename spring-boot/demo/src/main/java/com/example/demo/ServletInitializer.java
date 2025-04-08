package com.example.demo;

import java.io.File;
import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		String configPath = Environment.getProperties().getProperty("catalina.home")
				+ File.separator + "conf"
				+ File.separator + "demo" + File.separator +
				"demo-application.properties";
		//
		logger.info("\n\n\nConfigpath: " + configPath);
		logger.info("\n\n\nStarting to run Spring boot app...");
		Properties props = new Properties();
		props.setProperty("spring.config.location", configPath); // set the config
		// file to use
		application.application().setDefaultProperties(props);
		return application.sources(Demo2025Application.class);
	}

}
