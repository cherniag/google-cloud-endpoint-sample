package com.mq.gae.voucher.admin.api.batches;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/23/2015
 */
public class BatchService {

    private static final String KIND = "Batch";
    private EntityService entityService = new EntityService();

    public void createBatch(String name, int generateCodesCount, Date startDate, Date endDate, String owner) {
        Entity batch = new Entity(KIND);
        batch.setProperty("name", name);
        batch.setProperty("generateCodesCount", generateCodesCount);
        batch.setProperty("createDate", new Date());
        batch.setProperty("startDate", startDate);
        batch.setProperty("endDate", endDate);
        batch.setProperty("owner", owner);
        entityService.create(batch);
    }


    public Batch findOne(Long id) throws EntityNotFoundException {
        Entity one = entityService.findOne(KIND, id);

        return convert(one);
    }

    public List<Batch> findAll() throws EntityNotFoundException {
        List<Entity> all = entityService.findAll(KIND, "createDate", Query.SortDirection.ASCENDING);
        List<Batch> batches = new ArrayList<Batch>();
        for (Entity entity : all) {
            batches.add(convert(entity));
        }
        return batches;
    }

    private Batch convert(Entity one) {
        return new Batch(
                one.getKey().getId(),
                (String)one.getProperty("name"),
                (Long)one.getProperty("generateCodesCount"),
                (Date)one.getProperty("createDate"),
                (Date)one.getProperty("startDate"),
                (Date)one.getProperty("endDate"),
                (String)one.getProperty("owner")
        );
    }

}
