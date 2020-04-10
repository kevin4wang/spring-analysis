package com.linlihudong.three;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//初始化一个上下文
public class StartApplicationContextContainer {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
		configApplicationContext.start();

	}


}
