package pl.futurecollars.invoicing.model;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Invoice {

  @ApiModelProperty(value = "Invoice id generated by application", required = true, example = "1")
  private int id;

  @ApiModelProperty(value = "Date invoice was created", required = true)
  private LocalDate date;

  @ApiModelProperty(value = "Company who bought the product/service", required = true)
  private Company buyer;

  @ApiModelProperty(value = "Company who is selling the product/service", required = true)
  private Company seller;

  @ApiModelProperty(value = "List of products/services", required = true)
  private List<InvoiceEntry> entries;

  public Invoice(LocalDate date, Company buyer, Company seller, List<InvoiceEntry> entries) {
    this.date = date;
    this.buyer = buyer;
    this.seller = seller;
    this.entries = entries;

  }
}
