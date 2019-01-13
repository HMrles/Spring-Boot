package com.hmrles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hmrles.SpringMVC4AngularJSApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringMVC4AngularJSApplication.class)
@WebAppConfiguration
public class Springmvc4AngularjsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
