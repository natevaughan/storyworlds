package storyworlds;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by nvaughan on 10/16/2016.
 */
@RunWith(SpringRunner.class)
public class AbstractSpringTest {

    @ComponentScan(basePackages = {
            "storyworlds.service", "storyworlds.model"
    })
    @Configuration
    @EnableMongoRepositories(basePackages = { "storyworlds.model.implementation" })
    public static class TestConfig {

    }

}
