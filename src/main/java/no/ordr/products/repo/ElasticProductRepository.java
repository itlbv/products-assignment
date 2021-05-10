package no.ordr.products.repo;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

import java.util.List;
import java.util.stream.Collectors;
import no.ordr.products.domain.Product;
import no.ordr.products.domain.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
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
    Query query =
        new NativeSearchQueryBuilder()
            .withQuery(matchAllQuery())
            .withPageable(
                PageRequest.of(0, 10000)) // without this Elasticsearch returns only 10 documents
            .build();

    return elasticsearchOperations.search(query, Product.class).getSearchHits().stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
  }

  @Override
  public Product getProduct(String productName) {
    Query query =
        new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("name", productName)).build();

    return elasticsearchOperations.searchOne(query, Product.class)
        .getContent(); //TODO may produce NPException, rewrite it
  }

  @Override
  public String save(Product product) {
    IndexQuery indexQuery =
        new IndexQueryBuilder().withId(product.getId()).withObject(product).build();
    return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(INDEX_NAME));
  }

  public String saveAll(List<Product> products) {
    List<IndexQuery> indexQueries =
        products.stream()
            .map(p -> new IndexQueryBuilder().withId(p.getId()).withObject(p).build())
            .collect(Collectors.toList());
    return String.valueOf(
        elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of(INDEX_NAME)).size());
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
