package gq.he25819.beans.config;

import gq.he25819.beans.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("gq.he25819")
public class TestConfig {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
		TestBean bean = applicationContext.getBean(TestBean.class);
		bean.sayHi();
	}
}
