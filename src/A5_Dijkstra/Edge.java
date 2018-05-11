package A5_Dijkstra;

public class Edge {
	// ** SOURCE - Generate Getters and Setters (quick method)
	// need label, idNum, weight, need to know the starting node and ending node
	// (where it points to)
	long idNum;
	long weight;
	String sLabel;
	String dLabel;
	String eLabel;

	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.weight = weight;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.eLabel = eLabel;

	}
}
