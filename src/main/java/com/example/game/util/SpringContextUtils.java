package com.example.game.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
