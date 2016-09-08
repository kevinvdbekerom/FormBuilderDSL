package org.uva.sea.ql.experiment;

public class ObserverTest {

	public static void main(String[] args) {
		
		
		// create state
		DataObject d = new DataObject();
		d.dataMap.put("A", "a");
		d.dataMap.put("B", "a");
		d.dataMap.put("C", "aa");
		
		DataSubject subA = new DataSubject("A", d);
		DataSubject subB = new DataSubject("B", d);
		DataSubject subC = new DataSubject("C", d);
		
		// add observers to dataobject
		d.attach(subA);
		d.attach(subB);
		d.attach(subC);
		
		// create dependencies
		subA.manager.attach(subB);
		subA.manager.attach(subC);
		
		subB.manager.attach(subC);
		subB.subjects.add(subA);
		
		subC.subjects.add(subA);
		subC.subjects.add(subB);
		
		System.out.println("Round 0");
		for (DataSubject s : subC.subjects) {
			System.out.println(s.getID());
			System.out.println( "has value in map ");
			System.out.println(subC.subject.dataMap.get(s.getID()));
		}
		
		System.out.println("Round 1");
		d.updateState("A", "b");
		
	}

}
