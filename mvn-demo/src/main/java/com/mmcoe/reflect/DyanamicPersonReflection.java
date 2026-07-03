package com.mmcoe.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DyanamicPersonReflection {
	public static void main(String[] args)throws Exception {
		//load the class manually into memory
		
		Class pc= Class.forName("com.mmcoe.reflect.Person");
		Constructor cc = pc.getConstructor(String.class,int.class);
		System.out.println(cc);
		
		Object obj = cc.newInstance("Bob",33);
		System.out.println(obj);
		
		Method mp= pc.getDeclaredMethod("print", null);
		System.out.println(mp);
		mp.setAccessible(true);
		mp.invoke(obj,null);
	}
}