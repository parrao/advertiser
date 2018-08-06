package com.iheart.ms;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheart.ms.web.api.BaseController;

/**
 * This class extends the functionality of AbstractMediaServicesApplicationTests. AbstractMediaServicesControllerTest
 * is the parent of all controller unit test classes. The class ensures that
 * a type of WebApplicationContext is built and prepares a MockMvc instance for
 * use in test methods.
 * 
 * Maps a String of JSON into an instance.
 * 
 * Maps an Object into a JSON String
 * 
 * @author Parthi
 */

@WebAppConfiguration
public abstract class AbstractMediaServicesControllerTest extends AbstractMediaServicesApplicationTests {
	
	protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;
    
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected void setUp(BaseController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }


}
