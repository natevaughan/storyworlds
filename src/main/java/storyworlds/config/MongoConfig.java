package storyworlds.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by nvaughan on 10/29/2016.
 */

@Configuration
@EnableMongoRepositories(basePackages = { "storyworlds.model.implementation" })
public class MongoConfig extends AbstractMongoConfiguration implements PropertyKeys {

    private Logger logr = LoggerFactory.getLogger(getClass());

    @Autowired
    Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty(KEY_MONGO_DATABASE_NAME);
    }

    @Override
    public Mongo mongo() throws Exception {

        logr.info(">>> mongo setup <<<");
        logr.info("connected to database " + env.getProperty(KEY_MONGO_DATABASE_NAME) + " at " + env.getProperty(KEY_MONGO_DATABASE_URI));
        MongoClientURI uri  = new MongoClientURI(env.getProperty(KEY_MONGO_DATABASE_URI));
        return new MongoClient(uri);
    }

}