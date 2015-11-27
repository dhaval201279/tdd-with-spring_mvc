/**
 * 
 */
package com.ds.ppms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ds.ppms.validator.ProductRequestValidator;


/**
 * @author Dhaval P Shah
 *
 */
@RunWith( SpringJUnit4ClassRunner.class )
//@RunWith( MockitoJUnitRunner.class )
@ContextHierarchy ( {
	@ContextConfiguration( locations = "classpath:test-service-context.xml"),
	@ContextConfiguration( locations = "classpath:test-servlet-context.xml")
} )
@WebAppConfiguration
public class ProductControllerTest {
	
	@Autowired
	WebApplicationContext wac ;
	MockMvc mockMvc ;
	
	@Mock
	ProductRequestValidator mockRequestValidator ;
	
	
	//@InjectMocks
	@Autowired
	ProductController productController ;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks( this ) ;
		this.mockMvc = MockMvcBuilders.webAppContextSetup( this.wac ).build() ;
		
		/* Both the below lines are not required as it will be achieved by autowire, component-scan and annotations */
		//productController = new ProductController() ;
		//productController.setProductRequestValidator( mockRequestValidator ) ;
		
	}

	//@Test
	public void when_search_keyword_is_null_or_empty_return_error_message() {
		ProductController productController = new ProductController();
		String searchKeyword = "" ;
		//productController.search( searchKeyword ) ;
	}
	
	@Test
	public void search_products_with_valid_string() throws Exception {
		
		String searchKeyword = "Very Nice Shoes" ;
		
		Mockito.when( mockRequestValidator.validateRequest( Mockito.anyString() ) ).thenReturn( Boolean.TRUE ) ;
		
		mockMvc.perform( get( "/product/search" )
				.param( "q", searchKeyword )
				.accept( MediaType.APPLICATION_JSON ) )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
			.andExpect( jsonPath( "$.products[0].name" ).value( searchKeyword ) ) ;
		//productController.search( searchKeyword ) ;
	}
	
	@Test
	public void test_search_product_by_name_not_found() throws Exception {
		ProductController productController = new ProductController();
		String searchKeyword = "Soft Shoes" ;
		String infoText = String.format( "Could not find product(s) matching : '%s' ", searchKeyword ) ;
		
		Mockito.when( mockRequestValidator.validateRequest( Mockito.anyString() ) ).thenReturn( Boolean.TRUE ) ;
		
		mockMvc.perform( get( "/product/search" )
				.param( "q", searchKeyword )
				.accept( MediaType.APPLICATION_JSON ) )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
			.andExpect( jsonPath( "$.infoText" ).value( infoText) ) ;
		//productController.search( searchKeyword ) ;
	}

}
