package com.mmcoe.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PersonReflection {
	public static void main(String[] args) throws Exception {
		Person p1= new Person("Polo",21);
		System.out.println(p1);
		//p1.print();
		
		
		Class pc =  p1.getClass();
		System.out.println(pc.getName());
		System.out.println(pc.getSuperclass());
		
		Constructor[] constructors = pc.getConstructors();
		
		System.out.println("---List of Constructors");
		
		for (Constructor c: constructors)
			System.out.println(c);
		
		Method[] decMethods = pc.getDeclaredMethods();
		System.out.println("---List of Declared Methods");
		for (Method m : decMethods)
			System.out.println(m);
		
		Field[] decfields = pc.getDeclaredFields();
		System.out.println("---List of Fields");
		for (Field f : decfields)
			System.out.println(f);
	}
}