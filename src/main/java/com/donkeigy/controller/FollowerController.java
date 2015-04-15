package com.donkeigy.controller;

import com.donkeigy.objects.OAuthToken;
import com.donkeigy.objects.util.ApplicationInfo;
import com.donkeigy.service.OAuthService;
import com.tumblr.jumblr.JumblrClient;
import org.scribe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cedric on 2/27/15.
 */

@Controller
@RequestMapping(value="/followers")
public class FollowerController
{
    @Autowired
    OAuthService oAuthService;

    @RequestMapping(value="/follow/{blogid}/{tokenid}", method= RequestMethod.GET)
    @ResponseBody
    public String followBlog(@PathVariable Integer tokenid, @PathVariable String blogid, HttpServletRequest request)
    {

        ApplicationInfo info = new ApplicationInfo("pF5upteQMm5SBUFwE0vzDRS3OIqIKOokdfx0odY8aTLg60IkqJ", "iZ08fU69HR6VouBNaajVFF9FkaTW8p1lcG5qTFSDR4kJ1pU589");
        JumblrClient client = new JumblrClient(info.getApiKey(),info.getApiSecret());
        OAuthToken accessToken = oAuthService.retrieveOAuthTokenbyId(tokenid.intValue());

        client.setToken(accessToken.getToken(), accessToken.getSecret());
        client.follow(blogid+".tumblr.com");
        return "Complete";

    }


}
