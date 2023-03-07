package pl.futurecollars.invoicing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import pl.futurecollars.invoicing.db.Database;
import pl.futurecollars.invoicing.db.memory.InMemoryDatabase;
import pl.futurecollars.invoicing.model.Company;
import pl.futurecollars.invoicing.model.Invoice;
import pl.futurecollars.invoicing.model.InvoiceEntry;
import pl.futurecollars.invoicing.model.Vat;
import pl.futurecollars.invoicing.service.InvoiceService;

public class App {

  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {

    Database db = new InMemoryDatabase();
    InvoiceService service = new InvoiceService(db);

    Company buyer = new Company("123-345-467", "ul. Weronicza, 11-227 Kraków", "MojaFima");
    Company seller = new Company("456-333-123", "ul. Konopnicka 2A/$id 03-287 Warszawa, Polska ", "Rechnungen Finanze");

    List<InvoiceEntry> products = List.of(new InvoiceEntry("Biuro podróży- dookoła świata ", BigDecimal.valueOf(100), BigDecimal.TEN, Vat.VAT_8));

    Invoice invoice = new Invoice(LocalDate.now(), buyer, seller, products);

    int id = service.save(invoice);

    service.getById(id).ifPresent(System.out::println);

    System.out.println(service.getAll());

    service.delete(id);

  }
}
