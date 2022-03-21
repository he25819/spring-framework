package gq.he25819.circularDependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MainStart {
//	private static Map<String, RootBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	private static Map<String, RootBeanDefinition> beanDefinitionMap = new LinkedHashMap<>();

	// 一级缓存
	private static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

	// 二级缓存
	private static Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

	// 三级缓存
	private static Map<String, ObjectFactory> singletonFactories = new ConcurrentHashMap<>();

	// 循环依赖标识
	public static Set<String> singletonsCurrentlyInCreation = new HashSet<>();


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
		InstanceA instanceA = (InstanceA) getBean("instanceA");
		instanceA.say();
		InstanceB instanceB = (InstanceB) getBean("instanceB");
		instanceB.getInstanceA().say();
	}

	public static Object getBean(String beanName) throws IllegalAccessException, InstantiationException {
		Object singleton = getSingleton(beanName);
		if (singleton != null) {
			return singleton;
		}

		// 标记正在创建
		singletonsCurrentlyInCreation.add(beanName);
		// createBean

		// 实例化
		RootBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		Class<?> beanClass = beanDefinition.getBeanClass();
		Object instanceBean = beanClass.newInstance();

		// 创建动态代理 （耦合、BeanPostProcessor）
		// AOP循环依赖时，动态代理在这里创建。spring还是希望正常的bean（没有循环依赖） 还是在初始化之后创建。
		// 只在循环依赖的情况下在实例化之后创建proxy
//		Object obj = new JdkProxyBeanPostProcessor().getEarlyBeanReference(earlySingletonObjects.get(beanName), beanName);

		Object finalInstanceBean = instanceBean;
		singletonFactories.put(beanName, () -> {
			return new JdkProxyBeanPostProcessor().getEarlyBeanReference(finalInstanceBean, beanName);
		});

//		// 添加到二级缓存
//		earlySingletonObjects.put(beanName, instanceBean);

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
		// 正常情况下会在初始化之后创建proxy

		if (earlySingletonObjects.containsKey(beanName)) {
			instanceBean = earlySingletonObjects.get(beanName);
		}
		// 添加到一级缓存
		singletonObjects.put(beanName, instanceBean);
		singletonFactories.remove(beanName);

		// remove 二级缓存和三级缓存

		return instanceBean;
	}

	private static Object getSingleton(String beanName) {
		// 先从一级缓存中拿
		Object bean = singletonObjects.get(beanName);
		// 说明是循环依赖
		if (bean == null && singletonsCurrentlyInCreation.contains(beanName)) {
			bean = earlySingletonObjects.get(beanName);
			// 如果二级缓存中没有就从三级缓存中拿
			if (bean == null) {
				// 从三级缓存中拿
				ObjectFactory objectFactory = singletonFactories.get(beanName);
				if (objectFactory != null) {
					bean = objectFactory.getObject();
					if (bean != null) {
						earlySingletonObjects.put(beanName, bean);
						singletonFactories.remove(beanName);
					}
				}
			}

		}
		return bean;
	}

}
