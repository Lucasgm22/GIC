package ast;

import util.StringUtil;

public abstract class AbstractCommand {

	private final int indentation;

	protected AbstractCommand(int indentation) {
		this.indentation = indentation;
	}

	public final String generateCode(TargetLang target) {
		String compiledCommand = switch (target) {
			case JS -> generateJSCode();
			case JAVA -> generateJavaCode();
			case C -> generateCCode();
		};

		return StringUtil.indentCommand(compiledCommand, indentation, target);
	}

	public abstract String generateJSCode();

	public abstract String generateJavaCode();

	public abstract String generateCCode();

	public abstract void run();
}
