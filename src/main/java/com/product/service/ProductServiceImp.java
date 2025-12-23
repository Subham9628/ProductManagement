package com.product.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService
{
	@Autowired
    private ProductRepository repo;
   
	@Override
	public void saveProduct(Product product) 
	{
		repo.save(product);
		
	}
	@Override
	public List<Product> getProductList() 
	{
		List<Product> list=repo.findAll();
		return list;
	}
	@Override
	public Product getProduct(int pid) 
	{
		Product product=repo.findById(pid).orElse(null);
		return product;
		
	}
	@Override
	public void deleteProduct(int pid) 
	{
		repo.deleteById(pid);
	}
	@Override
	public void updateProduct(Product updateProduct) 
	{
		repo.save(updateProduct);		
	}
	@Override
	public boolean productExist(int pid) 
	{
		return repo.existsById(pid);
	}
}
