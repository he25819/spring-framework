package gq.he25819.beans;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
	public void sayHi() {
		System.out.println("hello spring ioc");
	}
}
