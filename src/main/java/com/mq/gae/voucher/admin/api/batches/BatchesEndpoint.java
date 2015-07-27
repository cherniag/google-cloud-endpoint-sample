package com.mq.gae.voucher.admin.api.batches;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.mq.gae.voucher.admin.api.Constants;

import javax.inject.Named;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import static com.google.api.server.spi.config.ApiMethod.HttpMethod.GET;
import static com.google.api.server.spi.config.ApiMethod.HttpMethod.POST;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/24/2015
 *
 * Check oauth2 working: https://developers.google.com/oauthplayground
 */
@Api(name = "batches",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE}, // Access to OAuth2 API to view your email address
        clientIds = {
                //Constants.WEB_CLIENT_ID,
               // Constants.API_EXPLORER_CLIENT_ID,
                Constants.SERVICE_ACCOUNT_CLIENT_ID})  // service account client id
public class BatchesEndpoint {
    static final Logger logger = Logger.getLogger(BatchesEndpoint.class.getName());
    BatchService batchService = new BatchService();


    @ApiMethod(name = "getBatch",
            path = "batches/{id}",
            httpMethod = GET)
    public Batch getBatch(@Named("id") long id, User user) throws EntityNotFoundException, OAuthRequestException {
        logger.info("user " + user);
        if(user == null) {
            throw new OAuthRequestException("Not authorized");
        }
        logger.info("getOne with id " + id);
        return batchService.findOne(id);
    }

    @ApiMethod(name = "getBatches",
            path = "batches",
            httpMethod = GET)
    public List<Batch> getBatches(User user) throws EntityNotFoundException, OAuthRequestException {
        logger.info("user " + user);
        if(user == null) {
            throw new OAuthRequestException("Not authorized");
        }
        logger.info("getAll");
        return batchService.findAll();
    }

    @ApiMethod(name = "create",
            path = "batches",
            httpMethod = POST)
    public void createJson(Batch batch, User user) throws ParseException, OAuthRequestException {
        logger.info("user " + user);
        if(user == null) {
            throw new OAuthRequestException("Not authorized");
        }
        logger.info("createJson: batch=" + batch);
        batchService.createBatch(batch);

    }

    /*@ApiMethod(name = "create", path = "batches", httpMethod = ApiMethod.HttpMethod.POST)
    public void create(@Named("name") String name,
                       @Named("generateCodesCount") int generateCodesCount,
                       @Named("startDate") String startDate,
                       @Named("endDate") String endDate,
                       @Named("owner") String owner) throws ParseException {

        logger.info("create: name=" + name + " owner=" + owner);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        batchService.createBatch(name, generateCodesCount, dateFormat.parse(startDate), dateFormat.parse(endDate), owner);
    }*/

}
