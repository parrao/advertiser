package com.iheart.ms.service;

import java.util.Collection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iheart.ms.exceptions.EntityExistsException;
import com.iheart.ms.exceptions.NoResultException;
import com.iheart.ms.model.Advertiser;
import com.iheart.ms.repository.AdvertiserRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
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
	public Advertiser findOne(Long id) {
		
		Advertiser adv=advertiserRepository.findById(id);
		return adv;
	}

	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
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
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public void update(Advertiser advertiser) {
		logger.info("> update id:{}", advertiser.getId());
		
		Advertiser advertiserToUpdate = findOne(advertiser.getId());
        if (advertiserToUpdate == null) {
            // Cannot update Advertiser that hasn't been persisted
            logger.error(
                    "Attempted to update a 	Advertiser, but the entity does not exist.");
            throw new NoResultException("Requested for update - Advertiser not found.");
        }

        advertiserToUpdate.setContactName(advertiser.getContactName());
        advertiserToUpdate.setCreditLimit(advertiser.getCreditLimit());
        int result = advertiserRepository.update(advertiserToUpdate);
      
         logger.info("< update id:{}", advertiserToUpdate.getId());

      
        
	}

	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public void delete(Long id) {
		
		logger.info("delete id:{}", id);
		
		Advertiser advertiserToDelete = findOne(id);
        if (advertiserToDelete == null) {
            // Cannot update Advertiser that hasn't been persisted
            logger.error(
                    "Attempted to delete a 	Advertiser, but the record does not exist.");
            throw new NoResultException("Requested for delete - Advertiser not found.");
        }
		int result = advertiserRepository.deleteById(id);
		

	}

	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = true)
	public boolean validateCredit(Long id,Long transAmount) {
		
		logger.info("Validate Advertiser credit - id:{}", id);
		
		Advertiser advertiserToValidate = findOne(id);
        if (advertiserToValidate == null) {
            
            logger.error(
                    "Attempted to validate  a Advertiser credit, but the record does not exist.");
            throw new NoResultException("Requested for Credit validation - Advertiser not found.");
        }
		
        Long creditLimit=advertiserToValidate.getCreditLimit()-transAmount;
        if(creditLimit>0) {
        	return true;
        }else {
        	return false;
        }
        
		
	}

	
	
}
