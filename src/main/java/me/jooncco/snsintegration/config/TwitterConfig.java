package me.jooncco.snsintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
public class TwitterConfig {

    @Bean(name = "twitterConnectionFactory")
    public TwitterConnectionFactory getTwitterConnectionFactory() {
        return new TwitterConnectionFactory(
                "Fdj8nGGhpUvtPEQeaLf5QXUu1",
                "gFRt3XRCZf6V4EC8NuMWhMw1ha9S7rDnG32r1DFWcGFR9NuTN2"
        );
    }
}
