package com.example.Ecom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Ecom.model.Product;
import com.example.Ecom.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    // GET ALL PRODUCTS
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {

        return new ResponseEntity<>(
                service.getAllProducts(),
                HttpStatus.OK
        );
    }

    // GET PRODUCT BY ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id) {

        Product product = service.getProductById(id);

        if (product != null) {
            return new ResponseEntity<>(
                    product,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // ADD PRODUCT + IMAGE
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile) {

        try {

            Product product1 =
                    service.addProduct(product, imageFile);

            return new ResponseEntity<>(
                    product1,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // GET PRODUCT IMAGE
    @GetMapping("/products/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(
            @PathVariable int productId) {

        Product product =
                service.getProductById(productId);

        if (product == null ||
                product.getImageData() == null) {

            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType(
                                product.getImageType()
                        )
                )
                .body(product.getImageData());
    }

    // UPDATE PRODUCT + IMAGE
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile)
            throws IOException {

        Product product1 =
                service.updateProduct(
                        id,
                        product,
                        imageFile
                );

        if (product1 != null) {

            return new ResponseEntity<>(
                    "Product updated",
                    HttpStatus.OK
            );

        } else {

            return new ResponseEntity<>(
                    "Product not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // DELETE PRODUCT
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable int id) {

        Product product =
                service.getProductById(id);

        if (product == null) {

            return new ResponseEntity<>(
                    "Product not found",
                    HttpStatus.NOT_FOUND
            );
        }

        service.deleteProduct(id);

        return new ResponseEntity<>(
                "Product deleted",
                HttpStatus.OK
        );
    }
}
