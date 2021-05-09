package no.ordr.products.domain;

import java.util.Set;
import lombok.Data;

@Data
public class Product {
  private String id;
  private String name;
  private String description;
  private int version;
  private String image;
  private Set<Variant> variants;
  private String created;
  private String updated;
}