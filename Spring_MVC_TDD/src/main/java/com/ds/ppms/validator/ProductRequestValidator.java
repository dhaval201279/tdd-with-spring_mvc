/**
 * 
 */
package com.ds.ppms.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @author Dhaval P Shah
 *
 */
@Service
public class ProductRequestValidator {

	/*public boolean validateRequest( HttpServletRequest request ) {
		
		if ( StringUtils.isNotBlank( request.getParameter( "q" ) ) && StringUtils.isNotEmpty( "q" ) ) {
			return Boolean.TRUE ;
		}
		return Boolean.FALSE ;
	}*/
	
	public boolean validateRequest( String keyword ) {
		
		if ( StringUtils.isNotBlank( keyword ) && StringUtils.isNotEmpty( keyword ) ) {
			return Boolean.TRUE ;
		}
		return Boolean.FALSE ;
	}

}
