package storyworlds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import storyworlds.service.console.ConsoleIO;

@SpringBootApplication
@EnableMongoRepositories(basePackages = { "storyworlds.model.implementation" })
@ComponentScan(basePackages = {"storyworlds.service"})
public class Application implements CommandLineRunner {

    @Autowired
    ConsoleIO consoleIO;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleIO.run();
    }
}
