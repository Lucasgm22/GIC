package ast;

import expressions.AbstractExpression;
import symbols.Identifier;

public class CmdAttrib extends AbstractCommand {

	private Identifier id;
	private AbstractExpression expr;

	public CmdAttrib(Identifier id, AbstractExpression expr, int indentation) {
		super(indentation);
		this.id = id;
		this.expr = expr;
	}

	public CmdAttrib(Identifier id, int indentationLvl) {
		super(indentationLvl);
		this.id = id;
	}
	
	@Override
	public String generateJSCode() {
		return super.generateJSCode() + id.getText() + " = " + expr.toString() + ";\n";
	}

	@Override
	public String generateJavaCode() {
		return super.generateJavaCode() + id.getText() + " = " + expr.toString() + ";\n";
	}

	@Override
	public String generateCCode() {
		return super.generateCCode() + id.getText() + " = " + expr.toString() + ";\n";
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
