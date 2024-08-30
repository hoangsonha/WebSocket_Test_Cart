package com.hsh.demo.service;

import com.hsh.demo.pojo.Product;

import java.util.List;

public interface ProductService {
    public boolean checkExistProduct(int id);

    public List<Product> getAllProducts();

    public void setStatusProduct(int id);

}
