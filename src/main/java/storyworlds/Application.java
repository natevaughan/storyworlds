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
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.service.console.ConsoleIO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"storyworlds.service", "storyworlds.web.control", "storyworlds.config"})
public class Application implements CommandLineRunner {

    private static Logger logr = LoggerFactory.getLogger(Application.class);

    @Autowired
    ConsoleIO consoleIO;

    @Autowired
    Environment env;

    @Autowired
    LocationRepository locationRepository;

    List<storyworlds.model.Location> locations = new ArrayList<>();

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
            if (args[0].equalsIgnoreCase("threads")) {
                ExecutorService exec = Executors.newFixedThreadPool(5);

                for (int i = 0; i < 10; i++) {
                    exec.execute(new DBFetcher());
                }
                int j = 0;
                while (j < 100) {
                    ++j;
                    System.out.println(locations.size());
                    Thread.sleep(500);
                }
            }

        }
    }

    private class DBFetcher implements Runnable {

        public void run() {
            System.out.println("Thread reporting");
            locations.add(locationRepository.findAll().get(0));
        }
    }
}
