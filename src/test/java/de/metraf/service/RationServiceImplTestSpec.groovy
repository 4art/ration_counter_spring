package de.metraf.service

import de.metraf.model.Ration
import de.metraf.repository.RationRepository
import spock.lang.Specification

import java.time.LocalDateTime

class RationServiceImplTestSpec extends Specification {
    RationRepository repository = Mock(RationRepository)
    RationService service
    List<Ration> rationList = new ArrayList<>()

    void setup() {
        rationList.add(new Ration(500, 1, 1L, getDateTime()))
        rationList.add(new Ration(550, 2, 2L, getDateTime()))
        rationList.add(new Ration(600, 3, 3L, getDateTime()))
        List<Ration> smallRationList = [rationList.get(0)]
        repository.findAll() >> rationList
        repository.findOne(_ as Long) >> rationList.get(0)
        repository.findByUserID(_ as Integer) >> smallRationList
        repository.findByProductID(_ as Long) >> smallRationList
        repository.findByUserIDBetweenTimes(_ as Integer, _ as String, _ as String) >> smallRationList
        repository.save(_ as Ration) >> rationList.get(0)

        service = Spy(RationServiceImpl, constructorArgs: [repository])
    }

    def "FindAll"() {
        given:
        Set<Ration> rations = service.findAll()
        expect:
        rations != null
        when:
        !rationList.empty
        then:
        !rations.empty
        when:
        service.findAll()
        then:
        1 * service.findAll()
    }

    def "Save"() {
        given:
        Ration ration = service.save(new Ration())
        expect:
        ration != null
        when:
        service.save(new Ration())
        then:
        1 * service.save(_ as Ration)
        1 * repository.save(_ as Ration)
    }

    def "Delete"() {
        when:
        service.delete(1L)
        then:
        1 * service.delete(_ as Long)
        1 * repository.delete(_ as Long)
    }

    def "FindOne"() {
        given:
        Ration ration = service.findOne(1L)
        expect:
        ration != null
        when:
        service.findOne(1L)
        then:
        1 * service.findOne(_ as Long)
        1 * repository.findOne(_ as Long)
    }

    def "FindByUserID"() {
        given:
        List<Ration> rations = service.findByUserID(1)
        expect:
        rations != null
        when:
        service.findByUserID(1)
        then:
        1 * service.findByUserID(_ as Integer)
        1 * repository.findByUserID(_ as Integer)
    }

    def "FindByUserIDBetweenTimes"() {
        given:
        List<Ration> rations = service.findByUserIDBetweenTimes(1, getDateTime(), getDateTime())
        expect:
        rations != null
        when:
        service.findByUserIDBetweenTimes(1, getDateTime(), getDateTime())
        then:
        1 * service.findByUserIDBetweenTimes(_ as Integer, _ as String, _ as String)
        1 * repository.findByUserIDBetweenTimes(_ as Integer, _ as String, _ as String)
    }

    def "FindByProductID"() {
        given:
        List<Ration> rations = service.findByProductID(1L)
        expect:
        rations != null
        when:
        service.findByProductID(1L)
        then:
        1 * service.findByProductID(_ as Long)
        1 * repository.findByProductID(_ as Long)
    }

    private String getDateTime(){
        String[] dateTimeParts = LocalDateTime.now().toString().split("T")
        return dateTimeParts[0] + " " + dateTimeParts[1]
    }
}