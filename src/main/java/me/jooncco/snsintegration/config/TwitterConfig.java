package me.jooncco.snsintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
public class TwitterConfig {

    @Bean(name = "twitterConnectionFactory")
    public TwitterConnectionFactory getTwitterConnectionFactory() {
        return new TwitterConnectionFactory(
                "<CONSUMER_KEY>",
                "<CONSUMER_SECRET>"
        );
    }
}
