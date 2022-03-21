package gq.he25819.circularDependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InstanceA {

	@Autowired
	private InstanceB instanceB;

	public InstanceA() {
		System.out.println("InstanceA实例化");
	}

	public InstanceB getInstanceB() {
		return instanceB;
	}

	public void setInstanceB(InstanceB instanceB) {
		this.instanceB = instanceB;
	}

	public void say() {
		System.out.println("I'm A");
	}
}
