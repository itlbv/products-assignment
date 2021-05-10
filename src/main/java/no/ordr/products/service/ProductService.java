package no.ordr.products.service;

import java.util.Collection;
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

  public Collection<Product> getAll() {
    return productRepository.getAll();
  }

  public Product getProduct(String name) {
    return productRepository.getProduct(name);
  }

  public Variant getVariant(String variantName) {
    Product product = productRepository.getProductByVariantName(variantName);
    return product.getVariants().stream()
        .filter(v -> v.getVariantName().equals(variantName))
        .findFirst()
        .orElse(null);
  }

  public String save() {
    Set<Variant> variants =
        Set.of(
            Variant.builder().variantName("1").build(), Variant.builder().variantName("2").build());
    Product product = Product.builder().name("test product").variants(variants).build();
    return productRepository.saveProduct(product);
  }

  public boolean deleteProduct(String productId) {
    return productRepository.deleteProduct(productId);
  }

  public boolean deleteVariant(String variantId) {
    return productRepository.deleteVariant(variantId);
  }
}
