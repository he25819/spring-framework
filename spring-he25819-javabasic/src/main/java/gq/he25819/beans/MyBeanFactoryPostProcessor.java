package gq.he25819.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author hexw
 * @version 1.0
 * @create 2022-01-25 01:25
 **/
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition car = (GenericBeanDefinition) beanFactory.getBeanDefinition("car");

//		car.setScope("prototype");

		// 设置实例化的构造器
		/*ConstructorArgumentValues values = new ConstructorArgumentValues();
		values.addIndexedArgumentValue(0, "测试");
		car.setConstructorArgumentValues(values);*/

		// 设置属性
/*
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		mutablePropertyValues.addPropertyValue("tank", new Tank());
		car.setPropertyValues(mutablePropertyValues);*/

		// 设置自动注入的类型
//		car.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
	}
}
