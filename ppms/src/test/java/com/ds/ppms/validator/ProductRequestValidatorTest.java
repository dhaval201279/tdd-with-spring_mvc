/**
 * 
 */
package com.ds.ppms.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Dhaval P Shah
 *
 */
public class ProductRequestValidatorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidateRequestWithEmptyVaue() {
		ProductRequestValidator productRequestValidator = new ProductRequestValidator();
		String keyword = "" ;
		assertFalse( productRequestValidator.validateRequest( keyword ) ) ;
	}
	
	@Test
	public void testValidateRequestWithSomeVaue() {
		ProductRequestValidator productRequestValidator = new ProductRequestValidator();
		String keyword = "abc" ;
		assertTrue( productRequestValidator.validateRequest( keyword ) ) ;
	}

}
