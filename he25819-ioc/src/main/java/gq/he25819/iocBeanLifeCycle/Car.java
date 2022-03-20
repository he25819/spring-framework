package gq.he25819.iocBeanLifeCycle;

import org.springframework.stereotype.Component;

@Component
public class Car {
	private String name;
	private Tank tank;

	public Car() {
		System.out.println("Car构造函数执行了");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	@Override
	public String toString() {
		return "Car{" +
				"name='" + name + '\'' +
				", tank=" + tank +
				'}';
	}
}
