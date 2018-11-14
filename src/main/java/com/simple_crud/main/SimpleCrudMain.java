package com.simple_crud.main;

import com.simple_crud.dao.PersonDao;
import com.simple_crud.entity.Person;

public class SimpleCrudMain {

	public static void main(String[] args) {
		Person person = new Person(1, "Sumit", "M");
		PersonDao personDao = new PersonDao();
		personDao.savePerson(person);
	}

}
