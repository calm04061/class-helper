package com.calm.javassist.helper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.junit.Test;

import com.alibaba.fastjson.annotation.JSONField;

public class ClassHelperTest {

	@Test
	public void testMakeClass() throws CannotCompileException, NoSuchFieldException, SecurityException, NoSuchMethodException, NotFoundException, IOException, ClassNotFoundException {
		ClassHelper helper = ClassHelper.getHelper();
		assertNotNull(helper);
		ClassHelper make = helper.makeClass("com.calm.javassist.test.Bean");
		FieldHelper addField = make.addField("private String ID;");
//		Class<?> clazz = make.toClass();
//		Field field = clazz.getDeclaredField("id");
//		assertNotNull(field);
		
		MethodHelper methodHelper = make.addGetMethod("getID", addField);
		AnnationHelper<MethodHelper> annationHelper = methodHelper.addAnnation(JSONField.class);
		annationHelper.addBooleanMember("serialize", false).addBooleanMember("deserialize", false).addStringMember("name", "id").end();

//		JSONField annotation = method.getAnnotation(JSONField.class);
//		System.out.println(annotation);
		
		make.addSetMethod("setID", addField);
		
		Class<?> clazz  = make.toClass();
		System.out.println(clazz.hashCode());
		Method method = clazz.getMethod("setID",String.class);
		System.out.println(method);
		helper = ClassHelper.getHelper();
		helper.getClass("com.calm.javassist.test.Bean");
		
		addField = make.addField("private String NAME;");
		methodHelper = make.addGetMethod("getName", addField);
		annationHelper = methodHelper.addAnnation(JSONField.class);
		
		annationHelper.addBooleanMember("serialize", false).addBooleanMember("deserialize", false).addStringMember("name", "name").end();
		
		helper.addSetMethod("setNAME", addField);
		
		clazz=helper.toClass();
		System.out.println(clazz.hashCode());
		method = clazz.getMethod("setNAME",String.class);
		System.out.println(method);
		
	}

}
