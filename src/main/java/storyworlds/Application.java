package storyworlds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import storyworlds.service.console.ConsoleIO;

@SpringBootApplication
@EnableMongoRepositories(basePackages = { "storyworlds.model.implementation" })
@ComponentScan(basePackages = {"storyworlds.service", "storyworlds.web.control"})
public class Application implements CommandLineRunner {

    private static Logger logr = LoggerFactory.getLogger(Application.class);

    @Autowired
    ConsoleIO consoleIO;

    @Autowired
    Environment env;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logr.info(">>> logging check <<<");

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("console")) {
                consoleIO.run();
            }
        }
    }
}
