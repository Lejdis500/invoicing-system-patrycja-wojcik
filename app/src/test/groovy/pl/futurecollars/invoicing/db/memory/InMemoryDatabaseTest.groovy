package pl.futurecollars.invoicing.db.memory;

import pl.futurecollars.invoicing.db.Database
import pl.futurecollars.invoicing.model.Invoice;
import spock.lang.Specification;

class InMemoryDatabaseTest extends Specification {

  private Database database;
  private List<Invoice> invoices


  def setup(){
    database = new InMemoryDatabase();
    invoices = (1..12).collect{
      invoice(it)
    }
  }

  def "example test"(){

  }

}