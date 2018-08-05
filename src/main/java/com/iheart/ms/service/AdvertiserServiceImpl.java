package com.iheart.ms.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iheart.ms.exceptions.EntityExistsException;
import com.iheart.ms.model.Advertiser;
import com.iheart.ms.repository.AdvertiserRepository;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	  /**
     * The Mybatis Data repository for Advertiser entities.
     */
    @Autowired
    private AdvertiserRepository advertiserRepository;
	
	@Override
	public Collection<Advertiser> findAll() {
		
		Collection<Advertiser> adList=	advertiserRepository.findAll();
		
		return adList;
	}

	@Override
	public Advertiser findOne(int id) {
		
		Advertiser adv=advertiserRepository.findById(id);
		return adv;
	}

	@Override
	public Advertiser create(Advertiser advertiser) {


        // Ensure the advertiser object to be created does NOT exist in the
        // Database. 
        if (advertiser.getId() != null) {
            // Cannot create advertiser with specified ID value
            logger.error("Attempted to create a advertiser, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new record.");
        }

        advertiserRepository.insert(advertiser);
        logger.info("< create");
        return advertiser;
		
	}

	@Override
	public Advertiser update(Advertiser advertiser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
