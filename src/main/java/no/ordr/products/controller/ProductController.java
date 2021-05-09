package no.ordr.products.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-service/v1")
public class ProductController {

  @GetMapping("/getAll")
  public ResponseEntity getAll() {
    return ResponseEntity.ok("Get all");
  }
}
