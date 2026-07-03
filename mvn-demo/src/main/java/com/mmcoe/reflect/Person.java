package com.mmcoe.reflect;

import java.security.BasicPermission;

public class Person {
	private String name;
	private int age;
	static {//Executes at the time of  class loading...
		System.out.println("Person class Loaded.......");
	}
	{
		System.out.println("Person instantiated...");
	}
	public Person() {
		
	}
	
	public Person(String name,int age) {
		this.name = name;
		this.age = age;
		
	}
	
	private void print() {
		SecurityManager mgr = new SecurityManager();
		mgr.checkPermission(new BasicPermission("Private Method") {
		});
		System.out.println("name=" + name + ", age=" + age );
	}
		
	
	@Override
	public String toString() {
		return "Person [Name=" + name + ",Age="+ age+"]";
		
	}
}