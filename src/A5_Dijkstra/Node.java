package A5_Dijkstra;

import java.util.HashMap;

public class Node {
	// all you need for node
	public HashMap<String, Edge> incomingHMap = new HashMap<String, Edge>();
	public HashMap<String, Edge> outgoingHMap = new HashMap<String, Edge>();
	public int outdegree = 0;
	public int indegree = 0;
	public String label;
	public long idNum;

	// public ArrayList<Edge> inEdges;
	// public ArrayList<Edge> outEdges;
	public long distance = Long.MAX_VALUE;
	public boolean known;

	public Node(long idNum, String label) { // if problems check here*
		this.label = label;
		this.idNum = idNum;
		// but need in degree - how many edges are pointing to it
		int addEdgeCount = 0; // counter for addEdge
		// keep list/hashmap for all the edges (where the edges are pointing to)
		// this.inEdges = new ArrayList<Edge>();
		// this.outEdges = new ArrayList<Edge>();
		long distance = -1; // initially set to infinity
		known = false;
	}

}
