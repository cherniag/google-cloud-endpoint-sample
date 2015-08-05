package com.mq.gae.voucher.admin.api.vouchers;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.mq.gae.voucher.admin.api.batches.Batch;
import com.mq.gae.voucher.admin.api.batches.BatchesEndpoint;
import com.mq.gae.voucher.admin.api.campaigns.Campaign;
import com.mq.gae.voucher.admin.api.communities.Community;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
public class VoucherService {
    private static final Logger logger = Logger.getLogger(BatchesEndpoint.class.getName());
    private static final VoucherService VOUCHER_SERVICE = new VoucherService();
    private CodeGenerator codeGenerator = new CodeGenerator();

    public static VoucherService getInstance() {
        return VOUCHER_SERVICE;
    }

    private VoucherService() {

    }

    public List<Voucher> findAll(long communityId, long campaignId, long batchId) {
        Key<Community> communityKey = Key.create(Community.class, communityId);
        Key<Campaign> campaignKey = Key.create(communityKey, Campaign.class, campaignId);
        Key<Batch> batchKey = Key.create(campaignKey, Batch.class, batchId);
        return ofy().load().type(Voucher.class).ancestor(batchKey).list();
    }

    public void generate(Batch batch) {
        List<Voucher> vouchers = new ArrayList<>();
        for (int i = 0; i < batch.getGenerateCodesCount(); i++) {
            Voucher voucher = new Voucher(codeGenerator.generate());
            voucher.batchRef = Ref.create(batch);
            vouchers.add(voucher);
        }
        ofy().save().entities(vouchers).now();
    }


    public long redeem(long communityId, String voucherCode, long id, String email) {
        Key<Community> communityKey = Key.create(Community.class, communityId);
        List<Voucher> vouchers = ofy().load().type(Voucher.class).ancestor(communityKey).filter("code", voucherCode).list();
        if(vouchers.size()!=1) {
            logger.info("vouchers : " + vouchers);
            throw new RuntimeException("Actual size for " + communityId + " code " + voucherCode + " is " + vouchers.size());
        }
        Voucher voucher = vouchers.get(0);
        voucher.redeem(id, email);
        ofy().save().entity(voucher).now();
        Campaign campaign = voucher.batchRef.get().getCampaignRef().get();

        return campaign.productId;
    }
}
