package ast;

import javax.swing.JOptionPane;

import symbols.Identifier;

public class CmdRead extends AbstractCommand {
	
	private Identifier id;
	
	public CmdRead(Identifier id, int indentation) {
		super(indentation);
		this.id = id;
	}

	@Override
	public String generateJSCode() {
		return super.generateJSCode() + id.getText() + " = " + "prompt(\"Type you input\");\n";
	}

	@Override
	public String generateJavaCode() {
		String content = switch (id.getType()) {
			case REAL -> id.getText() + " = " + "sc.nextDouble();\n";
			case INTEGER -> id.getText() + " = " + "sc.nextInt();\n";
			case TEXT -> id.getText() + " = " + "sc.nextLine();\n";
		};

		return super.generateJavaCode().concat(content);
	}

	@Override
	public String generateCCode() {
		String content = switch (id.getType()) {
			case REAL -> "scanf(\"%lf\", &" + id.getText() + ");\n";
			case INTEGER -> "scanf(\"%d\", &" + id.getText() + ");\n";
			case TEXT -> "scanf(\"%s\", &" + id.getText() + ");\n";
		};

		return super.generateCCode().concat(content);
	}

	@Override
	public void run() {
		id.setValue(Integer.parseInt(JOptionPane.showInputDialog("Type Your Input")));
	}

}
