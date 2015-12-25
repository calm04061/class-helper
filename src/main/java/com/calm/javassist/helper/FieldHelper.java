package com.calm.javassist.helper;

import javassist.CtField;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

/**
 * 属性处理
 * @author dingqihui
 *
 */
public class FieldHelper extends MemberHelper<CtField>{
	
	FieldHelper(CtField member, ConstPool cp, ClassFile cf) {
		super(member, cp, cf);
	}

	/* (non-Javadoc)
	 * @see com.calm.javassist.helper.MemberHelper#addAttributes(javassist.bytecode.AnnotationsAttribute)
	 */
	@SuppressWarnings("unchecked")
	@Override
	void addAttributes(AnnotationsAttribute attr) {
		target.getFieldInfo().getAttributes().add(attr);
	}
}
