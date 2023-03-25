package pl.futurecollars.invoicing.utils;

import spock.lang.Specification

import java.nio.file.Files;

class FilesServiceTest extends Specification {

    def filesService = new FilesService()
    def path = File.createTempFile('test', '.txt').toPath()

    def "should append line to file"() {
        given:
        def line = "test line"
        when:
        filesService.appendLineToFile(path, line)
        then:
        [line] == Files.readAllLines(path)
    }

    def "should write to file"() {
        given:
        def line = "tekst line"
        when:
        filesService.writeToFile(path, line)
        then:
        [line] == Files.readAllLines(path)
    }

    def "should write Lines To File"() {
        given:
        def line = "tekst line"
        when:
        filesService.writeLinesToFile(path, line)
        then:
        [line] == Files.readAllLines(path)
    }

    def "should read all lines"() {
        given:
        def line = "tekst line"
        when:
        filesService.writeLinesToFile(path, line)
        then:
        [line] == Files.readAllLines(path)
    }
}
