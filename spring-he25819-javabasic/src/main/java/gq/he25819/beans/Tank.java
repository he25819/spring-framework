package gq.he25819.beans;

import org.springframework.stereotype.Component;

@Component
public class Tank {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tank{" +
				"name='" + name + '\'' +
				'}';
	}
}
