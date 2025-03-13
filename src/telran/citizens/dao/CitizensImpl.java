package telran.citizens.dao;

import telran.citizens.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitizensImpl implements Citizens{
    private static Comparator<Person> lastNameComparator = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
    private static Comparator<Person> ageComparator = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
    private List<Person> idCollection;

    public CitizensImpl() {
        idCollection = new ArrayList<>();
    }

    public CitizensImpl(List<Person> citizens) {
        this();
        citizens.forEach(p -> add(p));
    }

    // O(n)
    @Override
    public boolean add(Person person) {
        if (person == null || find(person.getId()) != null) {
            return false;
        }
        return idCollection.add(person);
    }

    // O(n)
    @Override
    public boolean remove(int id) {
        Person victim = find(id);
        if (victim == null) {
            return false;
        }
        return idCollection.remove(victim);
    }

    // O(n)
    @Override
    public Person find(int id) {
        for (Person person: idCollection){
            if (person.getId() == id){
                return person;
            }
        }
        return null;
    }

    // O(n)
    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        List<Person> res = new ArrayList<>();
        for (Person person: idCollection){
            if (person.getAge()>= minAge && person.getAge() <= maxAge){
                res.add(person);
            }
        }
        return res;
    }

    // O(n)
    @Override
    public Iterable<Person> find(String lastName) {
        List<Person> res = new ArrayList<>();
        for (Person person: idCollection){
            if (lastName.equals(person.getLastName())){
                res.add(person);
            }
        }
        return res;
    }

    // O(n * log(n))
    @Override
    public Iterable<Person> getAllPersonSortedById() {
        List<Person> res = new ArrayList<>(idCollection);
        Collections.sort(res);
        return res;
    }

    // O(n * log(n))
    @Override
    public Iterable<Person> getAllPersonSortedByLastName() {
        List<Person> res = new ArrayList<>(idCollection);
        Collections.sort(res, lastNameComparator);
        return res;
    }

    // O(n * log(n))
    @Override
    public Iterable<Person> getAllPersonSortedByAge() {
        List<Person> res = new ArrayList<>(idCollection);
        Collections.sort(res, ageComparator);
        return res;
    }

    // O(1)
    @Override
    public int size() {
        return idCollection.size();
    }
}
