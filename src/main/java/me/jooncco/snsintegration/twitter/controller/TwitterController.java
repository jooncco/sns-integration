package me.jooncco.snsintegration.twitter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/twitter")
@RequiredArgsConstructor
public class TwitterController {

    private final String oauthCallbackUrl= "https://cryptic-meadow-96107.herokuapp.com/twitter/oauth/callback";
    private final TwitterConnectionFactory twitterConnectionFactory;

    @GetMapping("/oauth")
    public void getTwitterToken(HttpServletRequest req, HttpServletResponse res) throws IOException {
        OAuth1Operations oAuthOperations= twitterConnectionFactory.getOAuthOperations();
        OAuthToken requestToken= oAuthOperations.fetchRequestToken(oauthCallbackUrl, null);
        // TODO: cache request token
        String authorizeUrl= oAuthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
        res.sendRedirect(authorizeUrl);
    }

    @GetMapping("/oauth/callback")
    public String getTwitterTokenCallback(@RequestParam(name = "oauth_token") String oauthToken, @RequestParam(name = "oauth_verifier") String oauthVerifier) {
        String tokenSecret= ""; // TODO: retrieve request token secret from cache
        OAuthToken requestToken= new OAuthToken(oauthToken, tokenSecret);
        OAuth1Operations oAuthOperations= twitterConnectionFactory.getOAuthOperations();
        OAuthToken accessToken= oAuthOperations.exchangeForAccessToken(new AuthorizedRequestToken(requestToken, oauthVerifier), null);
        Twitter twitter = new TwitterTemplate(
                "<CONSUMER_KEY>",
                "<CONSUMER_SECRET>",
                accessToken.getValue(),
                accessToken.getSecret()
        );
        TwitterProfile profile = twitter.userOperations().getUserProfile();
        return profile.toString();
    }
}
