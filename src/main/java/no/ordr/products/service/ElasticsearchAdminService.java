package no.ordr.products.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import no.ordr.products.domain.Product;
import no.ordr.products.repo.ElasticProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchAdminService {

  ElasticProductRepository elasticProductRepository;

  @Autowired
  public ElasticsearchAdminService(ElasticProductRepository elasticProductRepository) {
    this.elasticProductRepository = elasticProductRepository;
  }

  public String indexRecovery() {
    List<Product> products = new ArrayList<>();
    try {
      products =
          Arrays.asList(
              new ObjectMapper()
                  .readValue(new ClassPathResource("products.json").getFile(), Product[].class));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return elasticProductRepository.saveAll(products);
  }
}

