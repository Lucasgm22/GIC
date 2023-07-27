package ast;

public abstract class AbstractCommand {

	private final int indentation;
	private static final String STR_INDENTATION = "    ";


	protected AbstractCommand(int indentation) {
		this.indentation = indentation;
	}

	public final String generateCode(TargetLang target) {
		return switch (target) {
			case JS -> generateJSCode();
			case JAVA -> generateJavaCode();
			case C -> generateCCode();
			case ALL -> throw new IllegalStateException("ALL does not generate code");
		};
	}

	public abstract String generateJSCode();

	public abstract String generateJavaCode();

	public abstract String generateCCode();

	public abstract void run(Program program);

	protected String getIndentationByTarget(TargetLang targetLang) {
		return STR_INDENTATION.repeat(indentation + getDefaultIndentation(targetLang));
	}

	private int getDefaultIndentation(TargetLang target) {
		return switch (target) {
			case JAVA -> 2;
			case C -> 1;
			case JS -> 0;
			case ALL -> throw new IllegalStateException("ALL does not generate code");
		};
	}
}
