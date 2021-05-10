package no.ordr.products.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
public class Variant {

  @Id
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

  @JsonCreator
  public Variant() {
  }
}
