package org.uva.sea.ql.gui.widget;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.gui.FormDataManager;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;

public class ComputedQuestionWidget extends Widget {
	private ComputedQuestion compQuestion;
	JLabel label;
	JTextField resultField;
	
	public ComputedQuestionWidget(ComputedQuestion compQuestion, 
									Box container, 
									FormDataManager dataManager) {
		super.dataManager = dataManager;
		this.compQuestion = compQuestion;
		initialize(container);
	}
	
	private void initialize(Box container) {
		String labelText = compQuestion.getLabel();
		//remove quotes
		labelText = labelText.substring(1, labelText.length()-1);
		label = new JLabel(labelText);
		resultField = new JTextField("", 10);
		resultField.setMaximumSize(new Dimension(100,20));
		resultField.setEditable(false);
		
		attachToPanel(container);
	}
	
	public ComputedQuestion getCompQuestion() {
		return this.compQuestion;
	}
	
	@Override
	protected void attachToPanel(Box b) {
		Box horBox = Box.createHorizontalBox();
		horBox.add(label);
		horBox.add(Box.createHorizontalGlue());
		horBox.add(resultField);
		b.add(horBox);
	}

	@Override
	public void update(ValueMap valueMap) {
		Value oldValue = valueMap.getValueFromMap(compQuestion.getIdentifier());
		Value newValue = compQuestion.getExpr().eval(valueMap);	
		if (!oldValue.getValue().equals(newValue.getValue())) {
			dataManager.updateValueState(compQuestion.getIdentifier(), newValue);
		}
		else if (!newValue.equals(new UndefinedValue())) {
			String result = String.valueOf(newValue.getValue());
			resultField.setText(result);
		} else {
			resultField.setText("");
		}
	}

	@Override
	public void update() {
		boolean isVisible = dataManager.getVisibilityState().getValueFromMap(compQuestion.getIdentifier());
		label.setVisible(isVisible);
		resultField.setVisible(isVisible);
	}
}
