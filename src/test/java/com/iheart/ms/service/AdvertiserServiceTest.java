package com.iheart.ms.service;


import java.util.Collection;



import com.iheart.ms.exceptions.EntityExistsException;
import com.iheart.ms.exceptions.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.iheart.ms.AbstractMediaServicesApplicationTests;
import com.iheart.ms.model.Advertiser;

/**
 * Unit test methods for the AdvertiserService and AsvertiserServiceImpl.
 * 
 * @author Parthi
 */
@Transactional
public class AdvertiserServiceTest extends AbstractMediaServicesApplicationTests {
	
	@Autowired
    private AdvertiserService adService;
	
	 @Test
	    public void findAllTest() {

	        Collection<Advertiser> list = adService.findAll();

	        Assert.assertNotNull("failure - expected not null", list);
	        //Assert.assertEquals("failure - expected list size", 2, list.size());

	    }
	 
	 @Test
	    public void findOneTest() {

		 Long id = new Long(1);

	        Advertiser adEntity = adService.findOne(id);

	        Assert.assertNotNull("failure - expected not null", adEntity);
	        Assert.assertEquals("failure - expected id attribute match", id,
	        		adEntity.getId());

	    }

	    @Test
	    public void findOneNotFoundTest() {

	    	Long id = Long.MAX_VALUE;

	        Advertiser adEntity = adService.findOne(id);

	        Assert.assertNull("failure - expected null", adEntity);

	    }
	    
	    @Test
	    public void createAdTest() {

	    	Advertiser adEntity = new Advertiser();
	    	adEntity.setContactName("TestAd");
	    	adEntity.setCreditLimit(10000L);

	    	Advertiser createdAd = adService.create(adEntity);

	        Assert.assertNotNull("failure - expected not null", createdAd);
	        Assert.assertNotNull("failure - expected id attribute not null",createdAd.getId());
	        
	        Assert.assertEquals("failure - expected text attribute match", "TestAd", createdAd.getContactName());
	        Assert.assertEquals("failure - expected text attribute match", Long.valueOf("10000"),createdAd.getCreditLimit());

	        //Collection<Advertiser> list = adService.findAll();

	        //Assert.assertEquals("failure - expected size", 3, list.size());

	    }
	    
	    @Test
	    public void createWithIdTest() {

	        Exception exception = null;

	        Advertiser adEntity = new Advertiser();
	        adEntity.setId(Long.MAX_VALUE);
	        adEntity.setContactName("TestAd");
	    	adEntity.setCreditLimit(10000L);

	        try {
	        	adService.create(adEntity);
	        } catch (EntityExistsException e) {
	            exception = e;
	        }

	        Assert.assertNotNull("failure - expected exception", exception);
	        Assert.assertTrue("failure - expected EntityExistsException", exception instanceof EntityExistsException);

	    }
	    
	    
	    
	    @Test
	    public void updateTest() {

	    	Long id = new Long(1);

	    	Advertiser adEntity = adService.findOne(id);

	        Assert.assertNotNull("failure - expected not null", adEntity);
	        
	        
	        String nameChange = adEntity.getContactName() + "-Test";
	        adEntity.setContactName(nameChange);
	        
	        Long updatedCredit = adEntity.getCreditLimit() + 5000L;
	        adEntity.setCreditLimit(updatedCredit);
	        
	        adService.update(adEntity);
	      
	        
	        
	        Assert.assertEquals("failure - expected id attribute match", id,
	        		adEntity.getId());
	        Assert.assertEquals("failure - expected text attribute match",
	        		nameChange, adEntity.getContactName());
	        Assert.assertEquals("failure - expected text attribute match",
	        		updatedCredit, adEntity.getCreditLimit());

	    }

	    @Test
	    public void updateNotFoundTest() {

	        Exception exception = null;

	        Advertiser adEntity = new Advertiser();
	        adEntity.setId(Long.MAX_VALUE);
	        adEntity.setContactName("AdTest");
	        adEntity.setCreditLimit(0L);

	        try {
	        	adService.update(adEntity);
	        } catch (NoResultException e) {
	            exception = e;
	        }

	        Assert.assertNotNull("failure - expected exception", exception);
	        Assert.assertTrue("failure - expected NoResultException",exception instanceof NoResultException);

	    }

	    @Test
	    public void deleteTest() {

	        Long id = new Long(1);

	        Advertiser adEntity = adService.findOne(id);

	        Assert.assertNotNull("failure - expected not null", adEntity);

	        adService.delete(id);

	       // Collection<Advertiser> list = adService.findAll();

	       // Assert.assertEquals("failure - expected size", 2, list.size());

	        Advertiser deletedEntity = adService.findOne(id);

	        Assert.assertNull("failure - expected null", deletedEntity);

	    }

}
