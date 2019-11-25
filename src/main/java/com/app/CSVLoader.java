package com.app;

import au.com.bytecode.opencsv.CSVReader;
import com.app.entities.Division;
import com.app.entities.Person;
import com.app.entities.enums.Gender;
import com.app.repository.IRepository;

import java.io.FileReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static com.app.entities.enums.Gender.FEMALE;
import static com.app.entities.enums.Gender.MALE;

public class CSVLoader {

    private static ArrayList<Division> divisions = new ArrayList<>();

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

                int countDivision = 0;
                int idDivision = 0;
                if (divisions.size() == 0) {
                    divisions.add(new Division(countDivision, nextLine[4]));
                    idDivision = countDivision;
                    countDivision++;
                }
                for(int j = 0; j < divisions.size(); j++)
                {
                    if(nextLine[4].equals(divisions.get(j).name)){
                        divisions.add(new Division(countDivision, nextLine[4]));
                        idDivision = countDivision;
                        countDivision++;
                    } else {
                        idDivision = j;
                    }
                }

                BigDecimal salary = BigDecimal.valueOf(Long.parseLong(nextLine[5]));

                repository.add(new Person(id, firstName, lastName, birthdate, gender, divisions.get(idDivision),
                        salary));
                i++;
            }
            if (i == 10)
                break;
        }
    }
}
