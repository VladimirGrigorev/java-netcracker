package com.app;

import com.app.entities.Person;
import com.app.repository.Repository;
import ru.vsu.lab.entities.IPerson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamRequest {

    public static Object firstRequest(Repository<IPerson> personList){
        List<Person> list = personList.toPersonList();

        return list.stream().collect(Collectors.groupingBy(p->p.getDivision().getName(),
                Collectors.summingDouble(p->p.getSalary().intValue())));
    }

    public static List<Person> secondRequest(Repository<IPerson> personList) {
        List<Person> list = personList.toPersonList();
        list = list.stream().filter((p) -> p.getFirstName().contains("a") && p.getAge() > 30 && p.getSalary().compareTo(new BigDecimal(5000)) < 0).collect(Collectors.toList());
        return list;
    }

    public static List<Person> thirdRequest(Repository<IPerson> personList) {
        List<Person> list = personList.toPersonList();
        list = list.stream().filter((p)->p.getFirstName().contains("aa") || p.getFirstName().contains("Aa")).collect(Collectors.toList());
        return list;
    }

    public static Object fourthRequest(Repository<IPerson> personList) {
        List<Person> list = personList.toPersonList();

        return list.stream().collect(Collectors.groupingBy(p->p.getBirthdate().getYear(),
                Collectors.counting()));
    }
}
