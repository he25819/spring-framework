package gq.he25819.circularDependencies;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("gq.he25819.circularDependencies")
//@ImportResource(value = {"classpath:beans/beans.xml"})
@EnableAspectJAutoProxy
public class MainConfig {
}
