package org.uva.sea.ql;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.ReachableQuestionsMap;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.FormDataManager;
import org.uva.sea.ql.gui.QlGUI;
import org.uva.sea.ql.visit.VisibilityChecker;
import org.uva.sea.ql.visit.Visitor;

public class QL {
	private Form form;
	private FormDataManager dataManager;
	
	public QL(String fileLocation) {
		ASTBuilder builder = new ASTBuilder();
		try {
			form = builder.buildAST(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataManager = new FormDataManager(form.getValueMap(), 
					initializeReachableQuestionsMap(), 
					form.getMainBlock());
	}

	private ReachableQuestionsMap initializeReachableQuestionsMap() {
		List<String> qIdentifiers = new ArrayList<String>();
		
		for (Question question : form.getQuestions()) {
			qIdentifiers.add(question.getIdentifier());
		}
		
		ReachableQuestionsMap visibilityMap = new ReachableQuestionsMap(qIdentifiers);
		
		Visitor<ReachableQuestionsMap> v = new VisibilityChecker(form.getValueMap());
		form.getMainBlock().accept((VisibilityChecker) v, visibilityMap);
		
		return visibilityMap;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QL ql = new QL("D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt");
					QlGUI frame = new QlGUI(ql.form, ql.dataManager);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
