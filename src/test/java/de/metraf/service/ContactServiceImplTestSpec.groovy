package de.metraf.service

import de.metraf.model.Contact
import de.metraf.repository.ContactRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

//@ContextConfiguration
// not mentioned by docs, but had to include this for Spock to startup the Spring context
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringJUnit4ClassRunner.class)
class ContactServiceImplSpec extends Specification {

    ContactRepository contactRepository = Mock(ContactRepository)
    ContactService service

    protected Contact contact
    protected Collection<Contact> contacts = new ArrayList<>()

    def setup() {
        contact = new Contact("bla-bla", "some name", "bla@bla.com", "0000-00-00 12:00:00")
        contacts.add(new Contact("bla-bla", "some name", "bla@bla.com", "0000-00-00 12:00:00"))
        contacts.add(new Contact("bl-bl", "some name1", "bla1@bla.com", "0000-00-00 11:00:00"))
        contacts.add(new Contact("bla-bla1", "some name0", "bla0@bla.com", "0000-00-00 10:00:00"))
        contactRepository.findAll() >> contacts
        contactRepository.findOne(_ as Long) >> contact
        contactRepository.findByText(_ as String) >> contact
        contactRepository.save(_ as Contact) >> new Contact("added1", "added1", "added1@bla.com", "2017-08-31 11:45:00")

        contactRepository.delete(_ as Long) >> contacts.remove(contacts.size() - 1) >> {
            throw new IllegalArgumentException()
        }

//        service = new ContactServiceImpl(contactRepository)
        service = Spy(ContactServiceImpl, constructorArgs: [contactRepository])
    }

    def "check service not NULL"() {
        expect:
        service != null
    }

    def "check findAll() not empty"() {
        given:
        Set<Contact> contacts1 = service.findAll()
        expect:
        contacts1.size() != 0
    }

    def "check called methods in findAll()"() {
        when:
        Set<Contact> contacts1 = service.findAll()
        then:
//        1 * contactRepository.findAll()
        1 * service.findAll()
    }

    def "check called method in findByID()"() {
        when:
        Contact contact1 = service.findById(1L)
        then:
        1 * service.findById(1L)
        1 * contactRepository.findOne(_ as Long)
    }

    def "return from findById() is not null"() {
        when:
        Contact contact1 = service.findById(1L)
        then:
        contact1 != null
    }

    def "return from findByText() is not null"() {
        when:
        Contact contact1 = service.findByText("some text")
        then:
        contact1 != null
    }

    def "check called methods in findByText()"() {
        when:
        Contact contact1 = service.findByText("bla")
        then:
        1 * contactRepository.findByText(_ as String)
        1 * service.findByText(_ as String)
    }

    def "check called methods in save()"() {
        when:
        service.save(new Contact())
        then:
        1 * service.save(_ as Contact)
        1 * contactRepository.save(_ as Contact)

    }

    def "check save() is not null"(){
        expect:
        service.save(new Contact()) != null
    }

    def "check called methods in delete()"() {
        when:
        service.delete(1L)
        then:
        1 * service.delete(_ as Long)
        1 * contactRepository.delete(_ as Long)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof ContactServiceImplSpec)) return false

        ContactServiceImplSpec that = (ContactServiceImplSpec) o

        if (contact != that.contact) return false
        if (contacts != that.contacts) return false
        if (contactRepository != that.contactRepository) return false
        if (service != that.service) return false

        return true
    }

    int hashCode() {
        int result
        result = (contactRepository != null ? contactRepository.hashCode() : 0)
        result = 31 * result + (service != null ? service.hashCode() : 0)
        result = 31 * result + (contact != null ? contact.hashCode() : 0)
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0)
        return result
    }

}
