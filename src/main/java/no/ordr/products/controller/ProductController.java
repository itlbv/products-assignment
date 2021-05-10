package no.ordr.products.controller;

import java.util.Collection;
import java.util.Objects;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;
import no.ordr.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-service/v1")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/getAll")
  public ResponseEntity<Collection<Product>> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }

  @GetMapping("/search")
  public ResponseEntity<Collection<Product>> search(@RequestParam String searchString) {
    Objects.requireNonNull(searchString);
    return ResponseEntity.ok(productService.search(searchString));
  }

  @GetMapping("/getProduct")
  public ResponseEntity<Product> getProduct(@RequestParam String productName) {
    Objects.requireNonNull(productName);
    return ResponseEntity.ok(productService.getProduct(productName));
  }

  @GetMapping("/getVariant")
  public ResponseEntity<Variant> getVariant(@RequestParam String variantName) {
    Objects.requireNonNull(variantName);
    return ResponseEntity.ok(productService.getVariant(variantName));
  }

  @PostMapping("/saveProduct")
  public ResponseEntity<String> saveProduct(@RequestBody Product product) {
    Objects.requireNonNull(product);
    return ResponseEntity
        .ok(String.format("Product saved with id:[%s]", productService.saveProduct(product)));
  }

  @PutMapping("/updateProduct")
  public ResponseEntity<String> updateProduct(@RequestParam String productId,
      @RequestBody Product product) {
    Objects.requireNonNull(productId);
    Objects.requireNonNull(product);
    return ResponseEntity
        .ok(String.format("Product saved with id:[%s]",
            productService.updateProduct(productId, product)));
  }

  @PostMapping("/addVariant")
  public ResponseEntity<String> addVariant(@RequestParam String productId,
      @RequestBody Variant variant) {
    Objects.requireNonNull(productId);
    Objects.requireNonNull(variant);
    return ResponseEntity
        .ok(String
            .format("Product saved with id:[%s]", productService.addVariant(productId, variant)));
  }

  @PutMapping("/updateVariant")
  public ResponseEntity<String> updateVariant(@RequestParam String variantId,
      @RequestBody Variant variant) {
    Objects.requireNonNull(variantId);
    Objects.requireNonNull(variant);
    return ResponseEntity
        .ok(String.format("Product saved with id:[%s]",
            productService.updateVariant(variantId, variant)));
  }

  @DeleteMapping("/deleteProduct")
  public ResponseEntity<String> deleteProduct(@RequestParam String productId) {
    Objects.requireNonNull(productId);
    productService.deleteProduct(productId);
    return ResponseEntity.ok(String.format("Product with id:[%s] deleted", productId));
  }

  @DeleteMapping("/deleteVariant")
  public ResponseEntity<String> deleteVariant(@RequestParam String variantId) {
    Objects.requireNonNull(variantId);
    productService.deleteVariant(variantId);
    return ResponseEntity.ok(String.format("Variant with id:[%s] deleted", variantId));
  }
}
