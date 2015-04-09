package com.calm.javassist.helper;

import java.util.HashSet;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

public class ClassHelper {
	private ClassPool pool = ClassPool.getDefault();
	private ClassFile cf;
	private ConstPool cp;
	private CtClass makeClass;
	private Set<ClassLoader> loaders = new HashSet<ClassLoader>();

	private ClassHelper(ClassLoader loader) {
		if (!loaders.contains(loader)) {
			loaders.add(loader);
			pool.insertClassPath(new LoaderClassPath(loader));
		}
	}

	public static ClassHelper getHelper() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return new ClassHelper(loader);
	}

	public static ClassHelper getHelper(ClassLoader loader) {
		if (loader == null) {
			throw new IllegalArgumentException("classLoader must be not null!");
		}
		return new ClassHelper(loader);
	}

	public ClassHelper makeClass(String name) {
		makeClass = pool.makeClass(name);
		cf = makeClass.getClassFile();
		cp = cf.getConstPool();
		return this;
	}

	public FieldHelper addField(String code) throws CannotCompileException {
		CtField field = CtField.make(code, makeClass);
		makeClass.addField(field);
		return new FieldHelper(field,cp,cf);
	}
	
	public MethodHelper addMethod(String code) throws CannotCompileException {
		CtMethod method = CtMethod.make(code, makeClass);
		makeClass.addMethod(method);
		return new MethodHelper(method,cp,cf);
	}

	public ClassHelper setSupperClass(String classname) throws CannotCompileException,
			NotFoundException {
		CtClass ctClass = pool.get(classname);
		makeClass.setSuperclass(ctClass);
		return this;
	}
}
