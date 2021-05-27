package stream_api;

import java.util.*;
import java.util.stream.Collectors;

public class Example11Students {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alex", Speciality.Physics, 1),
                new Student("Rika", Speciality.Biology, 4),
                new Student("Julia", Speciality.Biology, 2),
                new Student("Steve", Speciality.History, 4),
                new Student("Mike", Speciality.Finance, 1),
                new Student("Hinata", Speciality.Biology, 2),
                new Student("Richard", Speciality.History, 1),
                new Student("Kate", Speciality.Psychology, 2),
                new Student("Sergey", Speciality.ComputerScience, 4),
                new Student("Maximilian", Speciality.ComputerScience, 3),
                new Student("Tim", Speciality.ComputerScience, 5),
                new Student("Ann", Speciality.Psychology, 1)
        );

        // Нужно сгруппировать всех студентов по курсу
        students.stream()
                .collect(Collectors.groupingBy(Student::getYear))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("-----");
        // Вывести в алфавитном порядке список специальностей, на которых учатся перечисленные в списке студенты.
        students.stream()
                .map(Student::getSpeciality)
                .distinct()
                .sorted(Comparator.comparing(Enum::name))
                .forEach(System.out::println);

        System.out.println("-----");
        // Вывести количество учащихся на каждой из специальностей.
        students.stream()
                .collect(Collectors.groupingBy(Student::getSpeciality, Collectors.counting()))
                .forEach((s, count) -> System.out.println(s + ": " + count));

        System.out.println("-----");
        // Сгруппировать студентов по специальностям, сохраняя алфавитный порядок специальности, а затем сгруппировать по курсу.
        Map<Speciality, Map<Integer, List<Student>>> result = students.stream()
                .sorted(Comparator
                        .comparing(Student::getSpeciality, Comparator.comparing(Enum::name))
                        .thenComparing(Student::getYear)
                )
                .collect(Collectors.groupingBy(
                        Student::getSpeciality,
                        LinkedHashMap::new,
                        Collectors.groupingBy(Student::getYear)));

        result.forEach((s, map) -> {
            System.out.println("-= " + s + " =-");
            map.forEach((year, list) -> System.out.format("%d: %s%n", year, list.stream()
                    .map(Student::getName)
                    .sorted()
                    .collect(Collectors.joining(", ")))
            );
            System.out.println();
        });
    }
}
