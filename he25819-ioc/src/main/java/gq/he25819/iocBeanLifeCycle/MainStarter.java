package gq.he25819.iocBeanLifeCycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainStarter {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Car car = (Car) applicationContext.getBean("car");
		Car car2 = (Car) applicationContext.getBean("car");
		System.out.println(car.getName());
	}
}
