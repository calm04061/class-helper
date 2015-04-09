package com.calm.javassist.helper;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * 注解处理器
 * @author dingqihui
 *
 */
public class AnnationHelper {
	private Annotation a;
	private ConstPool cp;

	public AnnationHelper(Annotation a, ConstPool cp) {
		super();
		this.a = a;
		this.cp = cp;
	}

	/**
	 * 添加字符串成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper addStringMember(String name, String value) {
		a.addMemberValue(name, new StringMemberValue(value, cp));
		return this;
	}

	/**
	 * 添加整数成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper addIntMember(String name, Integer value) {
		a.addMemberValue(name, new IntegerMemberValue(cp, value));
		return this;
	}
	
	/**
	 * 添加整数成员
	 * @param name
	 * @param value
	 * @return
	 */
	public AnnationHelper addBooleanMember(String name, Boolean value) {
		a.addMemberValue(name, new BooleanMemberValue(value,cp));
		return this;
	}
}
