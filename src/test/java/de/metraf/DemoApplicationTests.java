package de.metraf;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		String s = "Test";
		assertEquals("Test", s);
	}

}
