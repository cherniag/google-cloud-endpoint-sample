package com.mq.gae.voucher.admin.client;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/27/2015
 */
public class TestCall {

    /** E-mail address of the service account. */
    private static final String SERVICE_ACCOUNT_EMAIL = "617753369170-h6h6ocmobetkv8pb4gms600e6ui3544a@developer.gserviceaccount.com";


    /** Global configuration of Google Cloud Storage OAuth 2.0 scope. */
    private static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";

    /** Global instance of the HTTP transport. */
    private static HttpTransport httpTransport;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) {
        try {
            try {
                httpTransport = GoogleNetHttpTransport.newTrustedTransport();

                // Build a service account credential.
                GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                        .setJsonFactory(JSON_FACTORY)
                        .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                        .setServiceAccountScopes(Collections.singleton(EMAIL_SCOPE))
                        .setServiceAccountPrivateKeyFromP12File(new File("D:\\key.p12"))
                                //.setServiceAccountPrivateKeyId("af5da290cf4beb9ea2061931a4daa6bc4429c4e9")
                        .build();

                // Set up and execute a Google Cloud Storage request.
                String URI = "https://voucher-admin.appspot.com/_ah/api/batches/v1/batches";
                HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
                GenericUrl url = new GenericUrl(URI);
                HttpRequest request = requestFactory.buildGetRequest(url);
                HttpResponse response = request.execute();
                String content = response.parseAsString();


                System.out.println(content);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(1);
    }
}
