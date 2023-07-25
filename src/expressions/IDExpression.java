package expressions;

import symbols.Identifier;

public class IDExpression extends AbstractExpression{
	private Identifier id;


	public IDExpression(Identifier id) {
		this.id = id;
	}
	public double eval() {
		return id.getValue();
	}

	@Override
	public String toString() {
		return id.getText();
	}
}
