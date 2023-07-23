package ast;

import expressions.AbstractExpression;
import symbols.Identifier;

public class CmdAttrib extends AbstractCommand {

	private Identifier id;
	private AbstractExpression expr;
	
	@Override
	public String generateJSCode() {
		return id.getText() + " = " + expr.toString() + ";\n";
	}

	@Override
	public String generateJavaCode() {
		return id.getText() + " = " + expr.toString() + ";\n";
	}

	public CmdAttrib(Identifier id, AbstractExpression expr) {
		super();
		this.id = id;
		this.expr = expr;
	}

	public CmdAttrib(Identifier id) {
		super();
		this.id = id;
	}

	public CmdAttrib() {
		super();
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	public AbstractExpression getExpr() {
		return expr;
	}

	public void setExpr(AbstractExpression expr) {
		this.expr = expr;
	}

	@Override
	public void run() {
		id.setValue(expr.eval());
	}

}
