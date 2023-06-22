package com.aurosoft.ecommerce.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aurosoft.ecommerce.entity.Brand;
import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.Category;
import com.aurosoft.ecommerce.entity.Company;
import com.aurosoft.ecommerce.entity.Facility;
import com.aurosoft.ecommerce.entity.Product;
import com.aurosoft.ecommerce.entity.User;
import com.aurosoft.ecommerce.service.BrandService;
import com.aurosoft.ecommerce.service.CartService;
import com.aurosoft.ecommerce.service.CategoryService;
import com.aurosoft.ecommerce.service.CompanyService;
import com.aurosoft.ecommerce.service.FacilityService;
import com.aurosoft.ecommerce.service.ProductService;
import com.aurosoft.ecommerce.service.UserService;
import com.aurosoft.ecommerce.util.Helper;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewController {
@Autowired
private	CategoryService categoryService;
@Autowired
private	ProductService productService;
@Autowired
private BrandService brandService;
@Autowired
private CompanyService companyService;
@Autowired
private FacilityService facilityService;
@Autowired
private CartService cartService;
@Autowired
private UserService userService;




	

	public ViewController(CategoryService categoryService, ProductService productService, BrandService brandService,
		CompanyService companyService, FacilityService facilityService, CartService cartService,
		UserService userService) {
	super();
	this.categoryService = categoryService;
	this.productService = productService;
	this.brandService = brandService;
	this.companyService = companyService;
	this.facilityService = facilityService;
	this.cartService = cartService;
	this.userService = userService;
}

	@GetMapping("/template")
	public String template()
	{
		
		
		return "user/template";
	}
	
	@GetMapping("/dashboard")
	public String dashboard()
	{
		return "admin/dashboard";
	}
	
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String logincheck(@RequestParam("email") String email,
			@RequestParam("password")String password, HttpSession session) 
	{
		User user = userService.findByEmailAndPassword(email, password);
		if(user!=null)
		{
			session.setAttribute("uname", user.getFname()+ "    "+user.getLname());
			session.setAttribute("fname", user.getFname());
			session.setAttribute("uid", user.getId());
			session.setAttribute("urole", user.getRole());
			session.setAttribute("email", user.getEmail());
//			System.out.println("XXXXXXXXXXXXXXXX");
//			System.out.println(user);
//			System.out.println("XXXXXXXXXXXXXXXX");

			
			if(Helper.checkUserRole())
			{
				session.setAttribute("msg", "You are succesfully login..");
				return "redirect:/index";
			}
			else
		{
			session.setAttribute("msg","something went wrong");
			return "redirect:/dashboard";
		}
		}
			else
			{
				session.setAttribute("msg", "Wrong Username or password");
				return "redirect:/login";
			}
		}	
	
	@GetMapping("/index")
	public String index(@RequestParam (value = "category_id",defaultValue = "0")int category_id,
			@RequestParam (value = "del_id",defaultValue = "0")int del_id,HttpSession session, Model m)
	{
		List<Category> list=categoryService.listAllCategorys();
		m.addAttribute("listCategory", list);
		m.addAttribute("count",list.size());
		
		List<Category> list_3=categoryService.listAllCategorys().subList(0,3);
		m.addAttribute("list_3",list_3);
		
		int uid = 0;
	       if(session.getAttribute("uid") != null) {
	           uid = (int) session.getAttribute("uid");
	       }
	        List<Cart> carts  = new ArrayList<>();
	        if(uid >0) {
	            User user1 = userService.getUserById(uid);
	            carts = cartService.getCartByUser(user1);
	        }
	        m.addAttribute("carts",carts);

	        if(del_id > 0)
	        {
	            cartService.deleteCart(del_id);
	        }

	        float total = 0;
	        for(int i=0;i<carts.size();i++)
	        {
	            total += carts.get(i).getQty()*carts.get(i).getProduct().getRate();
	        }
	        m.addAttribute("total", total);

	        int totalProducts = carts.stream().mapToInt(Cart::getQty).sum();
	        m.addAttribute("totalProducts", totalProducts);

		// ----------------------------------------------------------------
		
	        List<Product> productCategoryList  = new ArrayList<>();
	        if(category_id >0) {
	            Category category = categoryService.getCategoryById(category_id);
	            productCategoryList = productService.getProductByCategory(category);
	        } else {
	            productCategoryList = productService.listAllProducts();
	        }
	        m.addAttribute("productList",productCategoryList);
        
        
		List<Product> pro=productService.listAllProducts().subList(0, 3);
		m.addAttribute("proList",pro);

		List<Brand> brand=brandService.listAllBrands();
		m.addAttribute("brandList",brand);
		
		List<Facility> fac=facilityService.listAllFacilities();
		m.addAttribute("facilityList",fac);
		
		List<Company> listC =companyService.listAllCompanys();
		m.addAttribute("listC", listC);
		
		List<Cart> cart = cartService.listAllCarts();
		m.addAttribute("cart",cart);
		
		return "user/index";
	}

	@GetMapping("/shop")
	public String shopForm(@RequestParam (value = "category_id", defaultValue = "0")int category_id, 
						@RequestParam (value = "brand_id",defaultValue = "0")int brand_id,
						@RequestParam(value = "del_id" , defaultValue = "0")int del_id,
						Model m,HttpSession session)
	
	{
		List<Product> productList  = new ArrayList<>();
        if(category_id > 0) {
            Category category = categoryService.getCategoryById(category_id);
            productList = productService.getProductByCategory(category);
        }
        else {
            productList = productService.listAllProducts();
        }
        m.addAttribute("productList",productList);
		
		
		List<Brand> brandList = brandService.listAllBrands();
        m.addAttribute("brandList",brandList);

        List<Product> productBrandList  = new ArrayList<>();
        if(brand_id >0) {
            Brand brand= brandService.getBrandById(brand_id);
            productBrandList = productService.getProductByBrand(brand);
        }
        else {
            productBrandList = productService.listAllProducts();
        }
        m.addAttribute("productBrandList",productBrandList);

      //*-------------------------------------template-----------------------------------------------------	
        
		List<Category> list=categoryService.listAllCategorys();
		m.addAttribute("listCategory", list);
		m.addAttribute("count",list.size());
		
		
		List<Category> list_3=categoryService.listAllCategorys().subList(0,3);
		m.addAttribute("list_3",list_3);
		
		List<Product> pro=productService.listAllProducts().subList(0,6);
		m.addAttribute("proList1",pro);
		
		
		List<Brand> brand=brandService.listAllBrands();
		m.addAttribute("brandList",brand);
		
		List<Facility> fac=facilityService.listAllFacilities();
		m.addAttribute("facilityList",fac);
		
		List<Company> listC =companyService.listAllCompanys();
		m.addAttribute("listC", listC);
		
		int uid = 0;
        if(session.getAttribute("uid") != null) {
            uid = (int) session.getAttribute("uid");
        }
        List<Cart> carts  = new ArrayList<>();
        if(uid >0) {
            User user1 = userService.getUserById(uid);
            carts = cartService.getCartByUser(user1);
        }
        m.addAttribute("carts",carts);
		
		if(del_id > 0)
        {
            cartService.deleteCart(del_id);
        }

        float total = 0;
        for(int i=0;i<carts.size();i++)
        {
            total += carts.get(i).getQty()*carts.get(i).getProduct().getRate();
        }
        m.addAttribute("total", total);

        int totalProducts = carts.stream().mapToInt(Cart::getQty).sum();
      m.addAttribute("totalProducts", totalProducts);
//		
		return "user/shop";
	}
	
	@GetMapping("/product")
	
		public String product(@RequestParam int id,Model m)
		{
			
			List<Category> list=categoryService.listAllCategorys();
			m.addAttribute("listCategory", list);
			m.addAttribute("count",list.size());
			
			Product product =productService.getProductById(id);
			m.addAttribute("product",product);
			
			List<Product> pro=productService.listAllProducts().subList(0, 4);
			m.addAttribute("proList",pro);
			
			List<Brand> brand=brandService.listAllBrands();
			m.addAttribute("brandList",brand);
			
			List<Facility> fac=facilityService.listAllFacilities();
			m.addAttribute("facilityList",fac);
			
			List<Company> listC =companyService.listAllCompanys();
			m.addAttribute("listC", listC);
			
			List<Cart> cartList = cartService.listAllCarts();
			m.addAttribute("cartList",cartList);
			
		return "user/product";
	}
	

	@GetMapping("/contact")
	public String contact(Model m)
	{
		
		List<Category> list=categoryService.listAllCategorys();
		m.addAttribute("listCategory", list);
		m.addAttribute("count",list.size());
		
		
		List<Category> list_3=categoryService.listAllCategorys().subList(0,3);
		m.addAttribute("list_3",list_3);
		
		List<Product> pro=productService.listAllProducts().subList(0,6);
		m.addAttribute("proList1",pro);
		
		
		List<Brand> brand=brandService.listAllBrands();
		m.addAttribute("brandList",brand);
		
		List<Facility> fac=facilityService.listAllFacilities();
		m.addAttribute("facilityList",fac);
		
		List<Company> listC =companyService.listAllCompanys();
		m.addAttribute("listC", listC);
		return "user/contact";
	}
	
	@GetMapping("/cart")
	public String cart(@RequestParam(value = "del_id",defaultValue = "0") int del_id,
            @RequestParam(value = "add_id",defaultValue = "0") int add_id,Model m,HttpSession session)
	{
		
		
		if(!Helper.checkUserRole() && !Helper.checkAdminRole()){
            return "redirect:/login ";
        }

        if(add_id > 0)
        {
            Product product = productService.getProductById(add_id);
            int uid = (int)session.getAttribute("uid");
            User user = userService.getUserById(uid);
            Cart cart = new Cart(user,product,1);

            cart.setRate(product.getRate());
            cart.setAmount(product.getRate()*cart.getQty());
            cartService.insertCart(cart);
        }

        if(del_id > 0)
        {
            cartService.deleteCart(del_id);
        }

		 
		List<Cart> listCart = cartService.listAllCarts();
		m.addAttribute("listCart",listCart);
		
		float total = 0;
        for(int i=0;i<listCart.size();i++)
        {
            total += listCart.get(i).getQty()*listCart.get(i).getProduct().getRate();
        }
        m.addAttribute("total", total);
	
	//------------------------tempplate-----------------------------		
		List<Category> list_3=categoryService.listAllCategorys().subList(0,3);
		m.addAttribute("list_3",list_3);
		
		List<Product> pro=productService.listAllProducts().subList(0,9);
		m.addAttribute("proList1",pro);
//		
//		Product product =productService.getProductById(id);
//		m.addAttribute("product",product);
		
		List<Brand> brand=brandService.listAllBrands();
		m.addAttribute("brandList",brand);
		
		List<Facility> fac=facilityService.listAllFacilities();
		m.addAttribute("facilityList",fac);
		
		List<Company> listC =companyService.listAllCompanys();
		m.addAttribute("listC", listC);	
		
		 
		
		return "user/shopping-cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(@RequestParam(value ="del_id", defaultValue = "0")int del_id,
			   HttpSession session,Model m)
	{
		int uid = (int)session.getAttribute("uid");

        List<Cart> listCart  = new ArrayList<>();
        if(uid >0) {
            User user1 = userService.getUserById(uid);
          listCart = cartService.getCartByUser(user1);
        }
        m.addAttribute("carts",listCart);

	
		float total = 0;
        for(int i=0;i<listCart.size();i++)
        {
            total += listCart.get(i).getQty()*listCart.get(i).getProduct().getRate();
        }
        m.addAttribute("total", total);
		
  //-----------------------------template--------------------------  
       
        List<Category> list=categoryService.listAllCategorys();
		m.addAttribute("listCategory", list);
		m.addAttribute("count",list.size());
		
		
		List<Category> list_3=categoryService.listAllCategorys().subList(0,3);
		m.addAttribute("list_3",list_3);
		
		List<Product> pro=productService.listAllProducts().subList(0,6);
		m.addAttribute("proList1",pro);
		
		
		List<Brand> brand=brandService.listAllBrands();
		m.addAttribute("brandList",brand);
		
		List<Facility> fac=facilityService.listAllFacilities();
		m.addAttribute("facilityList",fac);
		
		List<Company> listC =companyService.listAllCompanys();
		m.addAttribute("listC", listC);
		
		
	
		
		
	
        if(session.getAttribute("uid") != null) {
            uid = (int) session.getAttribute("uid");
        }


        if(del_id > 0)
        {
            cartService.deleteCart(del_id);
        }



        int totalProducts = listCart.stream().mapToInt(Cart::getQty).sum();
        m.addAttribute("totalProducts", totalProducts);

		return "user/checkout";
	}
	
	 
}
