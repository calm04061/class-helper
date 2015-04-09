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
public abstract class MemberHelper<T extends CtMember> implements Helper<T> {
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
	 * @return
	 */
	public AnnationHelper addAnnation(String className){
		AnnotationsAttribute attr = new AnnotationsAttribute(cp,AnnotationsAttribute.visibleTag);
		Annotation a = new Annotation(className, cp);
		attr.setAnnotation(a);
		cf.addAttribute(attr);
		cf.setVersionToJava5();
		addAttributes(attr);
		return new AnnationHelper(a, cp);
	}
	/**
	 * 添加注解
	 * @param className
	 * @return
	 */
	public AnnationHelper addAnnation(Class<?> className){
		return addAnnation(className.getName());
	}
	public T getTarget() {
		return target;
	}
	
	/**
	 * 添加注解属性
	 * @param attr
	 */
	abstract void addAttributes(AnnotationsAttribute attr);
}
