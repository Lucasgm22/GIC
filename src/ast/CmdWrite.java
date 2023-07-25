package ast;

import javax.swing.JOptionPane;

import symbols.Identifier;

public class CmdWrite extends AbstractCommand {
	
	private Identifier id;
	private String text;

	public CmdWrite(Identifier id, int indentation) {
		super(indentation);
		this.id = id;
	}
	

	public CmdWrite(String text, int indentation) {
		super(indentation);
		this.text = text;
	}

	@Override
	public String generateJSCode() {
		return "alert(" + (id!=null?id.getText():text) + ");\n";
	}

	@Override
	public String generateJavaCode() {
		return "System.out.println(" + (id!=null?id.getText():text) +");\n";
	}

	@Override
	public String generateCCode() {
		if (id != null) {
			return switch (id.getType()) {
				case INTEGER -> "printf(\"%d\\n\", " + id.getText()+ ");\n";
				case REAL -> "printf(\"%lf\\n\", " + id.getText()+ ");\n";
				case TEXT -> "printf(\"%s\\n\" ," + id.getText()+ ");\n";
			};
		} else {
			return  "printf(\"%s\\n\" ," + text + ");\n";
		}
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, (id!=null? id.show():text));
	}

}
