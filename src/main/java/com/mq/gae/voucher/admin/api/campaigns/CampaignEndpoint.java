package com.mq.gae.voucher.admin.api.campaigns;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.DefaultValue;
import com.google.api.server.spi.config.Nullable;
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

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
@Api(name = "voucheradmin",
        version = "v2",
        scopes = {Constants.EMAIL_SCOPE}, // Access to OAuth2 API to view your email address
        clientIds = {
                Constants.WEB_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID,
                Constants.SERVICE_ACCOUNT_CLIENT_ID})  // service account client id
public class CampaignEndpoint {
    static final Logger logger = Logger.getLogger(CampaignEndpoint.class.getName());
    CampaignService campaignService = CampaignService.getInstance();
    AuthorizationService authorizationService = AuthorizationService.getInstance();


    @ApiMethod(name = "communities.campaigns.getOne",
            path = "communities/{communityId}/campaigns/{campaignId}",
            httpMethod = GET)
    public Campaign getOne(@Named("communityId") long communityId,
                                @Named("campaignId") long campaignId,
                                User user) throws EntityNotFoundException, OAuthRequestException {
        logger.info("getOne communityId:" + communityId + ", campaignId:" + campaignId);
        authorizationService.authorize(user);
        return campaignService.findOne(communityId, campaignId);
    }

    @ApiMethod(name = "communities.campaigns.getAll",
            path = "communities/{communityId}/campaigns",
            httpMethod = GET)
    public List<Campaign> getAll(@Named("communityId") long communityId,
                                       @Nullable @DefaultValue("0") @Named("page") int page,
                                       @Nullable @DefaultValue("1000000") @Named("size") int size,
                                       User user) throws EntityNotFoundException, OAuthRequestException {
        logger.info("getAll communityId:" + communityId + ", page:" + page + ", size:" + size);
        authorizationService.authorize(user);
        return campaignService.findAll(communityId, page, size);
    }

    @ApiMethod(name = "communities.campaigns.create",
            path = "communities/{communityId}/campaigns",
            httpMethod = POST)
    public void create(@Named("communityId") long communityId,
                       Campaign campaign,
                       User user) throws ParseException, OAuthRequestException {
        logger.info("create communityId:" + communityId + ", campaign:" + campaign);
        authorizationService.authorize(user);
        campaignService.createCampaign(campaign, communityId);
    }

    @ApiMethod(name = "communities.campaigns.activate",
            path = "communities/{communityId}/campaigns/{campaignId}/activate",
            httpMethod = PUT)
    public Campaign activate(@Named("communityId") long communityId,
                             @Named("campaignId") long campaignId,
                             User user) throws ParseException, OAuthRequestException {
        logger.info("activate communityId:" + communityId + ", campaignId:" + campaignId);
        authorizationService.authorize(user);
        return campaignService.changeStatus(communityId, campaignId, true);
    }

    @ApiMethod(name = "communities.campaigns.inactivate",
            path = "communities/{communityId}/campaigns/{campaignId}/inactivate",
            httpMethod = PUT)
    public Campaign deactivate(@Named("communityId") long communityId,
                               @Named("campaignId") long campaignId,
                               User user) throws ParseException, OAuthRequestException {
        logger.info("deactivate communityId:" + communityId + ", campaignId:" + campaignId);
        authorizationService.authorize(user);
        return campaignService.changeStatus(communityId, campaignId, false);
    }
}
