package no.ordr.products.repo;

import java.util.Collection;
import java.util.List;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;

public interface ProductRepository {

  Collection<Product> getAll();

  Product getProduct(String productName);

  Product getProductByVariantName(String variantName);

  String saveProduct(Product product);

  boolean deleteProduct(String productId);

  boolean deleteVariant(String variantId);

  void addVariants(String productId, List<Variant> variants);

  void removeVariant(String productId, String variantId);
}
