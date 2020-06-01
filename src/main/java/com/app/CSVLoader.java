package com.app;

import au.com.bytecode.opencsv.CSVReader;
import com.app.entities.Division;
import com.app.entities.Person;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static ru.vsu.lab.entities.enums.Gender.FEMALE;
import static ru.vsu.lab.entities.enums.Gender.MALE;

public class CSVLoader {

    public static ArrayList<Division> divisions = new ArrayList<>();

    /**
     * Метод добавления значений из файла csv
     * @param path - путь к файлу
     * @throws Exception
     */
    public static void addFromFileCSV(IRepository repository, String path) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(path), ';' , '"' , 1);
        String[] nextLine;
        int i = 0;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {

                Integer id = Integer.parseInt(nextLine[0]);
                String firstName = nextLine[1];
                String lastName = "empty";
                Gender gender;
                if(nextLine[2].equals("Male")){
                    gender = MALE;
                } else {
                    gender = FEMALE;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                String date = nextLine[3];
                LocalDate birthdate = LocalDate.parse(date, formatter);

                int idDivision = -1;
                boolean divisionExist = false;
                for (Division value : divisions) {
                    if (nextLine[4].equals(value.name)) {
                        idDivision = value.id;
                        divisionExist = true;
                        break;
                    }
                }
                if (!divisionExist){
                    divisions.add(new Division(nextLine[4]));
                    idDivision = divisions.size() - 1;
                }

                BigDecimal salary = BigDecimal.valueOf(Long.parseLong(nextLine[5]));

                repository.add(new Person(id, firstName, lastName, birthdate, gender, new Division(nextLine[4]),
                        salary));
            }
        }
    }
}
