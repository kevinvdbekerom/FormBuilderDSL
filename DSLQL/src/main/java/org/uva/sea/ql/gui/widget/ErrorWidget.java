package org.uva.sea.ql.gui.widget;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ErrorWidget {
	JScrollPane scrollPane;
	JTextArea errorPane;
	
	public ErrorWidget() {
		errorPane = new JTextArea("");
		scrollPane = new JScrollPane(errorPane);
		scrollPane.setBounds(10,60,780,500);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	public void addErrorMessageToErrorPane(String message) {
		String text = errorPane.getText();
		text += message + "\n";
		errorPane.setText(text);
	}
}
