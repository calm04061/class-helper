package com.calm.javassist.helper;

import java.util.HashSet;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

/**
 * 类处理器
 * @author dingqihui
 *
 */
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

	/**
	 * 获得类处理器
	 * @return
	 */
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

	/**
	 * 制作类
	 * @param name
	 * @return
	 */
	public ClassHelper makeClass(String name) {
		makeClass = pool.makeClass(name);
		cf = makeClass.getClassFile();
		cp = cf.getConstPool();
		return this;
	}

	/**
	 * 添加属性
	 * @param code
	 * @return
	 * @throws CannotCompileException
	 */
	public FieldHelper addField(String code) throws CannotCompileException {
		CtField field = CtField.make(code, makeClass);
		makeClass.addField(field);
		return new FieldHelper(field,cp,cf);
	}
	
	/**
	 * 添加方法
	 * @param code
	 * @return
	 * @throws CannotCompileException
	 */
	public MethodHelper addMethod(String code) throws CannotCompileException {
		CtMethod method = CtMethod.make(code, makeClass);
		makeClass.addMethod(method);
		return new MethodHelper(method,cp,cf);
	}
	/**
	 * 添加get方法
	 * @param methodName
	 * @return
	 * @throws CannotCompileException
	 */
	public MethodHelper addGetMethod(String methodName,FieldHelper field) throws CannotCompileException {
		CtMethod method = CtNewMethod.getter(methodName, field.getTarget());
		makeClass.addMethod(method);
		return new MethodHelper(method,cp,cf);
	}
	
	/**
	 * 添加get方法
	 * @param methodName
	 * @return
	 * @throws CannotCompileException
	 */
	public MethodHelper addSetMethod(String methodName,FieldHelper field) throws CannotCompileException {
		CtMethod method = CtNewMethod.setter(methodName, field.getTarget());
		makeClass.addMethod(method);
		return new MethodHelper(method,cp,cf);
	}
	
	/**
	 * 设置父类
	 * @param classname
	 * @return
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 */
	public ClassHelper setSupperClass(String classname) throws CannotCompileException,
			NotFoundException {
		CtClass ctClass = pool.get(classname);
		makeClass.setSuperclass(ctClass);
		return this;
	}
	
	/**
	 * 生成类对象
	 * @return
	 * @throws CannotCompileException
	 */
	@SuppressWarnings("unchecked")
	public<T> Class<T> toClass() throws CannotCompileException{
		return makeClass.toClass();
	}
}
