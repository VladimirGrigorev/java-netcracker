package com.app.service;

import com.app.CSVLoader;
import com.app.exseption.InjectorException;
import com.app.entities.Person;
import com.app.factory.LabFactory;
import com.app.repository.Repository;
import ru.vsu.lab.entities.IPerson;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.app.service.IRepositoryService")
public class RepositoryService implements IRepositoryService {

    private Repository<IPerson> repository;

    public RepositoryService() throws InjectorException {
        repository = new Repository<IPerson>();
        CSVLoader loader = new CSVLoader(repository, "src/main/resources/persons.csv");
        this.repository = (Repository<IPerson>) loader.getRepository();
    }

    @Override
    public String getUserNameById(int id) {
        Person person = (Person) repository.get(id);
        return person.getFirstName();
    }

    @Override
    public long getCountUsersByAge(int age) {
        List<Person> list = repository.toPersonList();
        return list.stream().filter(x -> x.getAge() == age).count();
    }
}
