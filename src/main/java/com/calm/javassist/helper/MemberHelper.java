package com.calm.javassist.helper;

import javassist.CtMember;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

/**
 * 属性处理器
 * @author dingqihui
 *
 * @param <T>
 */
public abstract class MemberHelper<T extends CtMember> {
	protected final T target;
	protected ConstPool cp;
	protected  ClassFile cf;
	
	MemberHelper(T target, ConstPool cp, ClassFile cf) {
		this.target = target;
		this.cp = cp;
		this.cf = cf;
	}
	
	/**
	 * 添加注解
	 * @param className
	 * @return 返回注解对象
	 */
	@SuppressWarnings("unchecked")
	public<E extends MemberHelper<? extends CtMember>> AnnationHelper<E> addAnnation(String className){
		
		Annotation a = new Annotation(className, cp);

		return new AnnationHelper<E>((E) this,a, cp,cf);
	}
	/**
	 * 添加注解
	 * @param className
	 * @return 返回注解对象
	 */
	public <E extends MemberHelper<? extends CtMember>> AnnationHelper<E> addAnnation(Class<?> className){
		return addAnnation(className.getName());
	}
	/**
	 * @return 返回目标处理器
	 */
	public T getTarget() {
		return target;
	}
	
	/**
	 * 添加注解属性
	 * @param attr
	 */
	abstract void addAttributes(AnnotationsAttribute attr);
}
