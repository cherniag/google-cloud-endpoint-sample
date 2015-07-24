package com.mq.gae.voucher.admin.api.batches;

import com.google.api.server.spi.config.Transformer;

import java.util.logging.Logger;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/24/2015
 */
public class BatchTransformer implements Transformer<String, Batch> {

    Logger logger = Logger.getGlobal();

    @Override
    public Batch transformTo(String s) {

        logger.info("to " + s);
        return null;
    }

    @Override
    public String transformFrom(Batch batch) {
        logger.info("from " + batch);
        return null;
    }
}
