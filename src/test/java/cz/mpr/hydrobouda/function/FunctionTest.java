package cz.mpr.hydrobouda.function;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Test;

/**
 * A collection of simple test related to experimets with functional interfaces and lambdas.
 * 
 * @author MPR
 * @version 1.0
 * @since 7.1.2019
 *
 */
public class FunctionTest {
	@Test
	public void helloWorldConsumerTest() {
		Consumer<String> helloWorldConsumer = (String name) -> System.out.println("Hello world from " + name);
		
		String name = "Martin";
		helloWorldConsumer.accept(name);
	}
	
	@Test
	public void sysoutConsumerTest() {
		Consumer<String> sysoutConsumer = System.out::println;
		
		String something = "Something";
		sysoutConsumer.accept(something);
	}
	
	@Test
	public void capitalizeFunctionTest() {
		String name = "Martin";
		
		Function<String, String> capitalizeFunction = s -> s.toUpperCase();
		Function<String, String> capitalizeReference = String::toUpperCase;
		
		String nameUppercasedFunction = capitalizeFunction.apply(name);
		String nameUppercasedReference = capitalizeReference.apply(name);
		
		assertEquals(nameUppercasedFunction, nameUppercasedReference);
	}
}
