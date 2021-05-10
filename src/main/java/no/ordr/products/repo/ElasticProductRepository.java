package no.ordr.products.repo;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
  public Collection<Product> search(String searchString) {
    Query query =
        new NativeSearchQueryBuilder().withQuery(matchQuery("name", searchString)).build();

    return elasticsearchOperations.search(query, Product.class).getSearchHits().stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
  }

  @Override
  public Product getProductByName(String productName) {
    Query query =
        new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("name", productName)).build();

    return elasticsearchOperations
        .searchOne(query, Product.class)
        .getContent(); // TODO may produce NPException, rewrite it
  }

  private Product getProductById(String productId) {
    Query query =
        new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("id", productId)).build();

    return elasticsearchOperations
        .searchOne(query, Product.class)
        .getContent(); // TODO may produce NPException, rewrite it
  }

  @Override
  public Product getProductByVariantName(String variantName) {
    Query query =
        new NativeSearchQueryBuilder()
            .withQuery(matchPhraseQuery("variants.variantName", variantName))
            .build();

    return elasticsearchOperations
        .searchOne(query, Product.class)
        .getContent(); // TODO may produce NPException, rewrite it
  }

  public Product getProductByVariantId(String variantId) {
    Query query =
        new NativeSearchQueryBuilder()
            .withQuery(matchPhraseQuery("variants.id", variantId))
            .build();

    return elasticsearchOperations
        .searchOne(query, Product.class)
        .getContent(); // TODO may produce NPException, rewrite it
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
  public String saveProduct(Product product) {
    IndexQuery indexQuery =
        new IndexQueryBuilder().withId(product.getId()).withObject(product).build();
    return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(INDEX_NAME));
  }

  @Override
  public String updateProduct(String productId, Product productNew) {
    deleteProduct(productId);
    saveProduct(productNew);
    return null;
  }

  @Override
  public String addVariant(String productId, Variant variant) {
    Product product = getProductById(productId);
    product.getVariants().add(variant);
    return saveProduct(product);
  }

  @Override
  public String updateVariant(String variantId, Variant variant) {
    Product product = getProductByVariantId(variantId);
    product.getVariants().removeIf(v -> v.getId().equals(variantId));
    product.getVariants().add(variant);
    return saveProduct(product);
  }

  @Override
  public boolean deleteProduct(String productId) {
    Query query =
        new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("id", productId)).build();

    elasticsearchOperations.delete(query, Product.class);
    return true; // TODO fix this
  }

  @Override
  public boolean deleteVariant(String variantId) {
    Query productQuery =
        new NativeSearchQueryBuilder()
            .withQuery(matchPhraseQuery("variants.id", variantId))
            .build();

    Product product =
        Optional.of(elasticsearchOperations.searchOne(productQuery, Product.class).getContent())
            .orElseThrow(Error::new);

    product.getVariants().removeIf(v -> v.getId().equals(variantId));

    saveProduct(product);
    return true; // TODO fix this
  }
}
