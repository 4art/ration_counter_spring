package de.metraf.controller

import de.metraf.model.Product
import de.metraf.model.WeatherModern
import de.metraf.service.RationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.yaml.snakeyaml.util.UriEncoder
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerTestSpec extends Specification {
    @Autowired
    RationService rationService
    @Autowired
    RestTemplate restTemplate
    @LocalServerPort
    int randomServerPort;
    String city = "Frankfurt";


    def "check get all products"(){
        given:
        Set<Product>products =
                restTemplate.getForObject(UriEncoder.encode("http://localhost:$randomServerPort/api/products"), List.class)
        expect:
        products != null
    }

    def "check getWeather by city"(){
        given:
        ResponseEntity<WeatherModern> weatherModernResponseEntity = restTemplate.exchange(UriEncoder.encode("http://localhost:$randomServerPort/api/weather/$city"), HttpMethod.GET, null, new ParameterizedTypeReference<WeatherModern>() {

        })
        and:
        WeatherModern weatherModern = weatherModernResponseEntity.getBody()
        expect:
        weatherModern != null
        weatherModern.getName() == city
        weatherModernResponseEntity.getStatusCode() == HttpStatus.OK
    }

    def "check global vars"(){
        expect:
        rationService != null
        restTemplate != null
        randomServerPort != null
        println randomServerPort
    }

    def "check new name"(){
        given:
        ResponseEntity<Map> mapResponseEntity = restTemplate.exchange(UriEncoder.encode("http://localhost:$randomServerPort/api/weather/$city"), HttpMethod.GET, null, new ParameterizedTypeReference<Map>() {
        })
        when:
        Map status = mapResponseEntity.getBody()
        then:
        status != null
        mapResponseEntity.getStatusCode() == HttpStatus.OK || mapResponseEntity.getStatusCode() == HttpStatus.NO_CONTENT
    }
}
