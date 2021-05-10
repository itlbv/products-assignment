package no.ordr.products.service;

import java.util.Collection;
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

  public Collection<Product> search(String searchString) {
    return productRepository.search(searchString);
  }

  public Product getProduct(String name) {
    return productRepository.getProductByName(name);
  }

  public Variant getVariant(String variantName) {
    Product product = productRepository.getProductByVariantName(variantName);
    return product.getVariants().stream()
        .filter(v -> v.getVariantName().equals(variantName))
        .findFirst()
        .orElse(null);
  }

  public String saveProduct(Product product) {
    return productRepository.saveProduct(product);
  }

  public String updateProduct(String productId, Product product) {
    return productRepository.updateProduct(productId, product);
  }

  public String addVariant(String productId, Variant variant) {
    return productRepository.addVariant(productId, variant);
  }

  public String updateVariant(String variantId, Variant variant) {
    return productRepository.updateVariant(variantId, variant);
  }

  public void deleteProduct(String productId) {
    productRepository.deleteProduct(productId);
  }

  public void deleteVariant(String variantId) {
    productRepository.deleteVariant(variantId);
  }
}
