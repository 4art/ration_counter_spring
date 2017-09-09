package de.metraf.service

import de.metraf.model.ApiKey
import de.metraf.model.WeatherModern
import de.metraf.model.weather.Weather
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class WeatherServiceImplTest extends Specification {
    RestTemplate restTemplate = new RestTemplate()
    WeatherService service
    String city = "Frankfurt"
    def apiKeyService = Mock(ApiKeyService)

    def setup() {
        apiKeyService.findByName(_ as String) >> new ApiKey("wetter", "b10896caa47947dba2c95319172608", "gi-15971161", "https://api.apixu.com/v1/current.json")
        service = Spy(WeatherServiceImpl, constructorArgs: [restTemplate, apiKeyService])
        //service = new WeatherServiceImpl(apiKeyService: apiKeyService, restTemplate: restTemplate)

    }

    def "GetWeatherModern"() {
        given:
        WeatherModern modern = service.getWeatherModern(city)
        expect:
        modern != null
        when:
        service.getWeatherModern(city)
        then:
        1 * apiKeyService.findByName(_ as String)
        //1 * restTemplate.getForEntity(_ as String, _ as Class)
    }

    def "GetWeather"() {
        given:
        Weather weather = service.getWeather(city)
        expect:
        weather != null
        when:
        service.getWeatherModern(city)
        then:
        1 * apiKeyService.findByName(_ as String)
    }
}
