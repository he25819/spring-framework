package gq.he25819.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author hexw
 * @version 1.0
 * @create 2022-01-24 22:27
 **/
@Component
public class Car /*implements FactoryBean<Car>*/ {
	private String name = "兰博基尼";

	public Car() {
		System.out.println("new Car()");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car{" +
				"name='" + name + '\'' +
				'}';
	}
/*
	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		car.setName("大众");
		return car;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}*/
}
