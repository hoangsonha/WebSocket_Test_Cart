package com.hsh.demo.controller;

import com.hsh.demo.pojo.Product;
import com.hsh.demo.request.ProductRequest;
import com.hsh.demo.response.ProductResponse;
import com.hsh.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CartController {

    @Autowired private ProductService productService;

    @Autowired private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/add")
    @SendTo("/topic/productStatus") // khi addToCart() xử lí xong, thông điệp phản hồi sẽ được gửi tới tất cả các client đang lắng nghe (subscribe) kênh /topic/productStatus.
    public ResponseEntity<ProductResponse> addToCart(ProductRequest request) {
        if (productService.checkExistProduct(request.getId())) {
            productService.setStatusProduct(request.getId());
            log.info("SUCCESSFULLY");
            return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Success", "Sản phẩm đã được thêm vào giỏ hàng.", productService.getAllProducts()));
        } else {
            log.error("FAILED");
            return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Failed", "Đã có người khác thêm vào sản phẩm", null));
        }
    }




}
