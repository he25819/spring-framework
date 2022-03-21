package gq.he25819.circularDependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstanceB {
	@Autowired
	private InstanceA instanceA;

	public InstanceB() {
		System.out.println("InstanceB实例化");
	}

	public InstanceA getInstanceA() {
		return instanceA;
	}

	public void setInstanceA(InstanceA instanceA) {
		this.instanceA = instanceA;
	}
}
