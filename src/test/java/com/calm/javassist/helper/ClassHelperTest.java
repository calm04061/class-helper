package com.calm.javassist.helper;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import javassist.CannotCompileException;

import org.junit.Test;

import com.alibaba.fastjson.annotation.JSONField;

public class ClassHelperTest {

	@Test
	public void testMakeClass() throws CannotCompileException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		ClassHelper helper = ClassHelper.getHelper();
		assertNotNull(helper);
		ClassHelper make = helper.makeClass("com.calm.javassist.test.Bean");
		FieldHelper addField = make.addField("private String ID;");
//		Class<?> clazz = make.toClass();
//		Field field = clazz.getDeclaredField("id");
//		assertNotNull(field);
		
		MethodHelper methodHelper = make.addGetMethod("getID", addField);
		AnnationHelper<MethodHelper> annationHelper = methodHelper.addAnnation(JSONField.class);
		annationHelper.addBooleanMember("serialize", false).addBooleanMember("deserialize", false).addStringMember("name", "test").end();

//		JSONField annotation = method.getAnnotation(JSONField.class);
//		System.out.println(annotation);
		
		make.addSetMethod("setID", addField);
		
		Class<?> clazz  = make.toClass();
		Method method = clazz.getMethod("setID",Object.class);
		System.out.println(method);
	}

}
