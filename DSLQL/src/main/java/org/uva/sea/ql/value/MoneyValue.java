package org.uva.sea.ql.value;

public class MoneyValue extends Value {
	private double value;
	
	public MoneyValue(double value) {
		this.value = value;
	}
	
	@Override
	public Value add(Value rhs) {
		return rhs.addMoney(this);
	}
	
	@Override
	public Value addInt(IntValue rhs) {
		return new MoneyValue(value + rhs.getValue());
	}
	
	@Override
	public Value addMoney(MoneyValue rhs) {
		return new MoneyValue(value + rhs.getValue());
	}
	
	// sub
	@Override
	public Value sub(Value rhs) {
		return rhs.subMoney(this);
	}
	
	@Override
	public Value subInt(IntValue rhs) {
		return new MoneyValue(value - rhs.getValue());
	}
	
	@Override
	public Value subMoney(MoneyValue rhs) {
		return new MoneyValue(value - rhs.getValue());
	}
	
	// mul
	@Override
	public Value mul(Value rhs) {
		return rhs.mulMoney(this);
	}
	
	@Override
	public Value mulInt(IntValue rhs) {
		return new MoneyValue(value * rhs.getValue());
	}
	
	@Override
	public Value mulMoney(MoneyValue rhs) {
		return new MoneyValue(value * rhs.getValue());
	}
	
	// div
	@Override
	public Value div(Value rhs) {
		return rhs.divMoney(this);
	}
	
	@Override
	public Value divInt(IntValue rhs) {
		return new MoneyValue(value / rhs.getValue());
	}
	
	@Override
	public Value divMoney(MoneyValue rhs) {
		return new MoneyValue(value / rhs.getValue());
	}
	
	// pos
	@Override
	public Value pos() {
		return new MoneyValue(Math.abs(value));
	}
	
	@Override
	// neg
	public Value neg() {
		return new MoneyValue(value * -1);
	}
	
	/* logic operations */
	
	// greater than
	@Override
	public Value gt(Value rhs, boolean gEq) {
		return rhs.gtMoney(this, gEq);
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
		return rhs.ltMoney(this, lEq);
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
	public Double getValue() {
		return this.value;
	}
}
