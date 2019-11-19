package psoft.labdao.daos;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psoft.labdao.entities.Product;

@Repository
public interface ProductRepository<T, ID extends Serializable> extends JpaRepository<Product, Long> {

}