package com.pranavagayathri.product_service.service;

import com.pranavagayathri.product_service.model.Category;
import com.pranavagayathri.product_service.model.Product;
import com.pranavagayathri.product_service.model.Status;
import com.pranavagayathri.product_service.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public ResponseEntity<String> createProduct(Product product) {
        productRepo.save(product);
        return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateProduct(int id, Product updatedProduct) {
        Product product = productRepo.findById(id).orElse(null);

        if (product != null) {
            product.setProductName(updatedProduct.getProductName());
            product.setCategory(updatedProduct.getCategory());
            product.setStatus(updatedProduct.getStatus());
            product.setPrize(updatedProduct.getPrize());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            productRepo.save(product);
            return new ResponseEntity<>("update successful",HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<String> deleteProduct(int id) {

        productRepo.deleteById(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }


    public ResponseEntity<List<Product>> getProductsByIds(List<Integer> productIds) {
        List<Product> products = new ArrayList<>();
        for (int i:productIds){
            Product product=productRepo.findById(i).get();
            products.add(product);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    public void updateStock(int pid) {
        Product product =productRepo.findById(pid).get();
        int k=product.getStockQuantity();

        product.setStockQuantity(k-1);
        if(k==1){
        product.setStatus(Status.OUT_OF_STOCK);
        }

        productRepo.save(product);
    }
}
