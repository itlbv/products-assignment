package no.ordr.products.controller;

import java.util.Collection;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;
import no.ordr.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/getProduct")
  public ResponseEntity<Product> getProduct(@RequestParam String productName) {
    return ResponseEntity.ok(productService.getProduct(productName));
  }

  @GetMapping("/getVariant")
  public ResponseEntity<Variant> getVariant(@RequestParam String variantName) {
    return ResponseEntity.ok(productService.getVariant(variantName));
  }

  @GetMapping("/save")
  public ResponseEntity<String> save() {
    return ResponseEntity.ok(String.format("Saved with id [%s]", productService.save()));
  }
}
