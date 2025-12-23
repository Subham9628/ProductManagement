package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product 
{
  @Id
  private int pid;
  private  String name;
  private String brand;
  private String price;
  public int getPid() {
	return pid;
  }
  public void setPid(int pid) {
	this.pid = pid;
  }
  public String getName() {
	return name;
  }
  public void setName(String name) {
	this.name = name;
  }
  public String getBrand() {
	return brand;
  }
  public void setBrand(String brand) {
	this.brand = brand;
  }
  public String getPrice() {
	return price;
  }
  public void setPrice(String price) {
	this.price = price;
  }
  
  
}
