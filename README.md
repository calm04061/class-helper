# class-helper
基于javassist类修改工具包
	
	可以动态的创建类
	动态修改类

		ClassHelper helper = ClassHelper.getHelper();
		ClassHelper make = helper.makeClass("com.calm.javassist.test.Bean");
		FieldHelper idField = make.addField("private String id;");
		MethodHelper methodHelper = make.addGetMethod("getId", idField);
		AnnationHelper<MethodHelper> annationHelper = methodHelper.addAnnation(JSONField.class);
		annationHelper.addBooleanMember("serialize", false)
			.addBooleanMember("deserialize", false)
			.addStringMember("name", "test")
			.end();
		Class<?> clazz  = make.toClass();
		Method method = clazz.getMethod("getId");
		JSONField annotation = method.getAnnotation(JSONField.class);
		System.out.println(annotation);
	
  
