package com.app.jaxb;

import com.app.entities.Person;
import com.app.repository.Repository;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Класс, экспортирующий IPersonRepository в XML файл.
 */
public class XMLParser {

    /**
     * Основной метод для экспортирования.
     * @param repository репозиторий.
     * @param xml_path путь xml файла.
     */
    public static void parseRepository(IRepository<IPerson> repository, String xml_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Repository.class, Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(repository, System.out);

            File xml = new File(xml_path);
            marshaller.marshal(repository, xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}