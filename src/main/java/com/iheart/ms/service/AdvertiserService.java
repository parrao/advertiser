package com.iheart.ms.service;

import java.util.Collection;

import com.iheart.ms.model.Advertiser;



public interface AdvertiserService {
	
	
	  /**
     * Find all Advertiser entities.
     * @return A Collection of Advertiser objects.
     */
    Collection<Advertiser> findAll();

    /**
     * Find a single Advertiser entity by primary key identifier.
     * @param id A Long primary key identifier.
     * @return A Advertiser or <code>null</code> if none found.
     */
    Advertiser findOne(Long id);

    /**
     * Persists a Advertiser entity in the data store.
     * @param advertiser A Advertiser object to be persisted.
     * @return The persisted Advertiser entity.
     */
    Advertiser create(Advertiser advertiser);

    /**
     * Updates a previously persisted Advertiser entity in the data store.
     * @param advertiser A Advertiser object to be updated.
     * @return The updated Advertiser entity.
     */
    void update(Advertiser advertiser);

    /**
     * Removes a previously persisted Advertiser entity from the data store.
     * @param id A Long primary key identifier.
     */
     void delete(Long id);



}
