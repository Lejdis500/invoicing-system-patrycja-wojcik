package pl.futurecollars.invoicing.utils

import pl.futurecollars.invoicing.helpers.TestHelpers
import pl.futurecollars.invoicing.model.Company
import spock.lang.Specification

class JsonServiceTest extends Specification {

    def jsonService = new JsonService()

    def "should convert object to json"() {
        given:
        def company = TestHelpers.company(0)
        when:
        def result = jsonService.toJson(company)
        then:
        result == "{\"taxIdentificationNumber\":\"0000000000\",\"address\":\"ul. Konopnicka 2A/0 03-287 Warszawa, Polska\",\"name\":\"Rechnungen,Finanzen 0 Sp. z o.o\"}"

    }

    def "should convert json to object"() {
        given:
        def companyJson = "{\"taxIdentificationNumber\":\"0000000000\",\"address\":\"ul. Konopnicka 2A/0 03-287 Warszawa, Polska\",\"name\":\"Rechnungen,Finanzen 0 Sp. z o.o\"}"
        when:
        def result = jsonService.toObject(companyJson, Company.class)
        then:
        result.taxIdentificationNumber == "0000000000"
        result.address == "ul. Konopnicka 2A/0 03-287 Warszawa, Polska"
        result.name == "Rechnungen,Finanzen 0 Sp. z o.o"


    }

    def "should throw exception while to object"() {
        given:
        def companyJson = "taxIdentificationNumber\":\"0000000000\",\"address\":\"ul. Konopnicka 2A/0 03-287 Warszawa, Polska\",\"name\":\"Rechnungen,Finanzen 0 Sp. z o.o\"}"
        when:
        jsonService.toObject(companyJson, Company.class)
        then:
        thrown(RuntimeException)


    }

    def "should throw exception while to json"() {
        given:
        def companyJson = "taxIdentificationNumber\":\"0000000000\",\"address\":\"ul. Konopnicka 2A/0 03-287 Warszawa, Polska\",\"name\":\"Rechnungen,Finanzen 0 Sp. z o.o\"}"
        when:
        jsonService.toJson(companyJson, Company.class)
        then:
        thrown(RuntimeException)
    }
}