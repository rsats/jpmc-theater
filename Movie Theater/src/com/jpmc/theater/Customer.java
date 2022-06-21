package com.jpmc.theater;

import java.util.Objects;

/**
 * 
 * @author roopsatsangi
 *
 */
public class Customer {
	
	private String name;
	private String id;
	
	/**
	 * 
	 * @param name Customer Name
	 * @param id Customer Id
	 */
	public Customer(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + "]";
	}
}
