package com.mq.gae.voucher.admin.api.campaigns;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.mq.gae.voucher.admin.api.batches.BatchesEndpoint;
import com.mq.gae.voucher.admin.api.communities.Community;
import com.mq.gae.voucher.admin.api.vouchers.VoucherService;

import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
public class CampaignService {
    private static final Logger logger = Logger.getLogger(BatchesEndpoint.class.getName());
    private static final CampaignService CAMPAIGN_SERVICE = new CampaignService();

    public static CampaignService getInstance() {
        return CAMPAIGN_SERVICE;
    }

    private CampaignService() {

    }

    public Campaign findOne(long communityId, long campaignId) {
        Key<Community> communityKey = Key.create(Community.class, communityId);
        Key<Campaign> campaignKey = Key.create(communityKey, Campaign.class, campaignId);
        return ofy().load().key(campaignKey).now();
    }

    public List<Campaign> findAll(long communityId, int page, int size) {
        Key<Community> communityKey = Key.create(Community.class, communityId);
        logger.info("communityKey: " + communityKey.getString());
        return ofy().load().type(Campaign.class).ancestor(communityKey).offset(page * size).limit(size).list();
    }

    public void createCampaign(Campaign campaign, long communityId) {
        Key<Community> communityKey = Key.create(Community.class, communityId);
        campaign.communityRef = Ref.create(communityKey);
        ofy().save().entity(campaign).now();
    }

    public Campaign changeStatus(long communityId, long campaignId, boolean isActive) {
        Key<Community> communityKey = Key.create(Community.class, communityId);
        Key<Campaign> campaignKey = Key.create(communityKey, Campaign.class, campaignId);
        Campaign campaign = ofy().load().key(campaignKey).now();
        campaign.isActive = isActive;
        ofy().save().entity(campaign).now();
        return campaign;
    }

}
