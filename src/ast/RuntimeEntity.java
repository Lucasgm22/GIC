package ast;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import symbols.Identifier;

public class RuntimeEntity extends JFrame {
	
	private final JTextArea txtContent;
	public RuntimeEntity() {
		setTitle("Runtime Entity Program Status ");
		setBounds(0, 0, 600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		txtContent = new JTextArea();
		txtContent.setBounds(10, 10, 500, 500);
		getContentPane().add(txtContent);
		setVisible(true);
		
	}
	
	public void updateContent(Collection<Identifier> values) {
		StringBuilder strBuilder = new StringBuilder();
		values.forEach(i -> strBuilder.append(i).append("\n"));
		txtContent.setText(strBuilder.toString());
	}

	public void close() {
		dispose();
	}

}
