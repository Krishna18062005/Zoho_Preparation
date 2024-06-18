import java.util.*;

class Person {
    String name;
    String gender;
    Person father;
    Person mother;
    List<Person> children;
    List<Person> siblings;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
        this.children = new ArrayList<>();
        this.siblings = new ArrayList<>();
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public void addSibling(Person sibling) {
        if (!this.siblings.contains(sibling) && !this.name.equals(sibling.name)) {
            this.siblings.add(sibling);
        }
    }
}

class FamilyTree {
    Map<String, Person> people;

    public FamilyTree() {
        this.people = new HashMap<>();
    }

    public Person addPerson(String name, String gender, String fatherName, String motherName) {
        Person person = people.getOrDefault(name, new Person(name, gender));
        person.gender = gender;

        if (fatherName != null) {
            Person father = people.getOrDefault(fatherName, new Person(fatherName, "Male"));
            father.addChild(person);
            person.father = father;
            people.put(fatherName, father);
        }

        if (motherName != null) {
            Person mother = people.getOrDefault(motherName, new Person(motherName, "Female"));
            mother.addChild(person);
            person.mother = mother;
            people.put(motherName, mother);
        }

        people.put(name, person);
        return person;
    }

    public void setSiblings() {
        for (Person person : people.values()) {
            if (person.father != null && person.mother != null) {
                for (Person sibling : people.values()) {
                    if (!sibling.name.equals(person.name) && 
                        sibling.father != null && 
                        sibling.mother != null &&
                        sibling.father.name.equals(person.father.name) && 
                        sibling.mother.name.equals(person.mother.name)) {
                        person.addSibling(sibling);
                    }
                }
            }
        }
    }

    public List<String> findEligibleMatches(String personName) {
        List<String> eligibleMatches = new ArrayList<>();
        Person person = people.get(personName);

        if (person == null) {
            return eligibleMatches; // Return empty list if the person is not found
        }

        if (person.gender.equals("Male")) {
            // Father's sisters' daughters
            if (person.father != null && person.father.siblings != null) {
                for (Person aunt : person.father.siblings) {
                    if (aunt.gender.equals("Female")) {
                        for (Person cousin : aunt.children) {
                            if (cousin.gender.equals("Female")) {
                                eligibleMatches.add(cousin.name);
                            }
                        }
                    }
                }
            }
            // Mother's brothers' daughters
            if (person.mother != null && person.mother.siblings != null) {
                for (Person uncle : person.mother.siblings) {
                    if (uncle.gender.equals("Male")) {
                        for (Person cousin : uncle.children) {
                            if (cousin.gender.equals("Female")) {
                                eligibleMatches.add(cousin.name);
                            }
                        }
                    }
                }
            }
        } else if (person.gender.equals("Female")) {
            // Father's sisters' sons
            if (person.father != null && person.father.siblings != null) {
                for (Person aunt : person.father.siblings) {
                    if (aunt.gender.equals("Female")) {
                        for (Person cousin : aunt.children) {
                            if (cousin.gender.equals("Male")) {
                                eligibleMatches.add(cousin.name);
                            }
                        }
                    }
                }
            }
            // Mother's brothers' sons
            if (person.mother != null && person.mother.siblings != null) {
                for (Person uncle : person.mother.siblings) {
                    if (uncle.gender.equals("Male")) {
                        for (Person cousin : uncle.children) {
                            if (cousin.gender.equals("Male")) {
                                eligibleMatches.add(cousin.name);
                            }
                        }
                    }
                }
            }
        }

        return eligibleMatches;
    }
}

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        familyTree.addPerson("John", "Male", "Brad", "Lisa");
        familyTree.addPerson("Emma", "Female", "Brad", "Lisa");
        familyTree.addPerson("Alex", "Male", "John", "Jenny");
        familyTree.addPerson("Emily", "Female", "Steve", "Emma");
        familyTree.addPerson("Rachel", "Female", "Steve", "Emma");

        // Set siblings for all persons
        familyTree.setSiblings();

        String personName = "Alex";
        List<String> eligibleMatches = familyTree.findEligibleMatches(personName);

        System.out.println(String.join(", ", eligibleMatches));
    }
}
