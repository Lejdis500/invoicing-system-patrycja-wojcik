package pl.futurecollars.invoicing.db.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import pl.futurecollars.invoicing.db.Database;
import pl.futurecollars.invoicing.model.Invoice;

public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();
  private int nextId = 1;

  @Override
  public int save(Invoice invoice) {
    invoice.setId(nextId);
    invoices.put(nextId, invoice);

    return nextId++;
  }

  @Override
  public Optional<Invoice> getById(int id) {
    return Optional.empty();xx
  }

  @Override
  public List<Invoice> getAll() {
    return null;xx
  }

  @Override
  public void update(int id, Invoice updatedInvoice) {
xx
  }

  @Override
  public void delete(int id) {
xx
  }
}
