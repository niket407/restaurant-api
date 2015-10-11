package restaurant.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api")
public class restapp extends ResourceConfig {

public restapp(){
			packages("restaurant.customer.contoller");
			
			register(io.swagger.jaxrs.listing.ApiListingResource.class);
	        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
			
			BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0.2");
	        beanConfig.setSchemes(new String[]{"http"});
	        beanConfig.setBasePath("/restaurant-api/api");
	        beanConfig.setResourcePackage("restaurant.customer.contoller");
	        beanConfig.setTitle("RESTapi Documentation");
	        beanConfig.setDescription("REST api for the app");
	        beanConfig.setScan(true);
		}
	}