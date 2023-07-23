package expressions;

import symbols.DataType;
import symbols.Identifier;

public class IDExpression extends AbstractExpression{
	private String id;
	private Double    value;


	public IDExpression(Identifier identifier) {
		id = identifier.getText();
		value = identifier.getValue();
	}
	public double eval() {
		return this.value;
	}

	@Override
	public String toString() {
		return id;
	}
}
