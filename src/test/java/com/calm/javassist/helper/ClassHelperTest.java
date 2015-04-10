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
		FieldHelper addField = make.addField("private String id;");
//		Class<?> clazz = make.toClass();
//		Field field = clazz.getDeclaredField("id");
//		assertNotNull(field);
		
		MethodHelper methodHelper = make.addGetMethod("getId", addField);
		AnnationHelper<MethodHelper> annationHelper = methodHelper.addAnnation(JSONField.class);
		annationHelper.addBooleanMember("serialize", false).addBooleanMember("deserialize", false).addStringMember("name", "test").end();
		Class<?> clazz  = make.toClass();
		Method method = clazz.getMethod("getId");
		JSONField annotation = method.getAnnotation(JSONField.class);
		System.out.println(annotation);
	}

}
