/**
 * 
 */
package com.ds.ppms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.ds.ppms.entity.Product;

/**
 * @author Dhaval P Shah
 * 
 */
@Service
public class ProductService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Product> findByName(String name) {
		Product product = null;

		List < Product > products = new ArrayList < Product > () ;

/*		if (name.contains("Very Nice Shoes")) {
			product = new Product("Very Nice Shoes");
			product.setDescription("Very Nice Shoes made in Thailand");
			products.add(product);
		}*/

		TypedQuery < Product > namedQuery = entityManager.createNamedQuery( "Product.findByNameContains", Product.class ) ;
		namedQuery.setParameter( "name", "%" + name + "%") ;
		products = namedQuery.getResultList();

		return products ;

	}
}
