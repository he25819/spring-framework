import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hexw
 * @version 1.0
 * @create 2022-01-24 22:29
 **/
public class AppMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:/spring.xml");
		System.out.println("ioc.getBean(\"car\") = " + ioc.getBean("car"));	// 获得FactoryBean修饰后的getObject()返回的对象
//		System.out.println("ioc.getBean(\"car\") = " + ioc.getBean("&car"));	// 获得Bean本身
//		System.out.println("ioc.getBean(\"car\") = " + ioc.getBean("car"));
	}
}
