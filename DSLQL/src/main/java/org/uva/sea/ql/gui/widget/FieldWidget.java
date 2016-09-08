package org.uva.sea.ql.gui.widget;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.FormDataManager;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;

public class FieldWidget extends QuestionWidget {
	JTextField inputField;
	JLabel label;

	public FieldWidget(Question question, 
						Box container, 
						FormDataManager dataManager) {
		super(question, dataManager);
		initialize(container);
	}
	
	private void initialize(Box container) {
		inputField = new JTextField("", 10);
		inputField.setMaximumSize(new Dimension(100,20));
		String labelText = getQuestion().getLabel();
		//remove quotes
		labelText = labelText.substring(1, labelText.length()-1);
		label = new JLabel(labelText);
		
		//add isTrue listener
		inputField.addKeyListener(new KeyListener() {
	        public void keyTyped(KeyEvent keyEvent) {
	        	//
	        }

			public void keyPressed(KeyEvent keyEvent) {
				//
			}

			public void keyReleased(KeyEvent keyEvent) {
				Value value = getQuestion().getType().parse(inputField.getText());
	        	if (value.equals(new UndefinedValue())) {
	        		inputField.setText("");
	        		dataManager.updateValueState(getQuestion().getIdentifier(), value);
	        	} else {
	        		dataManager.updateValueState(getQuestion().getIdentifier(), value);
	        	}
			}
	    });
		
		attachToPanel(container);
	}
	
	@Override
	protected void attachToPanel(Box b) {
		Box horBox = Box.createHorizontalBox();
		horBox.add(label);
		horBox.add(Box.createHorizontalGlue());
		horBox.add(inputField);
		b.add(horBox);
	}

	@Override
	public void update(ValueMap valueMap) {}

	@Override
	public void update() {
		boolean isVisible = dataManager.getVisibilityState().getValueFromMap(getQuestion().getIdentifier());
		label.setVisible(isVisible);
		inputField.setVisible(isVisible);
	}

}
