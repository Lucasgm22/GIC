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
		return id.getText() + " = " + "prompt(\"Type you input\");\n";
	}

	@Override
	public String generateJavaCode() {
		return switch (id.getType()) {
			case REAL -> id.getText() + " = " + "sc.nextDouble();\n";
			case INTEGER -> id.getText() + " = " + "sc.nextInt();\n";
			case TEXT -> id.getText() + " = " + "sc.nextLine();\n";
		};
	}

	@Override
	public String generateCCode() {
		return  switch (id.getType()) {
			case REAL -> "scanf(\"%lf\", &" + id.getText() + ");\n";
			case INTEGER -> "scanf(\"%d\", &" + id.getText() + ");\n";
			case TEXT -> "scanf(\"%s\", &" + id.getText() + ");\n";
		};
	}

	@Override
	public void run() {
		id.setValue(Integer.parseInt(JOptionPane.showInputDialog("Type Your Input")));
	}

}
