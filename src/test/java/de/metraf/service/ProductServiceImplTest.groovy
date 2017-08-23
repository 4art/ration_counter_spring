package de.metraf.service

import de.metraf.model.Product
import de.metraf.repository.ProductsRepository
import spock.lang.Specification

class ProductServiceImplTestSpec extends Specification {
    List<Product> products = new ArrayList<>();
    ProductsRepository repository = Mock(ProductsRepository)
    ProductService service;

    def setup() {
        products.add(new Product("kaki", 1.1, 1.1, 1.1, 1.1))
        products.add(new Product("orangen", 2.2, 2.2, 2.2, 2.2))
        products.add(new Product("karroten", 3.3, 3.3, 3.3, 3.3))

        repository.findAll() >> products
        repository.findOne(_ as Long) >> products.get(0)
        repository.findByName(_ as String) >> products.get(0)
        repository.save(_ as Product) >> products.get(0)

        service = Spy(ProductServiceImpl, constructorArgs: [repository])
    }

    def "check FindAll"() {
        //check not null
        given:
        Set<Product> products1 = service.findAll()
        expect:
        products1 != null
        //check not empty
        when:
        !products.empty
        then:
        !products1.empty
        //verify methods
        when:
        service.findAll()
        then:
        1 * service.findAll()
//        1 * repository.findAll()
    }


    def "FindOne"() {
//        check not null
        given:
        Product product = service.findOne(1L)
        expect:
        product != null
//        verify methods
        when:
        service.findOne(1L)
        then:
        1 * service.findOne(_ as Long)
        1 * repository.findOne(_ as Long)
    }

    def "FindByName"() {
//        check not null
        given:
        Product product = service.findByName("bla")
        expect:
        product != null
//        verify methods
        when:
        service.findByName("bla")
        then:
        1 * service.findByName(_ as String)
        1 * repository.findByName(_ as String)
    }

    def "Save"() {
        given:
        Product product = service.save(new Product())
        expect:
        product != null
        when:
        service.save(new Product())
        then:
        1 * service.save(_ as Product)
        1 * repository.save(_ as Product)
    }

    def "Delete"() {
        when:
        service.delete(1L)
        then:
        1 * service.delete(_ as Long)
        1 * repository.delete(_ as Long)
    }
}
