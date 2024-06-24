package co.pipat.shai_hulud;

import co.pipat.shai_hulud.feature.proxy.service.V4Proxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class ShaiHuludApplication implements CommandLineRunner {
	private final V4Proxy v4Proxy;

	@Override
	public void run(String... args) throws Exception {
		v4Proxy.x();
	}

	public static void main(String[] args) {
		SpringApplication.run(ShaiHuludApplication.class, args);
	}
}
