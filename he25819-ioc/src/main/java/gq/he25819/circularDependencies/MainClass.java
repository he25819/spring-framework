package gq.he25819.circularDependencies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
	public static void main(String[] args) {

		//创建IOC容器
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

		InstanceB instanceB = (InstanceB) ctx.getBean("instanceB");
		instanceB.getInstanceA().say();

	}

}
