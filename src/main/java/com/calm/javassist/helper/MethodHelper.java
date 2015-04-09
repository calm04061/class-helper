package com.calm.javassist.helper;

import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

public class MethodHelper extends MemberHelper<CtMethod>{

	MethodHelper(CtMethod member, ConstPool cp, ClassFile cf) {
		super(member, cp, cf);
	}

	@SuppressWarnings("unchecked")
	@Override
	void addAttributes(AnnotationsAttribute attr) {
		member.getMethodInfo().getAttributes().add(attr);
	}
}
