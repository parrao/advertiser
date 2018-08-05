package com.iheart.ms.web.api;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iheart.ms.model.Advertiser;
import com.iheart.ms.service.AdvertiserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


	/**
	 * Advertiser Controller class for Advertiser services endpoints
	 *  @author Parthi
	 */
	@RestController
	public class AdvertiserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	 /**
     * The AdvertiserService business service.
     */
    @Autowired
    private AdvertiserService advertiserService;
    
    
    
    
    
    /**
     * Web service endpoint to fetch all Advertiser entities. The service returns
     * the collection of Advertisers entities as JSON.
     * 
     * @return A ResponseEntity containing a Collection of Advertiser objects.
     */
    @RequestMapping(
            value = "/api/advertisers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Advertiser>> getAdvertisers() {
        logger.info("> getAdvertisers");

        Collection<Advertiser> advertisers = advertiserService.findAll();

        logger.info("< getAdvertisers");
        return new ResponseEntity<Collection<Advertiser>>(advertisers,
                HttpStatus.OK);
    }

    
    /**
     * The endpoint to fetch a single Advertiser entity by primary key
     * identifier.
     * If found, the Advertiser is returned as JSON with HTTP status 200 else empty response body with HTTP status
     * 404.
     * 
     * @param id A integer URL path variable containing the Advertiser primary key
     *        identifier.
     * @return A ResponseEntity containing a single Advertiser object
     */
	
    @RequestMapping(
            value = "/api/advertiser/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertiser> getAdvertiser(@PathVariable("id") int id) {
        logger.info("> getAdvertiser id:{}", id);

        Advertiser advertiser = advertiserService.findOne(id);
        if (advertiser == null) {
            return new ResponseEntity<Advertiser>(HttpStatus.NOT_FOUND);
        }

        logger.info("< getAdvertiser id:{}", id);
        
        return new ResponseEntity<Advertiser>(advertiser, HttpStatus.OK);
    }

    
    /**
     * The endpoint to create a single Advertiser entity. The HTTP request
     * body is expected to contain a Advertiser object in JSON format.
     * 
     * If created successfully, the persisted Advertiser is returned as JSON with
     * HTTP status 201 else the service returns an empty response body
     * with HTTP status 500.
     * 
     * @param advertiser The Advertiser object to be created.
     * @return A ResponseEntity containing a created Advertiser object
     */
    @RequestMapping(
            value = "/api/advertiser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertiser> createAdvertiser(
            @RequestBody Advertiser advertiser) {
        logger.info("> createAdvertiser");

        Advertiser savedAdvertiser = advertiserService.create(advertiser);

        logger.info("< createAdvertiser");
        return new ResponseEntity<Advertiser>(savedAdvertiser, HttpStatus.CREATED);
    }
    
    /**
     * The endpoint to update a single Advertiser entity. The HTTP request
     * body is expected to contain a Advertiser object in JSON format. 
     * 
     * If updated successfully, the updated Advertiser is returned as JSON with
     * HTTP status 200 else the service returns an empty response body and HTTP status
     * 404.
     * 
     * If not updated successfully, the service returns an empty response body
     * with HTTP status 500.
     * 
     * @param advertiser The Advertiser object to be updated.
     * @return A ResponseEntity containing a updated Advertiser object.
     */
    @RequestMapping(
            value = "/api/advertiser",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertiser> updateAdvertiser(
            @RequestBody Advertiser advertiser) {
        logger.info("> updateAdvertiser id:{}", advertiser.getId());

        boolean result = advertiserService.update(advertiser);
        if (result == false) {
            return new ResponseEntity<Advertiser>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< updateAdvertiser id:{}", advertiser.getId());
        return new ResponseEntity<Advertiser>(advertiser, HttpStatus.OK);
    }

    /**
     * The endpoint to delete a  Advertiser entity. If deleted successfully, the service returns an empty response body with
     * HTTP status 204 else the service returns with HTTP status 500.
     * 
     * @param id A Integer URL path variable containing the Advertiser primary key
     *        identifier.
     * @return A ResponseEntity with an empty response body and a HTTP status
     *         code.
     */
    @RequestMapping(
            value = "/api/advertiser/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Advertiser> deleteAdvertiser(
            @PathVariable("id") int id) {
        logger.info("> deleteAdvertiser id:{}", id);

        boolean result= advertiserService.delete(id);
        if(result==false) {
        	 return new ResponseEntity<Advertiser>(
                     HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("< deleteAdvertiser id:{}", id);
        return new ResponseEntity<Advertiser>(HttpStatus.NO_CONTENT);
    }

}
