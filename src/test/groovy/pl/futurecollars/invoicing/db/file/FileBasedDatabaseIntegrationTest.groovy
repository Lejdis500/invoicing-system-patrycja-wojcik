package pl.futurecollars.invoicing.db.file

import pl.futurecollars.invoicing.helpers.TestHelpers
import pl.futurecollars.invoicing.db.AbstractDatabaseTest
import pl.futurecollars.invoicing.db.Database
import pl.futurecollars.invoicing.utils.FilesService
import pl.futurecollars.invoicing.utils.JsonService

import java.nio.file.Files

class FileBasedDatabaseIntegrationTest extends AbstractDatabaseTest{

    def dbPath

    @Override
    Database getDatabaseInstance() {
        def fileService = new FilesService()

        def idPath = File.createTempFile("ids", ".txt").toPath()
        def idService = new IdService(idPath, fileService)

        dbPath = File.createTempFile('invoices', '.txt').toPath()
        return new FileBasedDatabase(dbPath, idService, fileService, new JsonService())
    }

    def "file based database writes invoices to correct file"(){
        given:
        def db = getDatabaseInstance()
        when:
        db.save(TestHelpers.invoice(1))
        then:
        1 == Files.readAllLines(dbPath).size()

    }
}
