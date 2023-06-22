package com.aurosoft.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.service.BrandService;
import com.aurosoft.ecommerce.service.CategoryService;
import com.aurosoft.ecommerce.service.ProductService;
import com.aurosoft.ecommerce.util.FileUploadUtil;

@Controller
@RequestMapping("/product")
public class ProductController {

private	ProductService productService;
private	CategoryService categoryService;
private BrandService brandService;

	

	public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
	super();
	this.productService = productService;
	this.categoryService = categoryService;
	this.brandService = brandService;
}

	@GetMapping("/list")
	public  String listproduct(Model m)
	{
		List<Product> list = productService.listAllProducts();
		m.addAttribute("list",list);
		
		return "/admin/product-list";
	}
	
	@GetMapping("/new")
	public String showForm(Product product, Model m)
	{
		List<Category> list= categoryService.listAllCategorys();
		m.addAttribute("listcategory",list );
		
		
		List<Brand> list1= brandService.listAllBrands();
		m.addAttribute("listBrand",list1 );
		
		return "/admin/product-add";
	}
	
@PostMapping("/insert")
	
	public String insert(Product product ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	product.setImage(fileName);
     
	
    Product savedProduct = productService.insertProduct(product);

    String uploadDir = "./product-photos/" + savedProduct.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	   product.setImage("noimg.png");
 	   Product savedProduct = productService.insertProduct(product);
 	   
    }
    
    return "redirect:/product/list";
   
	}
	
	@GetMapping(value="/edit/{id}")
	public String editcategory(@PathVariable int id,Model m)
	{
		
	Product product = productService.getProductById(id);
	m.addAttribute("product", product);
	
	List<Category> list= categoryService.listAllCategorys();
	m.addAttribute("listcategory",list );
	
	List<Brand> list1= brandService.listAllBrands();
	m.addAttribute("listBrand",list1 );
	
	return "admin/product-edit";
	}
	
	
	@PostMapping(value="/update")
	public String update(Product product ,@RequestParam("image1") MultipartFile multipartFile) throws IOException
	{
	
	
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
   
	
	
	if(fileName.length() > 3 ) {
	product.setImage(fileName);
     
	
    Product savedProduct = productService.updateProduct(product);

    String uploadDir = "./product-photos/" + savedProduct.getId();

    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} 
    else
    {
 	  // product.setImage("noimg.png");
 	   Product savedProduct = productService.updateProduct(product);
    }
    
    return "redirect:/product/list";
   
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String deletecategory(@PathVariable int id, Model m)
	
	{
		productService.deleteProduct(id);
		return "redirect:/product/list";
	}
	
}
