package br.com.isaccanedo.mail.api.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class ApplicationTest {
	@Test
	public void testIfApplicationRunsSuccessfully() {
		Runnable runnable = () -> {
			Application application = new Application();
			SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(Application.class);
			applicationBuilder = application.configure(applicationBuilder);
			
			String[] args = new String[1];
			args[0] = "test";
			
			Application.main(args);
		};
		Thread thread = new Thread(runnable);
		thread.run();
		thread.interrupt();
	}
}
