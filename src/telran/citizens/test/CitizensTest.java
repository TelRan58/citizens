package telran.citizens.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.citizens.dao.Citizens;
import telran.citizens.dao.CitizensImpl;
import telran.citizens.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CitizensTest {
    private Citizens citizens;
    private static final LocalDate now = LocalDate.now();

    @BeforeEach
    void setUp() {
        citizens = new CitizensImpl();
        citizens.add(new Person(1, "Peter", "Jackson", now.minusYears(23)));
        citizens.add(new Person(2, "John", "Smith", now.minusYears(20)));
        citizens.add(new Person(3, "Mary", "Jackson", now.minusYears(20)));
        citizens.add(new Person(4, "Rabindranate", "Anand", now.minusYears(25)));
    }

    @Test
    void testAdd() {
        assertFalse(citizens.add(null));
        assertFalse(citizens.add(new Person(2, "John", "Smith", now.minusYears(20))));
        assertEquals(4, citizens.size());
        assertTrue(citizens.add(new Person(5, "John", "Smith", now.minusYears(20))));
        assertEquals(5, citizens.size());
    }

    @Test
    void testRemove() {
        assertFalse(citizens.remove(5));
        assertEquals(4, citizens.size());
        assertTrue(citizens.remove(2));
        assertEquals(3, citizens.size());
    }

    @Test
    void testFindById() {
        Person person = citizens.find(1);
        assertEquals(1, person.getId());
        assertEquals("Peter", person.getFirstName());
        assertEquals("Jackson", person.getLastName());
        assertEquals(23, person.getAge());
        assertNull(citizens.find(5));
    }

    @Test
    void testFindByAges() {
        Iterable<Person> res = citizens.find(20, 23);
        int count = 0;
        for (Person person : res) {
            assertTrue(person.getAge() >= 20 && person.getAge() <= 23);
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void testFindByLastName() {
        Iterable<Person> res = citizens.find("Jackson");
        List<Person> actual = new ArrayList<>();
        res.forEach(p -> actual.add(p));
        Collections.sort(actual);
        Iterable<Person> expected = List.of(
                new Person(1, "Peter", "Jackson", now.minusYears(23)),
                new Person(3, "Mary", "Jackson", now.minusYears(20)));
        assertIterableEquals(expected, actual);
    }

    @Test
    void testGetAllPersonSortedById() {
        Iterable<Person> actual = citizens.getAllPersonSortedById();
        Iterable<Person> expected = List.of(new Person(1, "Peter", "Jackson", now.minusYears(23)),
                new Person(2, "John", "Smith", now.minusYears(20)),
                new Person(3, "Mary", "Jackson", now.minusYears(20)),
                new Person(4, "Rabindranate", "Anand", now.minusYears(25)));
        assertIterableEquals(expected, actual);
    }

    @Test
    void testGetAllPersonSortedByLastName() {
        Iterable<Person> actual = citizens.getAllPersonSortedByLastName();
        String name = "";
        int count = 0;
        for (Person person : actual) {
            count++;
            assertTrue(person.getLastName().compareTo(name) >= 0);
            name = person.getLastName();
        }
        assertEquals(4, count);
    }

    @Test
    void testGetAllPersonSortedByAge() {
        Iterable<Person> res = citizens.getAllPersonSortedByAge();
        int age = -1;
        int count = 0;
        for (Person person : res) {
            count++;
            assertTrue(person.getAge() >= age);
            age = person.getAge();
        }
        assertEquals(4, count);
    }

    @Test
    void testSize() {
        assertEquals(4, citizens.size());
    }
}
