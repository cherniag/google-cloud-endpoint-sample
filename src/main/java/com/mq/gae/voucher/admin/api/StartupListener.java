package com.mq.gae.voucher.admin.api;

import com.googlecode.objectify.ObjectifyService;
import com.mq.gae.voucher.admin.api.batches.Batch;
import com.mq.gae.voucher.admin.api.campaigns.Campaign;
import com.mq.gae.voucher.admin.api.communities.Community;
import com.mq.gae.voucher.admin.api.vouchers.Voucher;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
public class StartupListener implements ServletContextListener {
    static final Logger logger = Logger.getLogger(StartupListener.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("contextInitialized: " + servletContextEvent);
        ObjectifyService.register(Batch.class);
        ObjectifyService.register(Voucher.class);
        ObjectifyService.register(Campaign.class);
        ObjectifyService.register(Community.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
