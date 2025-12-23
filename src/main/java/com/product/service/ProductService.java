package com.product.service;


import java.util.List;

import com.product.entity.Product;

public interface ProductService 
{

	void saveProduct(Product product);

	List<Product> getProductList();

	Product getProduct(int pid);

	void deleteProduct(int pid);

	void updateProduct(Product updateProduct);

	boolean productExist(int pid);

}
