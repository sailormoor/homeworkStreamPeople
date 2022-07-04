import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ronald", "Neville", "Harry", "Luna", "Minerva", "Severus");
        List<String> families = Arrays.asList("Weasley", "Longbottom", "Potter", "Lovegood", "McGonagall", "Snape");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        System.out.println("Количество несовершеннолетних:");
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);
        System.out.println("______________________");

        System.out.println("Список фамилий призывников :");

        List<String> recruits = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .limit(20)// Ограничим количество до 20
                .collect(Collectors.toList());
        System.out.println(recruits);


        System.out.println("______________________");

        System.out.println("Список потенциально работоспособных людей с высшим образованием :");

        List<String> worker = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 60 && person.getSex() == Sex.WOMAN ||
                        person.getAge() >= 18 && person.getAge() <= 65 && person.getSex() == Sex.MAN)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .limit(20)// Ограничим количество до 20
                .collect(Collectors.toList());
        System.out.println(worker);

        System.out.println("______________________");

    }
}