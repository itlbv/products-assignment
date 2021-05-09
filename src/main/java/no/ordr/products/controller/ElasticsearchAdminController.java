package no.ordr.products.controller;

import no.ordr.products.service.ElasticsearchAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-service/v1/admin")
public class ElasticsearchAdminController {

  ElasticsearchAdminService adminService;

  @Autowired
  public ElasticsearchAdminController(ElasticsearchAdminService adminService) {
    this.adminService = adminService;
  }

  @GetMapping("/indexRecovery")
  public ResponseEntity<String> indexRecovery() {
    return ResponseEntity.ok(
        String.format("Successful recovery! Documents saved: %s", adminService.indexRecovery()));
  }
}
