package com.donkeigy.controller;

import com.donkeigy.objects.util.ApplicationInfo;
import com.donkeigy.service.util.OAuthConnection;
import com.tumblr.jumblr.JumblrClient;
import org.scribe.model.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cedric on 2/25/15.
 */
@Controller
@RequestMapping(value="/oauth")
public class OauthController
{
    @RequestMapping(value="/accessToken/retrieve", method= {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView createPage(@RequestParam("oauth_verifier") String oauth_verifier,
                                          @RequestParam("oauth_token") String oauth_token, HttpServletRequest request)
    {
        ModelAndView mav = new ModelAndView("oauth");
        ApplicationInfo info = new ApplicationInfo("pF5upteQMm5SBUFwE0vzDRS3OIqIKOokdfx0odY8aTLg60IkqJ", "iZ08fU69HR6VouBNaajVFF9FkaTW8p1lcG5qTFSDR4kJ1pU589");
        OAuthConnection oAuthConnection = new OAuthConnection();
        oAuthConnection.initService(info);

        JumblrClient client = new JumblrClient(info.getApiKey(),info.getApiSecret());
        Token requestToken = (Token) request.getSession().getAttribute("oauth-request-token");
        oAuthConnection.setRequestToken(requestToken);
         oAuthConnection.retrieveAccessToken(oauth_verifier);

        client.setToken(oAuthConnection.getAccessToken().getToken(), oAuthConnection.getAccessToken().getSecret());
        mav.addObject("oauth_verifier", oauth_verifier);
        mav.addObject("oauth_token", oauth_token);
        mav.addObject("user", client.user().getName());
        mav.addObject("followers", client.userFollowing());
        // Write the user's name
        return mav;
    }

}
