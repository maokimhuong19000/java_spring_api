package com.setec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setec.entities.Product;
import com.setec.repos.ProductRepo;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/product")
public class Mycontroller {
	@Autowired
	private ProductRepo productRepo;

	@GetMapping
	public List<Product> getAll() {
		return productRepo.findAll();
	}

	@GetMapping("/{id}")
	public Object getById(@PathVariable Integer id, HttpServletResponse http) {
		var pro = productRepo.findById(id);
		if (pro.isPresent())
			return pro.get();
		else {
			http.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Product id = " + id + " not found";
		}
	}

	@GetMapping("/name/{name}")
	public Object getByName(@PathVariable String name, HttpServletResponse http) {
		var pros = productRepo.findAllByName(name);
		if (pros.size() > 0) {
			return pros;
		} else {
			http.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Product id = " + name + " not found";
		}
	}

	@DeleteMapping("/{id}")
	public Object deleteById(@PathVariable Integer id, HttpServletResponse http) {
		var pro = productRepo.findById(id);
		if (pro.isPresent()) {
			productRepo.delete(pro.get());
			return "Product id = " + id + " has been deleted";
		} else {
			http.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "Product id = " + id + " not found";
		}
	}

	@PostMapping
	public Object addProduct(@RequestBody Product product, HttpServletResponse http) {
		productRepo.save(product);
		http.setStatus(HttpServletResponse.SC_CREATED);
		return product;
	}

	@PutMapping
	public Object editProduct(@RequestBody Product product, HttpServletResponse http) {
		var pro = productRepo.findById(product.getId());

		if(pro.isPresent()) {
			http.setStatus(HttpServletResponse.SC_OK);
		}else {
			http.setStatus(HttpServletResponse.SC_CREATED);
			product.setId(null);
		}
		productRepo.save(product);
		return product;
	}
}
