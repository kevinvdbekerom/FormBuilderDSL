package org.uva.sea.ql.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	private Set<Vertex> vertices;
	
	public Graph() {
		vertices = new HashSet<Vertex>();
	}
	
	public void addEdge(String from, String to) {
		
		for (Vertex v : vertices) {
			if (v.getIdentifier().equals(from)) {
				v.addNeighbor(to);
			}
		}
		
	}
	
	public boolean containsVertex(String vertexID) {
		
		for (Vertex v : vertices) {
			if (v.getIdentifier().equals(vertexID)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public Vertex getVertex(String identifier) {
		for (Vertex v : vertices) {
			if (v.getIdentifier().equals(identifier)) {
				return v;
			}
		}
		
		return null; // vertex does not exist.
	}

	public Set<Vertex> getVertices() {
		return vertices;
	}
	
	public void resetGraph() {
		vertices.removeAll(vertices);
	}
	
	public void printEdges() {
		System.out.println("Printing edges: ");
		if (vertices.size() == 0) {
			System.out.println("Something went wrong!!!: ");
		}
		
		for (Vertex v : vertices) {
			
			for (String edge : v.getNeighbors()) {
				System.out.println("Edge: (" + v.getIdentifier() + " " + edge + ") \n");
			}
			
		}
		
	}
	
	
}
