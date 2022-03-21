package gq.he25819.circularDependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainStart {
	private static Map<String, RootBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	// 一级缓存
	private static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		loadBeanDefinitions();
	}

	private static void loadBeanDefinitions() throws InstantiationException, IllegalAccessException {
		RootBeanDefinition beanDefinitionA = new RootBeanDefinition(InstanceA.class);
		RootBeanDefinition beanDefinitionB = new RootBeanDefinition(InstanceB.class);
		beanDefinitionMap.put("instanceA", beanDefinitionA);
		beanDefinitionMap.put("instanceB", beanDefinitionB);

		// 循环创建bean
		for (String key : beanDefinitionMap.keySet()) {
			getBean(key);

		}
	}

	public static Object getBean(String beanName) throws IllegalAccessException, InstantiationException {
		Object singleton = getSingleton(beanName);
		if (singleton != null) {
			return singleton;
		}

		// 实例化
		RootBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		Class<?> beanClass = beanDefinition.getBeanClass();
		Object instanceBean = beanClass.newInstance();

		// 属性注入
		Field[] declaredFields = beanClass.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			Autowired annotation = declaredField.getAnnotation(Autowired.class);
			if (annotation != null) {
				declaredField.setAccessible(true);
				declaredField.set(instanceBean, getBean(declaredField.getName()));
			}
		}

		// 初始化 init-method...
		// ..

		singletonObjects.put(beanName, instanceBean);
		return instanceBean;
	}

	private static Object getSingleton(String beanName) {
		if (singletonObjects.containsKey(beanName)) {
			return singletonObjects.get(beanName);
		}
		return null;
	}

}
