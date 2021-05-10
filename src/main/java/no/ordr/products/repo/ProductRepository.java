package no.ordr.products.repo;

import java.util.Collection;
import java.util.List;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;

public interface ProductRepository {

  Collection<Product> getAll();

  Product getProduct(String productName);

  String save(Product product);

  void delete(String productId);

  void addVariants(String productId, List<Variant> variants);

  void removeVariant(String productId, String variantId);
}
