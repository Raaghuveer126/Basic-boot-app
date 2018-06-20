package com.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.framework.api.BasicBootApplication;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = BasicBootApplication.class)
public class BasicBootApplicationTests {

	@Test
	public void contextLoads() {
	}

}
