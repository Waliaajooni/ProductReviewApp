package com.nagarro.registration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.parser.MediaTypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.registration.config.JwtTokenUtil;
import com.nagarro.registration.models.Product;
import com.nagarro.registration.models.Review;
import com.nagarro.registration.models.User;
import com.nagarro.registration.service.ProductService;
import com.nagarro.registration.service.ReviewService;
import com.nagarro.registration.service.UserService;

@RestController
public class RegController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to Home Page";
	}
	
	@GetMapping("/users/{prodId}")
	public Product getReviewsOfProduct(@PathVariable long prodId) {
		Product product = productService.getProductById(prodId);
		System.out.println(product.getProdName());
		return product;
	}
	
	
	@PostMapping("/products/{prodId}")
	public Review addReview(@PathVariable long prodId, @RequestBody Review rev) {
		System.out.println(prodId);
		Product product = productService.getProductById(prodId);
		reviewService.addReview(product, rev);
		//System.out.println("Review - " + rev);
		return rev;
	}
	
	@GetMapping("/users")
	public List<Product> getAllProducts() {
		System.out.println("Show Products");
		return this.productService.getAllProducts();
	}
	
	@GetMapping("/usernames")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}
	
	@GetMapping("/usernames/{usrId}")
	public User getUser(@PathVariable long usrId) {
		return this.userService.getUser(usrId);
	}
	
	@PostMapping("/products/search")
	public List<Product> getProductsFromName(@RequestBody String sname) {
		System.out.println("Searching..");
		return productService.getProductsFromName(sname);
	}

	/*
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception{
		String tempMail = user.getUsrmail();
		String tempPassword = user.getUsrpassword();
		
		User userobj = userService.checkUserForLogin(tempMail, tempPassword);
		
		if (userobj == null) {
			throw new Exception("Bad Credentials - " + tempMail + " " + tempPassword);
		}
		
		user.setUsrId(userobj.getUsrId());
		user.setUsrname(userobj.getUsrname());
		
		System.out.println(user.getUsrname());
		
		return user;
	}
	*/
	
	@PostMapping(value = "/login" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String , String> login(@RequestBody User userDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsrmail(), userDto.getUsrpassword()));
        String token = jwtTokenUtil.generateToken(authentication.getName());
        Map <String , String > map = new HashMap<>();
        map.put("jwt",token);
        return map;
    }
	
	@PostMapping("/register")
	public User addUser(@RequestBody User user) throws Exception{
		User temp = userService.getUserByEmail(user.getUsrmail());
		
		if (temp != null) {
			throw new Exception("This email id already exists" + temp.getUsrmail());
		}
		
		return userService.addUser(user);
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		return this.userService.updateUser(user);
	}

}
