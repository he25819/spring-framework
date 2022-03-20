package gq.he25819.iocBeanLifeCycle;

import org.springframework.stereotype.Component;

// scan扫描的2个car会报错，扫描一个@Bean一个的话，后解析(@Bean)的会覆盖前解析的car。
//@Component("car")
@Component
public class Tank {
	private Integer index;
	private String name;

	public Tank() {
		System.out.println("Tank构造函数执行了");
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tank{" +
				"index=" + index +
				", name='" + name + '\'' +
				'}';
	}
}
