package com.mq.gae.voucher.admin.api.vouchers;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.DefaultValue;
import com.google.api.server.spi.config.Nullable;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.mq.gae.voucher.admin.api.AuthorizationService;
import com.mq.gae.voucher.admin.api.Constants;
import com.mq.gae.voucher.admin.api.dtos.ProductDto;
import com.mq.gae.voucher.admin.api.dtos.UserDto;

import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

import static com.google.api.server.spi.config.ApiMethod.HttpMethod.GET;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.PUT;

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
public class VouchersEndpoint {
    static final Logger logger = Logger.getLogger(VouchersEndpoint.class.getName());
    AuthorizationService authorizationService = AuthorizationService.getInstance();
    VoucherService voucherService = VoucherService.getInstance();

    @ApiMethod(name = "communities.campaigns.batches.vouchers.getAll",
            path = "communities/{communityId}/campaigns/{campaignId}/batches/{batchId}/vouchers",
            httpMethod = GET)
    public List<Voucher> getAll(@Named("communityId") long communityId,
                                     @Named("campaignId") long campaignId,
                                     @Named("batchId") long batchId,
                                     @Nullable @DefaultValue("0") @Named("page") int page,
                                     @Nullable @DefaultValue("1000000") @Named("size") int size,
                                     User user) throws EntityNotFoundException, OAuthRequestException {
        logger.info("getAll communityId:" + communityId + ", campaignId:" + campaignId + ", batchId:" + batchId + ", page:" + page + ", size:" + size);
        authorizationService.authorize(user);
        return voucherService.findAll(communityId, campaignId, batchId, page, size);
    }

    @ApiMethod(name = "communities.campaigns.batches.vouchers.redeem",
            path = "communities/{communityId}/vouchers/{voucherCode}/redeem",
            httpMethod = PUT)
    public ProductDto redeem(@Named("communityId") long communityId,
                                       @Named("voucherCode") String voucherCode,
                                       UserDto userDto,
                                       User user) throws EntityNotFoundException, OAuthRequestException {
        logger.info("redeem communityId:" + communityId + ", voucherCode:" + voucherCode + ", userDto:" + userDto);
        authorizationService.authorize(user);
        long redeem = voucherService.redeem(communityId, voucherCode, userDto.id, userDto.userName);
        return new ProductDto(redeem);
    }
}
