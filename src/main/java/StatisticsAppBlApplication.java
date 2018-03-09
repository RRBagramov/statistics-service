import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 06.03.2018
 *
 * @author Robert Bagramov.
 */
@ComponentScan("ru.dz")
@SpringBootApplication
public class StatisticsAppBlApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsAppBlApplication.class, args);
    }
}
