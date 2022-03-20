package gq.he25819.iocBeanLifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "gq.he25819.iocBeanLifeCycle")
public class MainConfig {
//	@Bean
	public Car car() {
		Car car = new Car();
		car.setName("haha");
		// 如果没有@Configuration，则此配置类不会创建cglib代理，tank()方法不会被代理，
		// 因此每次getBean("car")都会调用new Tank()创建新的对象。
		// cglib动态代理在org.springframework.context.annotation.ConfigurationClassEnhancer.enhance(..)方法创建的，
		// 是spring在ConfigurationClassPostProcessor.postProcessBeanFactory(..)中实现的。（ConfigurationClassPostProcessor实现了BeanFactoryPostProcessor接口）
		car.setTank(tank());
		return car;
	}

//	@Bean
	public Tank tank() {
		return new Tank();
	}
}
