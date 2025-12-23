package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.product.entity.Product;
import com.product.service.ProductService;

@Controller
public class ProductController 
{
	@Autowired
	private ProductService service;	
   @GetMapping("/insertForm")
   public String insertForm(@ModelAttribute Product product)
   {
	   return "insert-form";
   }
   @PostMapping("/insertProduct")
   public String insertProduct(@ModelAttribute Product product ,Model model)
   {
	   int pid=product.getPid();
	   if(service.productExist(pid))
	   {
		   model.addAttribute("product",product);
		   model.addAttribute("msg", "product with id "+pid+" already exist");		   
		   return "insert-form";
	   }
	   service.saveProduct(product);
	   return "insert-success";
   }
   @GetMapping("/productList")
   public String productList(Model model)
   {
	  
	   List<Product> list=service.getProductList();
	   model.addAttribute("product",list);
	   return "product-list";
   }
   @GetMapping("/searchForm")
   public  String Serach()
   {
	   return "search-form";
   }
   @GetMapping("/searchRecord")
   public ModelAndView showRecord(int pid)
   {
	   Product product=service.getProduct(pid);
	   ModelAndView  model=new ModelAndView();
	   if(product==null)
	   {
		   model.addObject("msg","Product Does Not Exist");
		   model.addObject("pid",pid);
		   model.setViewName("search-form");
		   return model;
	   }
	   model.addObject("product",product);
	   model.setViewName("show-record");
	   return model;
	   
   }
   @GetMapping("/deleteForm")
    public String delete()
    {
	   return "delete-form";
    }
   @GetMapping("/confirmDelete")
   public String confirmDelete(int pid,Model model)
   {
	   Product product=service.getProduct(pid);
	  
	   if(product==null)
	   {
		   model.addAttribute("msg","Product Does Not Exist");
		   model.addAttribute("pid",pid);
		   return "delete-form";
	   }
	   model.addAttribute("product",product);
	   return "confirm-delete";
   }
   @GetMapping("/deleteSuccess")
   public String deleteSuccess(int pid ,Model model)
   {
	   System.out.println("Hello"+pid);
	   service.deleteProduct(pid);
	   model.addAttribute("pid", pid);
	   return "delete-success";
   }
   @GetMapping("/editForm")
   public String editForm()
   {
	   return "edit-form";
   }
   @GetMapping("/updateRecord")
   public String updateproduct(int pid,Model model)
   {
	   Product product=service.getProduct(pid);  
	   if(product==null)
	   {
		   model.addAttribute("msg","Product does not exist");
		   model.addAttribute("pid",pid);
		   return "edit-form";
	   }
	   model.addAttribute("product",product);
	   return "update-record-form";
   }
   @PostMapping("/updateSuccess")
   public String updateSuccess(Product updateProduct,Model model)
   {
	   service.updateProduct(updateProduct);
	   model.addAttribute("pid",updateProduct.getPid());
	   return "update-success";
   }
  
}
