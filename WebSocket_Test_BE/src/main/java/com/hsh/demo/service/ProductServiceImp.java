package com.hsh.demo.service;

import com.hsh.demo.pojo.Product;
import com.hsh.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired private ProductRepository productRepository;

    @Override
    public boolean checkExistProduct(int id) {
       Product product = productRepository.findById(id);
       return product.isStatus();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void setStatusProduct(int id) {
        productRepository.setStatusProduct(id);
    }
}
