package ast;

public abstract class AbstractCommand {

	private final int indentation;

	protected AbstractCommand(int indentation) {
		this.indentation = indentation;
	}

	public final String generateCode(TargetLang target) {
		return switch (target) {
			case JS -> generateJSCode();
			case JAVA -> generateJavaCode();
			case C -> generateCCode();
		};
	}

	public abstract String generateJSCode();

	public abstract String generateJavaCode();

	public abstract String generateCCode();

	public abstract void run();

	public int getIndentation() {
		return indentation;
	}
}
