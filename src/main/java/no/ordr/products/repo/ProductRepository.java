package no.ordr.products.repo;

import java.util.Collection;
import java.util.List;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;

public interface ProductRepository {

  Collection<Product> getAll();

  Product getProductByName(String productName);

  Product getProductById(String productId);

  Product getProductByVariantName(String variantName);

  String saveProduct(Product product);

  String addVariant(String productId, Variant variant);

  String updateVariant(String variantId, Variant variant);

  boolean deleteProduct(String productId);

  boolean deleteVariant(String variantId);

  void addVariants(String productId, List<Variant> variants);

  void removeVariant(String productId, String variantId);
}
