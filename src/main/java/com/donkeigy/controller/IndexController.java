package com.donkeigy.controller;

/**
 * Created by cedric on 2/25/15.
 */

import com.donkeigy.objects.util.ApplicationInfo;
import com.donkeigy.objects.util.UserJSPBean;
import com.donkeigy.service.OAuthService;
import com.donkeigy.service.util.OAuthConnection;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.User;
import org.scribe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/login")
public class IndexController
{
    @Autowired
    OAuthService oAuthService;

    @RequestMapping(value="/create", method= {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView createPage(HttpServletRequest request) {


        Token accessToken = (Token) request.getSession().getAttribute("oauth-access-token");
        ApplicationInfo info = new ApplicationInfo("pF5upteQMm5SBUFwE0vzDRS3OIqIKOokdfx0odY8aTLg60IkqJ", "iZ08fU69HR6VouBNaajVFF9FkaTW8p1lcG5qTFSDR4kJ1pU589");
        ModelAndView mav;
        if(accessToken == null)
        {

            OAuthConnection oAuthConnection = new OAuthConnection();
            mav = new ModelAndView("login");
            oAuthConnection.initService(info);
            mav.addObject("url", oAuthConnection.retrieveAuthorizationUrl());
            mav.addObject("users", oAuthService.retrieveAllOAuthToken());
            request.getSession().setAttribute("oauth-request-token", oAuthConnection.getRequestToken());
        }
        else
        {
            mav = new ModelAndView("data");
            JumblrClient client = new JumblrClient(info.getApiKey(),info.getApiSecret());
            client.setToken(accessToken.getToken(), accessToken.getSecret());
            List<UserJSPBean> followers = new LinkedList<UserJSPBean>();
            User user = client.user();
            for (Blog blog : user.getBlogs())
            {

                boolean running = true;
                int offset = 0;
                while(running)

                {
                    Map<String, Integer> options = new HashMap<String, Integer>();
                    options.put("limit", 20);
                    options.put("offset", offset);
                    List<User> currentSetFollowers = blog.followers(options);
                    for(User tempUser : currentSetFollowers)
                    {
                        followers.add(new UserJSPBean(tempUser));
                    }

                    if(currentSetFollowers.size() < 20 || followers.size() >= 200)
                    {
                        running = false;

                    }
                    offset = offset+20;

                }



            }

            mav.addObject("user", client.user().getName());
            mav.addObject("followers", followers);

        }
        return mav;


    }


}
