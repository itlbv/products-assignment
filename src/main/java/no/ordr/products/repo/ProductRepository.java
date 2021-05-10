package no.ordr.products.repo;

import java.util.Collection;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;

public interface ProductRepository {

  Collection<Product> getAll();

  Collection<Product> search(String searchString);

  Product getProductByName(String productName);

  Product getProductByVariantName(String variantName);

  String saveProduct(Product product);

  String updateProduct(String productId, Product product);

  String addVariant(String productId, Variant variant);

  String updateVariant(String variantId, Variant variant);

  void deleteProduct(String productId);

  void deleteVariant(String variantId);
}
