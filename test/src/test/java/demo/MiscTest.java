package demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MiscTest {

    class Human {

        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Human human = (Human) o;
            return age == human.age &&
                    Objects.equals(name, human.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    public void
    givenLambdaShortForm_whenSortingEntitiesByName_thenCorrectlySorted() {

        List<Human> humans = Arrays.asList(
                new Human("Sarah", 10),
                new Human("Jack", 12)
        );

        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));

        assert(humans.get(0).equals(new Human("Jack", 12)));
    }


    public final void givenStreamComparatorOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {

        List<Human> humans =
                Arrays.asList(new Human("Sarah", 10), new Human("Jack", 12));

        List<Human> reverseSortedHumans = humans.stream()
                .sorted(Comparator.comparing(Human::getName, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        assert(reverseSortedHumans.get(0).equals(new Human("Sarah", 10)));
    }

}
