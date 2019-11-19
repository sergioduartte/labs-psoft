package psoft.labdao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import psoft.labdao.daos.ProductRepository;
import psoft.labdao.entities.Product;

@Service
public class ProductService {

    private ProductRepository<Product, Long> productDAO;

    public ProductService(ProductRepository<Product, Long> productDAO) {
        super();
        this.productDAO = productDAO;
    }

    public Product addProduct(Product product) {
        return productDAO.save(product);
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    public Optional<Product> getProducts(Long id) {
        return productDAO.findById(id);
    }
}