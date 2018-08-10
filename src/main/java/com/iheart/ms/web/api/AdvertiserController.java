package com.iheart.ms.web.api;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iheart.ms.model.Advertiser;
import com.iheart.ms.service.AdvertiserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


	/**
	 * Advertiser Controller class for Advertiser services endpoints
	 *  @author Parthi
	 */
	@Api(value="Advertiser Web Service Endpoint", description="Operations pertaining to Advertiser in iHeart Media Services")
	@RestController
	public class AdvertiserController extends BaseController {
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
    @ApiOperation(value = "View a list of available Advertiser",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
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
    @ApiOperation(value = "Search for a particular Advertiser by Id",response = Advertiser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Advertiser info"),
            @ApiResponse(code = 404, message = "The Advertiser you were trying to reach is not found")
    }
    )
    @RequestMapping(
            value = "/api/advertiser/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertiser> getAdvertiser(@PathVariable("id") Long id) {
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
    @ApiOperation(value = "Create New Advertiser",response = Advertiser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Advertiser Resources"),
            @ApiResponse(code = 500, message = "The id attribute must be null to persist a new record.")
    }
    )
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
    @ApiOperation(value = "Update Advertiser",response = Advertiser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update Advertiser resource and retrive the updated Advertiser info"),
            @ApiResponse(code = 404, message = "The Advertiser you were trying to update is not found")
    }
    )
    @RequestMapping(
            value = "/api/advertiser/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertiser> updateAdvertiser(
            @RequestBody Advertiser advertiser) {
        logger.info("> updateAdvertiser id:{}", advertiser.getId());

         advertiserService.update(advertiser);

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
    @ApiOperation(value = "Delete Advertiser",response = Advertiser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete Advertiser resource"),
            @ApiResponse(code = 404, message = "The Advertiser you were trying to delete is not found")
    }
    )
    @RequestMapping(
            value = "/api/advertiser/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Advertiser> deleteAdvertiser(
            @PathVariable("id") Long id) {
        logger.info("> deleteAdvertiser id:{}", id);

         advertiserService.delete(id);
        logger.info("< deleteAdvertiser id:{}", id);
        return new ResponseEntity<Advertiser>(HttpStatus.NO_CONTENT);
    }

    
    
    /**
     * The endpoint to validate the Advertiser credit. The service returns an validCredit true/false response body with
     * HTTP status 200 else the service returns with HTTP status 500.
     * 
     * @param id A Long URL path variable containing the Advertiser primary key
     *        identifier.
     * @param id A Long URL path variable containing the Transaction Amount to validate.
     * @return A ResponseEntity with a enoughCredit true/false response body and a HTTP status
     *         code.
     */
    @ApiOperation(value = "Validate Advertiser Credit",response = Advertiser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully validated Advertiser credit"),
            @ApiResponse(code = 404, message = "The Advertiser you were trying to delete is not found")
    }
    )
    @RequestMapping(
            value = "/api/advertiser/{id}/validatecredit",
            method = RequestMethod.GET)
    public ResponseEntity<Map> validateAdvertiserCredit(
            @PathVariable("id") Long id,@RequestParam("transamount") BigDecimal transAmount) {
    	Map<String,Boolean> response=new HashMap<String,Boolean>();
        
        logger.info("> validateAdvertiserCredit id:{}", id);

         
         response.put("validCredit", advertiserService.validateCredit(id, transAmount));
        return new ResponseEntity<Map>(response,HttpStatus.OK);
    }
    
    
    /**
     * The endpoint to deduct amount from  Advertiser credit. The HTTP request
     * body is expected to contain a Advertiser object in JSON format.
     * 
     * If the amount is deducted successfully, the Advertiser info with updated credit limit is returned  as JSON with
     * HTTP status 200 else the service returns an empty response body
     * with HTTP status 500.
     * 
     * @param advertiser The Advertiser object to be created.
     * @return A ResponseEntity containing a created Advertiser object
     */
    @ApiOperation(value = "Deduct amount from  Advertiser credit",response = Advertiser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "updated Advertiser response"),
            @ApiResponse(code = 500, message = "server error.")
    }
    )
    @RequestMapping(
            value = "/api/advertiser/{id}/deductcredit",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertiser> deductCredit(
    		@PathVariable("id") Long id, @RequestParam(name="amount", required=true) BigDecimal deductAmount) {
        logger.info("> deductCredit");

        Advertiser updatedAdvertiser = advertiserService.deductCredit(id, deductAmount);
        
        logger.info("< deductCredit");
        return new ResponseEntity<Advertiser>(updatedAdvertiser, HttpStatus.OK);
    }
    
   
    
    
    
}
