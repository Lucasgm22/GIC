package ast;

import javax.swing.JOptionPane;

import symbols.Identifier;

public class CmdRead extends AbstractCommand {
	
	private Identifier id;
	
	public CmdRead(Identifier id) {
		super();
		this.id = id;
	}
	public CmdRead() {
		super();
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
	public void run() {
		id.setValue(Integer.parseInt(JOptionPane.showInputDialog("Type Your Input")));
	}

}
