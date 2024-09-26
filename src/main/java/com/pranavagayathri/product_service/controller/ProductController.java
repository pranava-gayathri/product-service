package com.pranavagayathri.product_service.controller;

import com.pranavagayathri.product_service.model.Product;
import com.pranavagayathri.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("allProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("addProduct")
    public ResponseEntity<String> createProduct(Product product){
        return productService.createProduct(product);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,Product updatedProduct){
        return productService.updateProduct(id,updatedProduct);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @PostMapping("getProducts")
    public ResponseEntity<List<Product>> getProductsById(@RequestBody List<Integer> productIds){
        return productService.getProductsByIds(productIds);
    }

    @PutMapping("updateStock")
    public void updateStock(@RequestParam int pid){
        productService.updateStock(pid);
    }





}
