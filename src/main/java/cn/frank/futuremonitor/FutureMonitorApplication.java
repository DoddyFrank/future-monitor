package cn.frank.futuremonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FutureMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FutureMonitorApplication.class, args);
    }

}
