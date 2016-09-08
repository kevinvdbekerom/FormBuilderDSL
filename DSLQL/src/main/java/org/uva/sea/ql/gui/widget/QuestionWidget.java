package org.uva.sea.ql.gui.widget;

import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.FormDataManager;

public abstract class QuestionWidget extends Widget {
	private Question question;
	
	public QuestionWidget(Question question, FormDataManager dataManager) {
		super.dataManager = dataManager;
		this.question = question;
	}
	
	public Question getQuestion() {
		return this.question;
	}
}
