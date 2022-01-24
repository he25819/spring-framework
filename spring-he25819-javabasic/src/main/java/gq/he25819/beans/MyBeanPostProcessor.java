package gq.he25819.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author hexw
 * @version 1.0
 * @create 2022-01-25 02:28
 **/
//@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanClass == Car.class) {
			Car car = new Car();
			car.setName("保时捷");
			return car;
		}
		return null;
	}
}
