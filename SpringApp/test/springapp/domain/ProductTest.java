package springapp.domain;

import junit.framework.TestCase;

public class ProductTest extends TestCase{
	 private Product product;
	 protected void setUp() throws Exception{
		 product=new Product();
	 }
	 public void testSetAndGetDescription(){
		 String description="Lime Water";
		 assertNull(product.getDescription());
		 product.setDescription(description);
		 assertEquals(description, product.getDescription());
	 }
	 public void testSetAndGetPrice(){
		 double price=21.21;
		 assertEquals(0.0,product.getPrice(),0);
		 product.setPrice(price);
		 assertEquals(price, product.getPrice(),0);
	 }
}
