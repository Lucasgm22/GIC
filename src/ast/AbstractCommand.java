package ast;

public abstract class AbstractCommand {

	public final String generateCode(TargetLang target) {
		return switch (target) {
			case JS -> generateJSCode();
			case JAVA -> generateJavaCode();
			default -> throw new IllegalArgumentException("Cant convert to this target");
		};
	}

	public abstract String generateJSCode();

	public abstract String generateJavaCode();
	public abstract void run();
}
