package no.ordr.products.service;

import java.util.Set;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;
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
    Set<Variant> variants =
        Set.of(
            Variant.builder().variantName("1").build(), Variant.builder().variantName("2").build());
    Product product = Product.builder().name("test product").variants(variants).build();
    return productRepository.save(product);
  }
}
