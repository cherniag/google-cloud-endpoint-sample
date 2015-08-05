package com.mq.gae.voucher.admin.api.communities;

import com.mq.gae.voucher.admin.api.batches.BatchesEndpoint;

import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
public class CommunityService {
    private static final Logger logger = Logger.getLogger(BatchesEndpoint.class.getName());
    private static final CommunityService Community_SERVICE = new CommunityService();

    public static CommunityService getInstance() {
        return Community_SERVICE;
    }

    private CommunityService() {

    }

    public Community findOne(long communityId) {
        return ofy().load().type(Community.class).id(communityId).now();
    }

    public List<Community> findAll() {
        return ofy().load().type(Community.class).list();
    }

    public void createCommunity(Community Community) {
        ofy().save().entity(Community).now();
    }
}
