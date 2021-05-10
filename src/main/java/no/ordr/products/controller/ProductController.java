package no.ordr.products.controller;

import java.util.Collection;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;
import no.ordr.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    return ResponseEntity.ok(productService.search(searchString));
  }

  @GetMapping("/getProduct")
  public ResponseEntity<Product> getProduct(@RequestParam String productName) {
    return ResponseEntity.ok(productService.getProduct(productName));
  }

  @GetMapping("/getVariant")
  public ResponseEntity<Variant> getVariant(@RequestParam String variantName) {
    return ResponseEntity.ok(productService.getVariant(variantName));
  }

  @PutMapping("/saveProduct")
  public ResponseEntity<String> saveProduct(@RequestBody Product product) {
    return ResponseEntity
        .ok(String.format("Saved with id [%s]", productService.saveProduct(product)));
  }

  @PostMapping("/updateProduct")
  public ResponseEntity<String> updateProduct(@RequestParam String productId,
      @RequestBody Product product) {
    return ResponseEntity
        .ok(String.format("Saved with id [%s]", productService.updateProduct(productId, product)));
  }

  @PostMapping("/updateVariant")
  public ResponseEntity<String> updateVariant(@RequestParam String variantId,
      @RequestBody Variant variant) {
    return ResponseEntity
        .ok(String.format("Saved with id [%s]", productService.updateVariant(variantId, variant)));
  }

  @PutMapping("/addVariant")
  public ResponseEntity<String> addVariant(@RequestParam String productId,
      @RequestBody Variant variant) {
    return ResponseEntity
        .ok(String.format("Saved with id [%s]", productService.addVariant(productId, variant)));
  }

  @GetMapping("/deleteProduct")
  public ResponseEntity<String> deleteProduct(@RequestParam String productId) {
    return productService.deleteProduct(productId)
        ? ResponseEntity.ok(String.format("Deleted product with id [%s]", productId))
        : ResponseEntity.ok(String.format("Error with product %s", productId));
  }

  @GetMapping("/deleteVariant")
  public Boolean deleteVariant(@RequestParam String variantId) {
    return productService.deleteVariant(variantId);
  }
}
