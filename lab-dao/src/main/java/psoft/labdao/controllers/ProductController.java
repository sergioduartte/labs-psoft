package psoft.labdao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psoft.labdao.entities.Product;
import psoft.labdao.services.ProductService;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProduct() {
        return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProducts(id);
        if (product.isPresent())
            return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }


}