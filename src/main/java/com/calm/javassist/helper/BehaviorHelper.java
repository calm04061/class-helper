package com.calm.javassist.helper;

import javassist.CtBehavior;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

/**
 * 动作
 * @author dingqihui
 *
 * @param <T>
 */
public class BehaviorHelper<T extends CtBehavior> extends MemberHelper<T> {

	BehaviorHelper(T member, ConstPool cp, ClassFile cf) {
		super(member, cp, cf);
	}

	/* (non-Javadoc)
	 * @see com.calm.javassist.helper.MemberHelper#addAttributes(javassist.bytecode.AnnotationsAttribute)
	 */
	@SuppressWarnings("unchecked")
	@Override
	void addAttributes(AnnotationsAttribute attr) {
		member.getMethodInfo().getAttributes().add(attr);
	}

}
