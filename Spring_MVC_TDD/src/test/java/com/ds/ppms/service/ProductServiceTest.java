/**
 * 
 */
package com.ds.ppms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ds.ppms.entity.Product;

/**
 * @author Dhaval P Shah
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml")
public class ProductServiceTest {

	@Autowired
	ProductService productService;

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
	public void testFindByNameContainsNiceShoesFoundOne() {
		String name = "Very Nice Shoes";
		String description = "Very Nice Shoes made in Thailand";
		List < Product > matchedProducts = productService.findByName( name );

		assertTrue(matchedProducts.size() == 1);
		assertEquals(name, matchedProducts.get(0).getName());
		assertEquals(description, matchedProducts.get(0).getDescription());
	}

}
