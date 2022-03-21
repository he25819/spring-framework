package gq.he25819.circularDependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		// 假设：A被切点命中 需要创建动态代理 @PointCut("execution(* *..InstantA.*(..))")
		if (bean instanceof InstanceA) {
			JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(bean);
			return jdkDynamicProxy.getProxy();
		}
		return bean;
	}
}
