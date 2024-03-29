package pl.futurecollars.invoicing.DatabaseIntegrationTest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.IfProfileValue
import pl.futurecollars.invoicing.db.AbstractDatabaseTest
import pl.futurecollars.invoicing.db.Database
import pl.futurecollars.invoicing.db.jpa.InvoiceRepository
import pl.futurecollars.invoicing.db.jpa.JpaDatabase

@DataJpaTest
@IfProfileValue(name = "spring.profiles.active", value = "jpa")

class JpaDatabaseIntegrationTest extends AbstractDatabaseTest {
    @Autowired
    private InvoiceRepository invoiceRepository

    @Override
    Database getDatabaseInstance() {
        assert invoiceRepository != null
        new JpaDatabase(invoiceRepository)
    }

}
