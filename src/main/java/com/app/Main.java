package com.app;

import com.app.factory.LabFactory;
import com.app.jaxb.XMLParser;
import com.app.jaxb.XMLResolver;
import com.app.repository.Repository;
import ru.vsu.lab.entities.IPerson;

public class Main {

    private static final String PATH = "src/main/resources/persons.csv";
    private static final String XML_PATH = "src/main/resources/repository.xml";

    /** Точка входа в приложение */
    public static void main(String[] args) {
        LabFactory factory = new LabFactory();
        CSVLoader loader = new CSVLoader(factory.createRepository(IPerson.class), PATH);

        // From PersonRepository to XML
        XMLParser.parseRepository(loader.getRepository(), XML_PATH);

        // From XML to PersonRepository
        Repository repository = XMLResolver.resolveXML(XML_PATH);
        for (Object object : repository.toPrint()) {
            System.out.println(object.toString());
        }
    }
}
