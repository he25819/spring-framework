package gq.he25819.circularDependencies;

import org.springframework.beans.BeansException;

@FunctionalInterface
public interface ObjectFactory<T> {
	T getObject() throws BeansException;
}
