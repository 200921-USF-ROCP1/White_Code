package com.banking.interfaces;

import java.sql.SQLException;

import java.util.List;

interface GenericDAO<T> {
	
	public T create(T t) throws SQLException ;
	
	public T retrieve(int id) throws SQLException ;
	
	public void update(T t) throws SQLException ;
	
	public void delete(int id) throws SQLException ;
	
	public List<T> getAll() throws SQLException;

}
