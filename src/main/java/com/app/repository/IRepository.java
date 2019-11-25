package com.app.repository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.app.entities.IPerson;

public interface IRepository {
	
	public void add(IPerson person);
	
	public IPerson get(int index);
	
	public void delete(int index);

	public void set(int index, IPerson person);
	
	public void add(int index, IPerson person);
	
	public List<IPerson> toList();
	
	//Should not use toList method
	public void sortBy(Comparator<IPerson> comparator );
	
	//Should not use toList method
	public IRepository searchBy(Predicate<IPerson> condition);
	
}
