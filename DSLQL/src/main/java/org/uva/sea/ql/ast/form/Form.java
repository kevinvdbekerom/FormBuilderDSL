package org.uva.sea.ql.ast.form;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.visit.QuestionCollector;

public class Form {
	private String name;
	private Block mainBlock;
	private ValueMap valueMap;
	private List<Question> questions;

	public Form(String name, Block block) {
		this.name = name;
		this.mainBlock = block;
		questions = new ArrayList<Question>();
		questions.addAll(QuestionCollector.getQuestions(mainBlock, true));
		initializeValueMap();
	}

	public void initializeContext() {
		
	}
	
	public ValueMap getValueMap() {
		return this.valueMap;
	}

	public String getName() {
		return name;
	}

	public Block getMainBlock() {
		return this.mainBlock;
	}

	private void initializeValueMap() {
		valueMap = new ValueMap();
		for (Question question : questions) {
			valueMap.putValueInMap(question.getIdentifier(), new UndefinedValue());
		}
	}
	
	public List<Question> getQuestions() {
		return this.questions;
	}

}
