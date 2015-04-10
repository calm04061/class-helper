package com.calm.javassist.helper;

import javassist.CtMember;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * 注解处理器
 * @author dingqihui
 *
 */
public class AnnationHelper<T extends MemberHelper<? extends CtMember>> {
	private Annotation a;
	private ConstPool cp;
	private T target;
	protected  ClassFile cf;
	private AnnotationsAttribute attr;
	public AnnationHelper(T target,Annotation a, ConstPool cp, ClassFile cf) {
		this.target = target;
		this.a = a;
		this.cp = cp;
		this.cf = cf;
		attr = new AnnotationsAttribute(cp,AnnotationsAttribute.visibleTag);
	}

	/**
	 * 添加字符串成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper<T> addStringMember(String name, String value) {
		a.addMemberValue(name, new StringMemberValue(value, cp));
		return this;
	}

	/**
	 * 添加整数成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper<T> addIntMember(String name, Integer value) {
		a.addMemberValue(name, new IntegerMemberValue(cp, value));
		return this;
	}
	
	/**
	 * 添加整数成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper<T> addBooleanMember(String name, Boolean value) {
		a.addMemberValue(name, new BooleanMemberValue(value,cp));
		return this;
	}
	
	/**
	 * 添加单精浮点数成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper<T> addFloatMember(String name, Float value) {
		a.addMemberValue(name, new FloatMemberValue(value,cp));
		return this;
	}
	
	/**
	 * 添加双精浮点数成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper<T> addDoubleMember(String name, Double value) {
		a.addMemberValue(name, new DoubleMemberValue(value,cp));
		return this;
	}
	
	/**
	 * 结束
	 * @return
	 */
	public T end(){
		attr.setAnnotation(a);
		cf.addAttribute(attr);
		cf.setVersionToJava5();
		target.addAttributes(attr);
		return target;
	}
}
