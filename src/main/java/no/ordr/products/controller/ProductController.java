package no.ordr.products.controller;

import no.ordr.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-service/v1")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/getAll")
  public ResponseEntity<String> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }
}
