package pl.futurecollars.invoicing.db.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import pl.futurecollars.invoicing.db.Database;
import pl.futurecollars.invoicing.model.Invoice;
import pl.futurecollars.invoicing.utils.FilesService;
import pl.futurecollars.invoicing.utils.JsonService;

@AllArgsConstructor
public class FileBasedDatabase implements Database {

  private final Path databasePath;
  private final IdService idService;
  private final FilesService filesService;
  private final JsonService jsonService;

  @Override
  public int save(Invoice invoice) {
    try {
      invoice.setId(idService.getNextIdAndIncrement());
      filesService.appendLineToFile(databasePath, jsonService.toJson(invoice));
      return invoice.getId();
    } catch (IOException e) {
      throw new RuntimeException("Failed to save invoice in database", e);
    }
  }

  @Override
  public Optional<Invoice> getById(int id) {
    try {
      return filesService.readAllLines(databasePath)
          .stream()
          .filter(line -> containsId(line, id))
          .map(line -> jsonService.toObject(line, Invoice.class))
          .findFirst();
    } catch (IOException e) {
      throw new RuntimeException("Failed to getById invoice in databse", e);
    }
  }

  @Override
  public List<Invoice> getAll() {
    try {
      return filesService.readAllLines(databasePath)
          .stream()
          .map(line -> jsonService.toObject(line, Invoice.class))
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException("Failed to get all invoice in databse", e);
    }
  }

  @Override
  public void update(int id, Invoice updatedInvoice) {
    try {
      List<String> allInvoices = filesService.readAllLines(databasePath);
      List<String> listWithoutInvoiceWIthId = allInvoices.stream()
          .filter(line -> !containsId(line, id))
          .collect(Collectors.toList());

      if (allInvoices.size() == listWithoutInvoiceWIthId.size()) {
        throw new IllegalArgumentException("Id " + id + " does not exist");
      }

      updatedInvoice.setId(id);
      listWithoutInvoiceWIthId.add(jsonService.toJson(updatedInvoice));
      filesService.writeLinesToFile(databasePath, listWithoutInvoiceWIthId);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(int id) {
    try {
      List<String> updatedList = filesService.readAllLines(databasePath)
          .stream()
          .filter(line -> !containsId(line, id))
          .collect(Collectors.toList());

      filesService.writeLinesToFile(databasePath, updatedList);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean containsId(String line, int id) {
    return line.contains("\"id\":" + id + ",");
  }

}
