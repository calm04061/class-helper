package com.calm.javassist.helper;

import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class AnnationHelper {
	private Annotation a;
	private ConstPool cp;

	public AnnationHelper(Annotation a, ConstPool cp) {
		super();
		this.a = a;
		this.cp = cp;
	}

	public AnnationHelper addStringMember(String name, String value) {
		a.addMemberValue(name, new StringMemberValue(value, cp));
		return this;
	}

	public AnnationHelper addIntMember(String name, Integer value) {
		a.addMemberValue(name, new IntegerMemberValue(cp, value));
		return this;
	}
}
