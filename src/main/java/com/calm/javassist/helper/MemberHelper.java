package com.calm.javassist.helper;

import javassist.CtMember;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

public abstract class MemberHelper<T extends CtMember> implements Helper<T> {
	protected T member;
	protected ConstPool cp;
	protected  ClassFile cf;
	
	MemberHelper(T member, ConstPool cp, ClassFile cf) {
		this.member = member;
		this.cp = cp;
		this.cf = cf;
	}
	
	public AnnationHelper addAnnation(String className){
		AnnotationsAttribute attr = new AnnotationsAttribute(cp,AnnotationsAttribute.visibleTag);
		Annotation a = new Annotation(className, cp);
		attr.setAnnotation(a);
		cf.addAttribute(attr);
		cf.setVersionToJava5();
		addAttributes(attr);
		return new AnnationHelper(a, cp);
	}
	
	abstract void addAttributes(AnnotationsAttribute attr);
}
