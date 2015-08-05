package com.mq.gae.voucher.admin.api;

import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

import java.util.logging.Logger;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
public class AuthorizationService {
    static final Logger logger = Logger.getLogger(AuthorizationService.class.getName());
    private static final AuthorizationService AUTHORIZATION_SERVICE= new AuthorizationService();

    private AuthorizationService(){

    }

    public static AuthorizationService getInstance() {
        return AUTHORIZATION_SERVICE;
    }

    public void authorize(User user) throws OAuthRequestException {
        logger.info("Authorize user " + user);
        /*if(user == null) {
            throw new OAuthRequestException("Not authorized");
        }*/
    }
}
