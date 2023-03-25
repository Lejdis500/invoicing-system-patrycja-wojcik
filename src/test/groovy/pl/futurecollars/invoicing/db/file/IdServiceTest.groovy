package pl.futurecollars.invoicing.db.file

import pl.futurecollars.invoicing.utils.FilesService
import spock.lang.Specification

import java.nio.file.Files

class IdServiceTest extends Specification {

    def path = File.createTempFile('test', '.txt').toPath()

    def " should return next id increment if file is empty"() {
        given:
        def idService = new IdService(path, new FilesService())
        when:
        def result = idService.getNextIdAndIncrement()
        then:
        result == 1
        ["2"] == Files.readAllLines(path)
    }

    def "should return next id and increment if file is not empty"() {
        given:
        Files.writeString(path, "10")
        def idService = new IdService(path, new FilesService())
        when:
        def result = idService.getNextIdAndIncrement()
        then:
        result == 10
        ["11"] == Files.readAllLines(path)
    }


}
