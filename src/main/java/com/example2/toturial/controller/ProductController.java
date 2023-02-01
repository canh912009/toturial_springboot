package com.example2.toturial.controller;

import com.example2.toturial.models.Product;
import com.example2.toturial.models.ResponseObject;
import com.example2.toturial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    // DI : Dependency Injection
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    // this request is : http://localhost:8090/api/v1/Products
    // can use postman
    List<Product> getAllProducts() {
//        return Arrays.asList(
//                new Product(1L, "Mac", 2020, 2400.0, ""),
//                new Product(2L, "Mac 2", 2021, 2400.0, "")
//        );
        // must save in DB, now use H2 DB - In-memory DB (for testing, ok all -->   install MySQL/mongo ...)

        return productRepository.findAll();  // findAll() có sẵn r nha , thế ms gọi là dùng framework
    }

    @GetMapping("/{id}")
//     let return object with : data, message, status(code return success or not)
//    --> cần thành fomat chuẩn để client dễ dàng xử lý
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query product successfully", product)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error_code", "Query product can not find "+ id, "")
            );
        }
    }

    // insert new Product with POST method
    // postman : Raw, json
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 products must not same name
        List<Product> foundProduct = productRepository.findByProductName(newProduct.getProductName().trim());
        if(foundProduct.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "product already taken", "")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add product successfully", productRepository.save(newProduct))
        );
    }

}
