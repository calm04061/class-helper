package com.calm.javassist.helper;

import javassist.CtConstructor;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

/**
 * 构造函数处理
 * @author dingqihui
 *
 */
public class ConstructorHelper extends BehaviorHelper<CtConstructor> {

	ConstructorHelper(CtConstructor member, ConstPool cp, ClassFile cf) {
		super(member, cp, cf);
	}

}
