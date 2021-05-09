package no.ordr.products.domain;

import lombok.Data;

@Data
public class Variant {
  private String id;
  private String variantName;
  private long quantity;
  private String unit;
  private float price;
  private float vat;
  private String currency;
  private int version;
  private String created;
  private String updated;
}
