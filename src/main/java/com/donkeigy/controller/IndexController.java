package com.donkeigy.controller;

/**
 * Created by cedric on 2/25/15.
 */

import com.donkeigy.objects.util.ApplicationInfo;
import com.donkeigy.service.util.OAuthConnection;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/login")
public class IndexController
{
    @RequestMapping(value="/create", method= {RequestMethod.GET, RequestMethod.HEAD})
    public ModelAndView createPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login");
        ApplicationInfo info = new ApplicationInfo("pF5upteQMm5SBUFwE0vzDRS3OIqIKOokdfx0odY8aTLg60IkqJ", "iZ08fU69HR6VouBNaajVFF9FkaTW8p1lcG5qTFSDR4kJ1pU589");
        OAuthConnection oAuthConnection = new OAuthConnection();
        oAuthConnection.initService(info);
        mav.addObject("url", oAuthConnection.retrieveAuthorizationUrl());
        request.getSession().setAttribute("oauth-request-token", oAuthConnection.getRequestToken());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
  public String getGeneratedKey(@RequestBody String generatedKey)
  {
      String name = generatedKey;

       return generatedKey;

  }
}
