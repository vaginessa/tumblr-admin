package com.donkeigy.dao.interfaces;


import com.donkeigy.objects.OAuthToken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cedric on 11/18/14.
 */


public interface OAuthDAO
{
    public void saveOAuthToken(OAuthToken oat);
    public OAuthToken getOAuthTokenById(int id);
    public List<OAuthToken> getOAuthTokenByExample(OAuthToken example);
    public void deleteOAuthToken(OAuthToken oat);
    public List<OAuthToken> getAllOAuth();
}

