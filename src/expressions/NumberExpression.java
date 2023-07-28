package expressions;

import symbols.DataType;

public class NumberExpression implements AbstractExpression {
	
	private double numberValue;

	private DataType type;
	
	public NumberExpression(double numberValue, DataType type) {
		super();
		this.numberValue = numberValue;
		this.type = type;
	}

	public NumberExpression() {
		super();
	}


	@Override
	public double eval() {
		return numberValue;
	}
	
	public String toString() {
		if (DataType.REAL.equals(type)) {
			return String.valueOf(numberValue);
		} else {
			if (numberValue < 0) {
				return "(" + Math.round(numberValue) + ")";
			}
			return String.valueOf(Math.round(numberValue));
		}
	}

}
