package org.uva.sea.ql.gui;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.form.ReachableQuestionsMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.VisibilityChecker;
import org.uva.sea.ql.visit.Visitor;

public class FormDataManager {
	private List<FormObserver> observers;
	private ValueMap valueMap;
	private ReachableQuestionsMap visibilityMap;
	private ASTNode root;
	
	public FormDataManager(ValueMap valueMap, ReachableQuestionsMap visibilityMap, ASTNode root) {
		observers = new ArrayList<FormObserver>();
		this.valueMap = valueMap;
		this.visibilityMap = visibilityMap;
		this.root = root;
	}
	
	public ValueMap getValueState() {
		return this.valueMap;
	}
	
	public ReachableQuestionsMap getVisibilityState() {
		return this.visibilityMap;
	}
	
	public void updateValueState(String identifier, Value value) {
		valueMap.updateValueInMap(identifier, value);
		notifyAllObserversOnValue();
		updateVisibilityState();
	}
	
	public void updateVisibilityState() {
		// set visibility map to false for every value.
		visibilityMap.setEveryValueToFalse();
		
		// when visiting a reachable question, set value in map to true.
		Visitor<ReachableQuestionsMap> v = new VisibilityChecker(valueMap);
		root.accept((VisibilityChecker) v, visibilityMap);
		notifyAllObserversOnVisibility();
	}
	
	public void attach(FormObserver observer) {
		observers.add(observer);
	}
	
	public void notifyAllObserversOnValue() {
		for (FormObserver observer : observers) {
			observer.update(valueMap);
		}
	}
	
	public void notifyAllObserversOnVisibility() {
		for (FormObserver observer : observers) {
			observer.update();
		}
	}
	
}
