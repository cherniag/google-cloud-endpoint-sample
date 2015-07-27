package com.mq.gae.voucher.admin.api;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming your API.
 */
public class Constants {

    public static final String WEB_CLIENT_ID = "617753369170-chlc7pjm7alo6pjgsfjv9oa8kskjcr3b.apps.googleusercontent.com";
    public static final String SERVICE_ACCOUNT_CLIENT_ID = "617753369170-h6h6ocmobetkv8pb4gms600e6ui3544a.apps.googleusercontent.com";
    public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;

//  public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
//  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
//  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

    // All scopes: https://discovery-check.appspot.com/
    // EMAIL_SCOPE : https://discovery-check.appspot.com/oauth2/v2
    // Lets you access OAuth2 protocol related APIs - View your email address
    public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";

}
