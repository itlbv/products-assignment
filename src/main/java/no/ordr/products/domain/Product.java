package no.ordr.products.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {

  private String id;
  private String name;
  private String description;
  private int version;
  private String image;
  private Set<Variant> variants;
  private String created;
  private String updated;

  @JsonCreator
  public Product() {
  }
}