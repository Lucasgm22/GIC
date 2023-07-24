package ast;

import util.StringUtil;

public abstract class AbstractCommand {

	private final int indentation;

	protected AbstractCommand(int indentation) {
		this.indentation = indentation;
	}

	public final String generateCode(TargetLang target) {
		return switch (target) {
			case JS -> generateJSCode();
			case JAVA -> generateJavaCode();
			default -> throw new IllegalArgumentException("Cant convert to this target");
		};
	}

	public String generateJSCode() {
		return StringUtil.getIndentationLvl(indentation);
	}

	public String generateJavaCode() {
		return StringUtil.getIndentationLvl(indentation + 2);
	}
	public abstract void run();

	public int getIndentation() {
		return indentation;
	}
}
