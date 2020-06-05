package com.app;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

import com.app.entities.Division;
import com.app.entities.Person;
import java.time.LocalDate;

import com.app.repository.Repository;
import ru.vsu.lab.entities.IPerson;

import static ru.vsu.lab.entities.enums.Gender.MALE;

/**
 * Класс представления меню
 */
public class MainView {

    private static final String PATH = "src/main/resources/persons.csv";

    /** Поле сканер */
    private Scanner in = new Scanner(System.in);
    /** Поле список персон */
    private Repository<IPerson> personList = new Repository<IPerson>();

    public MainView() throws InjectorException {
    }

    /** Функция показа меню */
    public void show() {
        // заполение списка
        LocalDate timePoint = LocalDate.of(1999, 12, 30);
        Division division = new Division(0, "A");

        for(int i=0; i<12; i++)
        {
            Person newPerson = new Person(i, String.format("first name %d", i), String.format("last name %d", i),
                    timePoint, MALE, division, new BigDecimal("1234.1234"));
            personList.add(newPerson);
        }

        while (true) {
            System.out.println("====Menu====");
            System.out.println("1. Show persons (Enter 1)");
            System.out.println("2. Create person (Enter 2)");
            System.out.println("3. Get person (Enter 2)");
            System.out.println("4. Delete person (Enter 4)");
            System.out.println("5. Sort persons (Enter 5)");
            System.out.println("6. Search person (Enter 6)");
            System.out.println("7. Exit (Enter 7)");

            int menuitem = in.nextInt();

            switch (menuitem) {
                case 1:
                    try {
                        CSVLoader loader = new CSVLoader(personList, PATH);
                    } catch (Exception e){
                        e.getStackTrace();
                    }
                    for(String person : personList.toPrint()) {
                        System.out.println(person);
                    }
                    break;
                case 2:
                    System.out.println("Enter person ID: ");
                    int idForCreate = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter person full name: ");
                    String fullNameForCreate = in.nextLine();
                    System.out.println("Enter person birth year: ");
                    int yearForCreate = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter person birth month: ");
                    int monthForCreate = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter person birth day: ");
                    int dayForCreate = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter person birth hour: ");
                    int hourForCreate = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter person birth minute: ");
                    int minuteForCreate = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter person gender(1-man, 2-woman): ");
                    int genderForCreate = in.nextInt();

                    LocalDate birthData = LocalDate.of(yearForCreate, monthForCreate, dayForCreate);
                    personList.add(new Person(idForCreate, fullNameForCreate, "", birthData,
                            MALE, division, new BigDecimal("1234.1234")));
                    break;
                case 3:
                    System.out.println("Enter person ID: ");
                    int idForGet = in.nextInt();
                    System.out.println(personList.get(idForGet));
                    break;
                case 4:
                    System.out.println("Enter person ID: ");
                    int idForDelete = in.nextInt();
                    personList.delete(idForDelete);
                    break;
                case 5:
                    Comparator<IPerson> comparator = new Comparator<IPerson>(){
                        public int compare(IPerson o1, IPerson o2) {
                            return o1.getFirstName().compareTo(o2.getFirstName());
                        }
                    };

                    personList.sortBy(comparator);

                    for(String person : personList.toPrint()) {
                        System.out.println(person);
                    }
                    break;
                case 6:
                    System.out.println("Enter person id: ");
                    int forSearch = in.nextInt();

                    Predicate<IPerson> predicate = x -> x.getId() == forSearch;

                    Repository repository = (Repository) personList.searchBy(predicate);

                    for(String person : repository.toPrint()) {
                        System.out.println(person);
                    }
                    break;
                case 7:
                    System.out.println(StreamRequest.firstRequest(personList).toString());
                    System.out.println(StreamRequest.fourthRequest(personList).toString());
            }
        }
    }
}
