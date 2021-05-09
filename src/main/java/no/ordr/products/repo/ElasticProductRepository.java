package no.ordr.products.repo;

import java.util.ArrayList;
import java.util.List;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

@Component
public class ElasticProductRepository implements ProductRepository {

  private static final String INDEX_NAME = "products";
  private final ElasticsearchOperations elasticsearchOperations;

  @Autowired
  public ElasticProductRepository(ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  @Override
  public List<Product> getAll() {
    return new ArrayList<>();
  }

  @Override
  public Product get(String productId) {
    return null;
  }

  @Override
  public String save(Product product) {
    IndexQuery indexQuery =
        new IndexQueryBuilder().withId(product.getId()).withObject(product).build();
    return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(INDEX_NAME));
  }

  @Override
  public void delete(String productId) {

  }

  @Override
  public void addVariants(String productId, List<Variant> variants) {

  }

  @Override
  public void removeVariant(String productId, String variantId) {

  }
}
