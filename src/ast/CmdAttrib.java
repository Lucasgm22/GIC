package ast;

import expressions.AbstractExpression;
import symbols.DataType;
import symbols.Identifier;

public class CmdAttrib extends AbstractCommand {

	private Identifier id;
	private AbstractExpression expr;
	private String content;

	public CmdAttrib(Identifier id, AbstractExpression expr, int indentation) {
		super(indentation);
		this.id = id;
		this.expr = expr;
	}

	public CmdAttrib(Identifier id, String content, int indentation) {
		super(indentation);
		this.id = id;
		this.content = content;
	}

	@Override
	public String generateJSCode() {
		return getIndentationByTarget(TargetLang.JS) +
				id.getText() + " = " +
				getAttribuition() +
				";\n";
	}

	@Override
	public String generateJavaCode() {
		return getIndentationByTarget(TargetLang.JAVA) +
				id.getText() + " = " + getAttribuition() + ";\n";
	}

	@Override
	public String generateCCode() {
		if (!DataType.TEXT.equals(id.getType())) {
			return getIndentationByTarget(TargetLang.C) +
					id.getText() + " = " + getAttribuition() + ";\n";
		} else {
			return getIndentationByTarget(TargetLang.C) +
					"strcpy(" + id.getText() + ", \""+ content +"\");\n";
		}
	}

	private String getAttribuition() {
		return DataType.TEXT.equals(id.getType()) ? ("\"" + content+ "\"") : expr.toString();
	}
	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	@Override
	public void run(Program program) {
		if (DataType.TEXT.equals(id.getType())) {
			id.setValueText(content);
		} else {
			id.setValue(expr.eval());
		}
		program.updateContent();
	}

}
