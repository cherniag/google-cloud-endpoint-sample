package com.mq.gae.voucher.admin.api.batches;

import com.google.appengine.api.datastore.*;

import java.util.List;

/**
 * Author: Gennadii Cherniaiev
 * Date: 7/23/2015
 */
public class EntityService {

    public void create(Entity entity) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(entity);
    }

    public Entity findOne(String kind, Long id) throws EntityNotFoundException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        return datastore.get(KeyFactory.createKey(kind, id));
    }

    public List<Entity> findAll(String kind, String sortBy, Query.SortDirection sortDirection) throws EntityNotFoundException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(kind);
        if (sortBy != null) {
            q.addSort(sortBy, sortDirection != null ? sortDirection : Query.SortDirection.ASCENDING);
        }
        PreparedQuery pq = datastore.prepare(q);
        return pq.asList(FetchOptions.Builder.withDefaults());
    }

}
