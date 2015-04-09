package com.calm.javassist.helper;

import javassist.CtField;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

public class FieldHelper extends MemberHelper<CtField>{
	
	FieldHelper(CtField member, ConstPool cp, ClassFile cf) {
		super(member, cp, cf);
	}

	@SuppressWarnings("unchecked")
	@Override
	void addAttributes(AnnotationsAttribute attr) {
		member.getFieldInfo().getAttributes().add(attr);
	}
}
