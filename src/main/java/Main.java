import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Filter = filtrar por um atributo que seja igual para todos
    List<Person> females = people.stream()
        .filter(nome1 -> nome1.getGender().equals(Gender.MALE))
        .collect(Collectors.toList());
    females.forEach(System.out::println);

    // Sort = coloca na ordem decrescente de 2 atributos, thenComparing apenas se quiser comparar com mais um
    List<Person> sorted = people.stream()
        .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getName).reversed())
        .collect(Collectors.toList());
    sorted.forEach(System.out::println);

    // All match
    boolean allMatch = people.stream()
        .allMatch(nome1 -> nome1.getAge() > 8);
    System.out.println(allMatch);

    // Any match = vê se tem pelo menos um valor que representa o valor, se tiver retorna true
    boolean anyMatch = people.stream()
        .anyMatch(nome1 -> nome1.getAge() > 121);
    System.out.println(anyMatch);

    // None match = vê se nenhum item é igual ao valor, se nenhum for igual retorna true
    boolean noneMatch = people.stream()
        .noneMatch(person -> person.getName().equals("Antonio"));
    System.out.println(noneMatch);

    // Max
    people.stream()
            .max(Comparator.comparing(u -> u.getAge()))
            .ifPresent(e -> System.out.println("Max: " + e.getAge()));

    // Min
    people.stream()
            .min(Comparator.comparing(u -> u.getAge()))
            .ifPresent(e -> System.out.println("Min: " + e.getAge()));

    // Group = ordenar pelo nome crescente por male e female
    Map<Gender, List<Person>> groupByGender = people.stream()
        .collect(Collectors.groupingBy(Person::getGender));

    groupByGender.forEach((gender, people1) -> {
      System.out.println(gender);
      people1.forEach(System.out::println);
      System.out.println();
    });

    // mulher mais velha
    Optional<String> oldestFemaleAge = people.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .max(Comparator.comparing(Person::getAge))
        .map(Person::getName);

    oldestFemaleAge.ifPresent(System.out::println);
  }

  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }
}
