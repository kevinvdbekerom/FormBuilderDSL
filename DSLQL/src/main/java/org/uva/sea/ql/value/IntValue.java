package org.uva.sea.ql.value;

import java.math.BigDecimal;

public class IntValue extends Value {
	private int value;
	
	public IntValue(int value) {
		this.value = value;
	}
	
	// add
	@Override
	public Value add(Value rhs) {
		return rhs.addInt(this);
	}
	
	@Override
	public Value addInt(IntValue rhs) {
		return new IntValue(value + rhs.getValue());
	}
	
	@Override
	public Value addMoney(MoneyValue rhs) {
		return new MoneyValue(value + rhs.getValue());
	}
	
	// sub
	@Override
	public Value sub(Value rhs) {
		return rhs.subInt(this);
	}
	
	@Override
	public Value subInt(IntValue rhs) {
		return new IntValue(value - rhs.getValue());
	}
	
	@Override
	public Value subMoney(MoneyValue rhs) {
		return new MoneyValue(value - rhs.getValue());
	}
	
	// mul
	@Override
	public Value mul(Value rhs) {
		return rhs.mulInt(this);
	}
	
	@Override
	public Value mulInt(IntValue rhs) {
		return new IntValue(value * rhs.getValue());
	}
	
	@Override
	public Value mulMoney(MoneyValue rhs) {
		return new MoneyValue(value * rhs.getValue());
	}
	
	// div
	@Override
	public Value div(Value rhs) {
		return rhs.divInt(this);
	}
	
	@Override
	public Value divInt(IntValue rhs) {
		return new IntValue(value / rhs.getValue());
	}
	
	@Override
	public Value divMoney(MoneyValue rhs) {
		return new MoneyValue(value / rhs.getValue());
	}
	
	// pos
	@Override
	public Value pos() {
		return new IntValue(Math.abs(value));
	}
	
	// neg
	@Override
	public Value neg() {
		return new IntValue(value * -1);
	}
	
	/* logic operations */
	
	// greater than
	@Override
	public Value gt(Value rhs, boolean gEq) {
		return rhs.gtInt(this, gEq);
	}
	
	@Override
	public Value gtInt(IntValue rhs, boolean gEq) {
		return (gEq) 	? new BoolValue(value >= rhs.getValue()) 
						: new BoolValue(value > rhs.getValue());
	}
	
	@Override
	public Value gtMoney(MoneyValue rhs, boolean gEq) {
		return (gEq) 	? new BoolValue(value >= rhs.getValue()) 
						: new BoolValue(value > rhs.getValue());
	}
	
	// less than
	@Override
	public Value lt(Value rhs, boolean lEq) {
		return rhs.ltInt(this, lEq);
	}
	
	@Override
	public Value ltInt(IntValue rhs, boolean lEq) {
		return (lEq) 	? new BoolValue(value <= rhs.getValue()) 
						: new BoolValue(value < rhs.getValue());
	}
	
	@Override
	public Value ltMoney(MoneyValue rhs, boolean lEq) {
		return (lEq) 	? new BoolValue(value <= rhs.getValue()) 
						: new BoolValue(value < rhs.getValue());
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
