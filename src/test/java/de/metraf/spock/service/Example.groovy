package de.metraf.spock.service

import org.hibernate.Transaction
import spock.lang.Specification

class Example extends Specification {
    def "check Return 2"() {
        given:
        List<Integer> list = new ArrayList<>()
        when:
        list.add(2)
        then:
        2 == list.get(0)
    }

    def "hello"() {
        expect:
        Math.pow(2, 2) == 4
    }

    def "creating example stubs"() {
        given:
        List list = Stub()

        list.get(0) >> 0
        list.get(1) >> { throw new IllegalArgumentException() }
        list.get(2) >> 2
        expect:
        list.get(2) == 2
    }

    def "numbers to the power of two"(int a, int b, int c){
        expect:
            Math.pow(a, b) == c
        where:
            a | b | c
            1 | 2 | 1
            2 | 2 | 4
            2 | 3 | 8
    }



//    def "should first save object before committing transaction"() {
//        given:
//        UserService service = Mock()
//        Transaction transaction = Mock()
//        when:
//        service.save(new User())
//        transaction.commit()
//        then:
//        1 * service.save(_ as User)
//        then:
//        1 * transaction.commit()
//    }

//    def "creating spy from class"(){
//        given:
//        Transaction transaction = Stub(Transaction)
//        UserService service = Spy(UserServiceImpl, consructorArgs: [transaction])
//        expect:
//        service.save(new User(name: 'Artem'))
//    }



}

