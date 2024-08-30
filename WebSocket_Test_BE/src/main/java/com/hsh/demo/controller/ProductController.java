package com.hsh.demo.controller;

import com.hsh.demo.pojo.Product;
import com.hsh.demo.request.ProductRequest;
import com.hsh.demo.response.ProductResponse;
import com.hsh.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ProductResponse> getALlProduct() {

        List<Product> lists = productService.getAllProducts();

        return !lists.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Success", "Sản phẩm đã được thêm vào giỏ hàng.", lists))
                :  ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Failed", "Đã có người khác thêm vào sản phẩm", null));
    }
}
