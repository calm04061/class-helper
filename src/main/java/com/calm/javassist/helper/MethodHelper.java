package com.calm.javassist.helper;

import javassist.CtMethod;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

/**
 * 方法处理器
 * @author dingqihui
 *
 */
public class MethodHelper extends BehaviorHelper<CtMethod>{

	MethodHelper(CtMethod member, ConstPool cp, ClassFile cf) {
		super(member, cp, cf);
	}
}
