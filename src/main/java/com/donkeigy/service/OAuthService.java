package com.donkeigy.service;

import com.donkeigy.dao.interfaces.OAuthDAO;
import com.donkeigy.objects.OAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cedric on 2/25/15.
 */
@Repository("oAuthService")
public class OAuthService
{
    @Autowired
    OAuthDAO oAuthDAO;


    public List<OAuthToken> retrieveAllOAuthToken()
    {
        return oAuthDAO.getAllOAuth();
    }
}
