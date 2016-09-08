package org.uva.sea.ql.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private final String identifier; 
	// edges implemented as a list of neighbors.
	private List<String> neighbors;
	
	public Vertex(String identifier) {
		neighbors = new ArrayList<String>();
		this.identifier = identifier;
	}
	
	public void addNeighbor(String vID) {
		neighbors.add(vID);
	}
	
	public List<String> getNeighbors() {
		return neighbors;
	}

	public boolean isTerminal() {
		return neighbors.size() == 0;
	}

	public String getIdentifier() {
		return this.identifier;
	}

}
