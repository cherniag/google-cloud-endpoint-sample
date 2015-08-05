package com.mq.gae.voucher.admin.api.batches;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.mq.gae.voucher.admin.api.AuthorizationService;
import com.mq.gae.voucher.admin.api.Constants;

import javax.inject.Named;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import static com.google.api.server.spi.config.ApiMethod.HttpMethod.*;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.GET;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.POST;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/24/2015
 *
 * Check oauth2 working: https://developers.google.com/oauthplayground
 */
@Api(name = "voucheradmin",
        version = "v2",
        scopes = {Constants.EMAIL_SCOPE}, // Access to OAuth2 API to view your email address
        clientIds = {
                Constants.WEB_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID,
                Constants.SERVICE_ACCOUNT_CLIENT_ID})  // service account client id
public class BatchesEndpoint {
    static final Logger logger = Logger.getLogger(BatchesEndpoint.class.getName());
    BatchService batchService = BatchService.getInstance();
    AuthorizationService authorizationService = AuthorizationService.getInstance();


    @ApiMethod(name = "communities.campaigns.batches.getOne",
            path = "communities/{communityId}/campaigns/{campaignId}/batches/{batchId}",
            httpMethod = GET)
    public Batch getBatch(@Named("communityId") long communityId,
                          @Named("campaignId") long campaignId,
                          @Named("batchId") long batchId,
                          User user) throws EntityNotFoundException, OAuthRequestException {
        authorizationService.authorize(user);
        logger.info("getOne with id " + batchId);
        return batchService.findOne(communityId, campaignId, batchId);
    }

    @ApiMethod(name = "communities.campaigns.batches.getAll",
            path = "communities/{communityId}/campaigns/{campaignId}/batches",
            httpMethod = GET)
    public List<Batch> getBatches(@Named("communityId") long communityId,
                                  @Named("campaignId") long campaignId,
                                  User user) throws EntityNotFoundException, OAuthRequestException {
        authorizationService.authorize(user);
        logger.info("getAll");
        return batchService.findAll(communityId, campaignId);
    }

    @ApiMethod(name = "communities.campaigns.batches.create",
            path = "communities/{communityId}/campaigns/{campaignId}/batches",
            httpMethod = POST)
    public void create(@Named("communityId") long communityId,
                       @Named("campaignId") long campaignId,
                       Batch batch,
                       User user) throws ParseException, OAuthRequestException {
        authorizationService.authorize(user);
        logger.info("create: batch=" + batch);
        batchService.createBatch(batch, communityId, campaignId);
    }

    @ApiMethod(name = "communities.campaigns.batches.activate",
            path = "communities/{communityId}/campaigns/{campaignId}/batches/{batchId}/activate",
            httpMethod = PUT)
    public Batch activate(@Named("communityId") long communityId,
                       @Named("campaignId") long campaignId,
                       @Named("batchId") long batchId,
                       User user) throws ParseException, OAuthRequestException {
        authorizationService.authorize(user);
        logger.info("activateBatch: batchId=" + batchId);
        return batchService.changeStatus(batchId, communityId, campaignId, true);
    }

    @ApiMethod(name = "communities.campaigns.batches.deactivate",
            path = "communities/{communityId}/campaigns/{campaignId}/batches/{batchId}/deactivate",
            httpMethod = PUT)
    public Batch deactivate(@Named("communityId") long communityId,
                         @Named("campaignId") long campaignId,
                         @Named("batchId") long batchId,
                         User user) throws ParseException, OAuthRequestException {
        authorizationService.authorize(user);
        logger.info("deactivateBatch: batchId=" + batchId);
        return batchService.changeStatus(batchId, communityId, campaignId, false);
    }

}
