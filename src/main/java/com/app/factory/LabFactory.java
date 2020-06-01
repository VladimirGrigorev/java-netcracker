package com.app.factory;

import com.app.InjectorException;
import com.app.entities.Division;
import com.app.entities.Person;
import com.app.injector.LabInjector;
import com.app.repository.Repository;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.ILabFactory;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

public class LabFactory implements ILabFactory {
    /**
     * Функция создания персоны
     * @return созданную персону
     */
    public IPerson createPerson(){
        return new Person();
    }

    /**
     * Функция создания подразделения
     * @return созданное подразделение
     */
    public IDivision createDivision(){
        return new Division();
    }

    /**
     * Функция создания репозитория
     * @param clazz -
     * @param <T> -
     * @return созданный репозиторий
     */
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        try {
            return (new LabInjector()).inject(new Repository<T>());
        } catch (InjectorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IPersonRepository createPersonRepository() {
        return null;
    }
}
