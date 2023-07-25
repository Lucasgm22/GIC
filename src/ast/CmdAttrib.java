package ast;

import expressions.AbstractExpression;
import symbols.DataType;
import symbols.Identifier;
import util.StringUtil;

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
		return StringUtil.indentationByTarget(getIndentation(), TargetLang.JS) +
				id.getText() + " = " +
				getAttribuition() +
				";\n";
	}

	@Override
	public String generateJavaCode() {
		return StringUtil.indentationByTarget(getIndentation(), TargetLang.JAVA) +
				id.getText() + " = " + getAttribuition() + ";\n";
	}

	@Override
	public String generateCCode() {
		if (!DataType.TEXT.equals(id.getType())) {
			return StringUtil.indentationByTarget(getIndentation(), TargetLang.C) +
					id.getText() + " = " + getAttribuition() + ";\n";
		} else {
			return StringUtil.indentationByTarget(getIndentation(), TargetLang.C) +
					"strcpy(" + id.getText() + ", " + content +");\n";
		}
	}

	private String getAttribuition() {
		return DataType.TEXT.equals(id.getType()) ? content : expr.toString();
	}
	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	@Override
	public void run() {
		if (DataType.TEXT.equals(id.getType())) {
			id.setValueText(content);
		} else {
			id.setValue(expr.eval());
		}
	}

}
