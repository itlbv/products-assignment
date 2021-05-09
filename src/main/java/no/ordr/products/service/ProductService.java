package no.ordr.products.service;

import no.ordr.products.domain.Product;
import no.ordr.products.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public String getAll() {
    return String.valueOf(productRepository.getAll().size());
  }

  public String save() {
    Product product = Product.builder().name("test product").build();
    return productRepository.save(product);
  }
}

