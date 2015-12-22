import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/10/24 0024.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
//        Set<Object> config = new HashSet<Object>();
//        config.add("classpath:spring/springconfig.xml");

        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(true);
        springApplication.setShowBanner(false);
//        springApplication.setSources(config);
        springApplication.run(args);
    }
}
