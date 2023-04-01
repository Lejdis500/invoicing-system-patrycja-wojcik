package pl.futurecollars.invoicing.utils;

import spock.lang.Specification

import javax.sound.sampled.Line
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
        def line = "test line"
        when:
        filesService.writeToFile(path, line)
        then:
        [line] == Files.readAllLines(path)
    }

    def "should write lines to file"() {
        given:
        def line = "test line"
        when:
        filesService.writeLinesToFile(path, [line])
        then:
        [line] == Files.readAllLines(path)
    }

    def "should read all lines"() {
        given:
        def line = "test line"
        filesService.writeLinesToFile(path, [line])
        when:
        def result = filesService.readAllLines(path)
        then:
        [line] == result
    }
}
