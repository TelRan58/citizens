package telran.citizens.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Person implements Comparable<Person>{
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Person(int id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;

        return id == person.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(getAge());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(id, o.id);
    }
}
