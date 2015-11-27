/**
 * 
 */
package com.ds.ppms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ds.ppms.entity.Product;
import com.ds.ppms.service.ProductService;
import com.ds.ppms.validator.ProductRequestValidator;

/**
 * @author Dhaval P Shah
 * 
 */
@Controller
public class ProductController {

	final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRequestValidator productRequestValidator ;
	
	@Autowired
	private ProductService productService ;

	/**
	 * 
	 */
	public ProductController() {
		System.out.println("In ProductController");
	}

	@RequestMapping(value = "/product/search", method = RequestMethod.GET)
	public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType( MediaType.APPLICATION_JSON_VALUE );

		if ( productRequestValidator.validateRequest( request.getParameter("q" ) ) ) {
			String searchKeyword = request.getParameter("q");
			String infoText = String.format( "Could not find product(s) matching : '%s' ", searchKeyword );

			logger.info(" Product searched with : " + StringUtils.stripToEmpty( searchKeyword ) );

			/*if ("Very Nice Shoes".equals(searchKeyword)) {
				response.getWriter().write("{\"name\":\"Very Nice Shoes\"}");
			} else {
				response.getWriter().write(
						"{\"infoText\":\"" + infoText + "\"}");
			}
			response.getWriter().write("{\"name\":\"Very Nice Shoes\"}");*/
			
			List < Product > matchedProducts = productService.findByName( searchKeyword ) ;
			
			
			 /* generate the response */
		    StringBuffer jsonBuffer = new StringBuffer ();
		    if( ! matchedProducts.isEmpty() ) {
		    	logger.info( "List of searched products : " + new ArrayList < Product > ( matchedProducts ) ) ;
		    	jsonBuffer.append( "{\"status\":\"found\", \"products\": [" ) ;

		    	for( Product product: matchedProducts ) {
		    		String productJson = String.format( "{\"name\": \"%s\", \"description\":\"%s\"}", product.getName(), product.getDescription() );
		      		//jsonBuffer.append( productJson + "," ) ;
		    		jsonBuffer.append( productJson ) ;
		    	}
		    	jsonBuffer.append( "]}" ) ;
		    	logger.info(" json response : " +  jsonBuffer );
		    	response.getWriter().write( jsonBuffer.toString() ) ;
		    }
		}
	}
}
