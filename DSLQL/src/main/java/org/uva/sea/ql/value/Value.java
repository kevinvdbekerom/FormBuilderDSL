package org.uva.sea.ql.value;


public abstract class Value {
	
	/* math operations */
	
	//add
	public Value add(Value rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value addInt(IntValue rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value addMoney(MoneyValue rhs) {
		return new UndefinedValue();
	}
	
	//sub
	public Value sub(Value rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value subInt(IntValue rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value subMoney(MoneyValue rhs) {
		return new UndefinedValue();
	}
	
	// mul
	public Value mul(Value rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value mulInt(IntValue rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value mulMoney(MoneyValue rhs) {
		return new UndefinedValue();
	}
	
	// div
	public Value div(Value rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value divInt(IntValue rhs) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value divMoney(MoneyValue rhs) {
		return new UndefinedValue();
	}
	
	// pos
	public Value pos() {
		return new UndefinedValue(); //BadOperandException()
	}
	
	// neg 
	public Value neg() {
		return new UndefinedValue(); //BadOperandException()
	}
	
	/* logic operations */
	
	// greater than
	public Value gt(Value rhs, boolean gEq) {
		return new UndefinedValue();
	}
	
	public Value gtInt(IntValue rhs, boolean gEq) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value gtMoney(MoneyValue rhs, boolean gEq) {
		return new UndefinedValue();
	}
	
	// less than
	public Value lt(Value rhs, boolean lEq) {
		return new UndefinedValue();
	}
	
	public Value ltInt(IntValue rhs, boolean lEq) {
		return new UndefinedValue(); //BadOperandException()
	}
	
	public Value ltMoney(MoneyValue rhs, boolean lEq) {
		return new UndefinedValue();
	}

	public abstract Object getValue();
	
}
