package no.ordr.products.service;

import no.ordr.products.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public String getAll() {
    return String.valueOf(productRepository.getAll().size());
  }
}
