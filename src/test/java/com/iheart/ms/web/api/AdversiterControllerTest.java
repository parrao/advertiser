package com.iheart.ms.web.api;

import java.math.BigDecimal;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.iheart.ms.AbstractMediaServicesControllerTest;
import com.iheart.ms.exceptions.EntityExistsException;
import com.iheart.ms.exceptions.NoResultException;
import com.iheart.ms.model.Advertiser;
import com.iheart.ms.service.AdvertiserService;




@Transactional
public class AdversiterControllerTest extends AbstractMediaServicesControllerTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	  @Autowired
	    private AdvertiserService advertiserService;

	    @Before
	    public void setUp() {
	        super.setUp();
	    }

	    @Test
	    public void getAdvertisersTest() throws Exception {

	        String uri = "/api/advertisers";

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);

	    }
	    
	    
	    @Test
	    public void getAdvertiserByIdTest() throws Exception {

	        String uri = "/api/advertiser/{id}";
	        Long id = new Long(1);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);

	    }
	    
	    @Test
	    public void getAdvertiserNotFoundTest() throws Exception {

	        String uri = "/api/advertiser/{id}";
	        Long id = Long.MAX_VALUE;

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	        Assert.assertTrue("failure - expected HTTP response body to be empty",
	                content.trim().length() == 0);

	    }
	    
	    @Test
	    public void createAdvertiserTest() throws Exception {

	        String uri = "/api/advertiser";
	        Advertiser adEntity = new Advertiser();
	        adEntity.setContactName("CTestAd");
	    	adEntity.setCreditLimit(new BigDecimal(10000.00));
	    	
	        String inputJson = super.mapToJson(adEntity);

	        MvcResult result = mvc
	                .perform(MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);

	        Advertiser createdAdvertiser = super.mapFromJson(content, Advertiser.class);

	        Assert.assertNotNull("failure - expected adversiter not null",createdAdvertiser);
	        Assert.assertNotNull("failure - expected adversiter.id not null",createdAdvertiser.getId());
	        Assert.assertEquals("failure - expected adversiter.ContactName match", "CTestAd",createdAdvertiser.getContactName());
	        Assert.assertEquals("failure - expected adversiter.CreditLimit match", new BigDecimal(10000.00),createdAdvertiser.getCreditLimit());

	    }
	    
	    @Test
	    public void createAdvertiserWithIdTest() throws Exception {
	    	Exception exception = null;
	        String uri = "/api/advertiser";
	        Advertiser adEntity = new Advertiser();
	        adEntity.setId(1L);
	        adEntity.setContactName("CTestAd");
	    	adEntity.setCreditLimit(new BigDecimal(10000.00));
	    	
	        String inputJson = super.mapToJson(adEntity);
	        MvcResult result =null;
	        try {
	        	result = mvc
	                .perform(MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();
	    } catch (EntityExistsException e) {
        	logger.info("Exception is updateNonExitsAdvertiserTest................................");
            exception = e;
        }
	      
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 500", 500, status);
	       

	    }
	    
	    
	    @Test
	    public void updateAdvertiserTest() throws Exception {

	        String uri = "/api/advertiser/{id}";
	        Long id = new Long(1);
	        Advertiser adEntity = advertiserService.findOne(id);
	        
	        String nameChange = adEntity.getContactName() + "-CTest";
	        adEntity.setContactName(nameChange);
	        
	        BigDecimal updatedCredit = adEntity.getCreditLimit().add(new BigDecimal(5000.00));
	        adEntity.setCreditLimit(updatedCredit);
	        
	        String inputJson = super.mapToJson(adEntity);

	        MvcResult result = mvc
	                .perform(MockMvcRequestBuilders.put(uri, id)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);

	        Advertiser updatedAdvertiser = super.mapFromJson(content, Advertiser.class);

	        Assert.assertNotNull("failure - expected advertiser not null",
	        		updatedAdvertiser);
	        Assert.assertEquals("failure - expected advertiser.id unchanged",
	        		adEntity.getId(), updatedAdvertiser.getId());
	        Assert.assertEquals("failure - expected updated advertiser contact name match",
	        		nameChange, updatedAdvertiser.getContactName());
	        Assert.assertEquals("failure - expected updated advertiser credit limit match",
	        		updatedCredit, updatedAdvertiser.getCreditLimit());

	    }
	       
	    @Test
	    public void updateNonExitsAdvertiserTest() throws Exception {
	    	 Exception exception = null;
	        String uri = "/api/advertiser/{id}";
	        Long id = new Long(Long.MAX_VALUE);
	        Advertiser adEntity = new Advertiser(Long.MAX_VALUE,"Janu",new BigDecimal(1000.00));
	        
	        
	        String inputJson = super.mapToJson(adEntity);
	        MvcResult result=null;
	        try {
	        	  result = mvc
	 	                .perform(MockMvcRequestBuilders.put(uri, id)
	 	                        .contentType(MediaType.APPLICATION_JSON)
	 	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	 	                .andReturn();
	        	  logger.info("Result :"+result);
	        } catch (NoResultException e) {
	        	logger.info("Exception is updateNonExitsAdvertiserTest................................");
	            exception = e;
	        }
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 500", 404, status);
	        

	    }

	    
	    
	    

	    @Test
	    public void deleteAdvertiserTest() throws Exception {

	        String uri = "/api/advertiser/{id}";
	        Long id = new Long(1);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 204", 204, status);
	        Assert.assertTrue("failure - expected HTTP response body to be empty",
	                content.trim().length() == 0);

	        Advertiser deletedAdvertiser = advertiserService.findOne(id);

	        Assert.assertNull("failure - expected advertiser to be null",
	        		deletedAdvertiser);

	    }
	    
	    @Test
	    public void deleteNonExitsAdvertiserTest() throws Exception {

	        String uri = "/api/advertiser/{id}";
	        Long id = new Long(Long.MAX_VALUE);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	      //  String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);

	    }
	    
	    
	    @Test
	    public void validateAdvertiserCreditTest() throws Exception {

	        String uri = "/api/advertiser/{id}/validatecredit";
	        Long id = new Long(1);
	        BigDecimal transAmt = new BigDecimal(5000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).param("transamount", transAmt.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	         JSONObject respJObj=new JSONObject(content);
	         
           
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue("failure - expected HTTP response with ValidCredit true",Boolean.valueOf(respJObj.getString("validCredit")));

	    }
	    
	    @Test
	    public void validateNonExitsAdvertiserTest() throws Exception {

	        String uri = "/api/advertiser/{id}/validatecredit";
	        Long id = new Long(Long.MAX_VALUE);
	        BigDecimal transAmt = new BigDecimal(5000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).param("transamount", transAmt.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
           
	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	       
	    }

	    @Test
	    public void inValidAdvertiserCreditTest() throws Exception {

	        String uri = "/api/advertiser/{id}/validatecredit";
	        Long id = new Long(1);
	        BigDecimal transAmt = new BigDecimal(15000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).param("transamount", transAmt.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	         JSONObject respJObj=new JSONObject(content);
	         
           
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertFalse("failure - expected HTTP response with ValidCredit false",Boolean.valueOf(respJObj.getString("validCredit")));

	       
	    }

	    
	    @Test
	    public void advertiserDeductCreditTest() throws Exception {

	        String uri = "/api/advertiser/{id}/deductcredit";
	        Long id = new Long(1);
	        BigDecimal amount = new BigDecimal(1000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri, id).param("amount", amount.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	         JSONObject respJObj=new JSONObject(content);
	         
           
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	       
	    }
	    
	    @Test
	    public void advertiserDeductCreditFullTest() throws Exception {

	        String uri = "/api/advertiser/{id}/deductcredit";
	        Long id = new Long(1);
	        BigDecimal amount = new BigDecimal(10000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri, id).param("amount", amount.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	         JSONObject respJObj=new JSONObject(content);
	         
           
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	       
	    }
	    
	    @Test
	    public void advertiserDeductOverCreditTest() throws Exception {

	        String uri = "/api/advertiser/{id}/deductcredit";
	        Long id = new Long(1);
	        BigDecimal amount = new BigDecimal(15000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri, id).param("amount", amount.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	         JSONObject respJObj=new JSONObject(content);
	         
           
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	       
	    }
	    
	    @Test
	    public void deductNonExitsAdvertiserTest() throws Exception {

	        String uri = "/api/advertiser/{id}/deductcredit";
	        Long id = new Long(Long.MAX_VALUE);
	        BigDecimal amount = new BigDecimal(5000.00);

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri, id).param("amount", amount.toString())
	                .contentType(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

           
	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	       
	    }
	    
}
