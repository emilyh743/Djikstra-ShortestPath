package A5_Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DiGraph implements DiGraph_Interface {

	// create hashset for idNum HERE - for finding duplicates:
	HashSet<Long> idNumHSet = new HashSet<Long>();
	// HashSet<Edge> EdgeHSet = new HashSet<Edge>();
	HashSet<Edge> EdgeIdHSet = new HashSet<Edge>();
	HashSet<Long> LongEdgeIdHSet = new HashSet<Long>();

	// create hashmap for label and node HERE:
	public HashMap<String, Node> HMap = new HashMap<String, Node>(); // for all
	// nodes
	int numNodes;
	int numEdges;

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
		String label;
		// Node node;
		// String label = new String();
		// Node node = new Node(idNum, label);
		// id for edge
		long EdgeID;
	}

	@Override
	public boolean addNode(long idNum, String label) {
		// CHECK OVER addNode**

		// unique: no other nodes with same id and label
		if (idNumHSet.contains(idNum) || HMap.containsKey(label) || idNum < 0) {
			return false;
		} else {
			// code add node into graph here:
			// add to hashmap
			HMap.put(label, new Node(idNum, label)); // adds node
			// HMap.put(label, new ArrayList<String>());
			// add label, new node in constructor
			idNumHSet.add(idNum); // put idNum in idNumHSet
			numNodes++; // increment counter for nodes // instantiate at the top
			return true;
		}
	}

	@Override
	// sLabel = source label, dLabel = destination label, weight = weight for
	// new edge (use 1 by default), eLabel =label for the new edge (allow null)
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// everytime addEdge is called successfully, increment the node attached
		// to that dLabel
		Node snode = HMap.get(sLabel); // get sLabel
		Node dnode = HMap.get(dLabel); // get dLabel
		// if already edge between two nodes
		// check here!!!! (if statement)

		// check if Edge with a certain idNum is contained in set of Edges
		if (LongEdgeIdHSet.contains(idNum) || idNum < 0 || !HMap.containsKey(sLabel) || !HMap.containsKey(dLabel)) {
			// System.out .println("duplicate edge");
			return false;
		}
		// for (Edge edge : EdgeIdHSet) {
		// if (edge.idNum == idNum) // edge.idNum is all edges
		// return false;
		// }
		// if (HMap.containsKey(sLabel) && HMap.containsKey(dLabel)) {
		// checking for duplicate edges here
		if (dnode.incomingHMap.containsKey(sLabel)) {
			return false;
		}
		if (snode.outgoingHMap.containsKey(dLabel)) {
			return false;
		}

		Edge edge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
		dnode.incomingHMap.put(sLabel, edge); // insert new edge
		snode.outgoingHMap.put(dLabel, edge);
		snode.outdegree++;
		dnode.indegree++;
		EdgeIdHSet.add(edge); // update edge hashset
		LongEdgeIdHSet.add(edge.idNum); // update edgeID hashset (Long)
		numEdges++; // added at end of office hours. instantiate at top
		return true;
	}

	@Override
	public boolean delNode(String label) {
		// create instance variable for numNodes here
		Node nodeHMap = HMap.get(label); // get the node from HMap of nodes

		if (!HMap.containsKey(label)) { // if hashmap contains node
			return false;
		} else { // if delete node is successful
			// (go through label of node = label on right now)
			// go through each node in hashmap, and if label of current node =
			// the argument, go through both inEdge and outEdge in that single
			// node and then delete all edges (sLabel, dLabel) from that node
			// for (Node node : HMap.values()) {// gives me node in hashmap

			for (Edge inEdges : nodeHMap.incomingHMap.values()) {
				// delete all edges (sLabel, dLabel from that node)
				delEdge(inEdges.sLabel, inEdges.dLabel);

			}
			for (Edge outEdges : nodeHMap.outgoingHMap.values()) {
				delEdge(outEdges.sLabel, outEdges.dLabel);
			}
			// remove node Id from nodeidhashset
			idNumHSet.remove(HMap.get(label).idNum);
			HMap.remove(label);
			numNodes--; // decrement numNodes; need to instantiate at the top
			return true;
		}

	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// don't need to remove any nodes, just edges
		Node snode = HMap.get(sLabel); // get sLabel
		Node dnode = HMap.get(dLabel); // get dLabel
		Edge myEdge = null;

		if (!HMap.containsKey(sLabel) || !HMap.containsKey(dLabel)) {
			return false;
		}
		// first step: finding the edge so you can delete
		for (Edge edge : EdgeIdHSet) {
			if (edge.sLabel == sLabel && edge.dLabel == dLabel) {
				// found edge to delete. now store in edge variable
				myEdge = edge;
				break;
			}
		}
		if (myEdge == null) { // checking value of myEdge
			return false;
		}
		// shouldn't be any edges in here
		dnode.incomingHMap.remove(sLabel, myEdge); // insert new edge
		snode.outgoingHMap.remove(dLabel, myEdge);
		snode.outdegree--;
		dnode.indegree--;
		numEdges--; // decrement edges; need to instantiate at the top
		// delete/remove from EdgeIdHSet and then delete from setId
		EdgeIdHSet.remove(myEdge);
		LongEdgeIdHSet.remove(myEdge.idNum);
		return true;
	}

	@Override
	public long numNodes() {
		return numNodes;
	}

	@Override
	public long numEdges() {
		return numEdges;
	}

	@Override
	public String[] topoSort() {
		// use a queue to loop through vertices when searching for next indegree
		// = 0 node
		// elements of array are node labels
		// go through hashmap, , and add those to the queue, then start your
		// while loop

		Queue<String> nodesQueue = new LinkedList<>();
		ArrayList<String> nodeArray = new ArrayList<>();

		for (Node n : HMap.values()) { // go through all your nodes
			// check if any indegree = 0
			if (n.indegree == 0) {
				nodesQueue.add(n.label); // adds nodes to queue
			}
		}

		// inside while loop, take first thing in queue and add to array at the
		// end and save all adjacent edges, delete them, then check adjacent
		// edges, then add them to the queue, then start over

		while (nodesQueue.isEmpty() == false) { // should keep going until queue
												// is not empty
			String b = nodesQueue.poll(); // take first thing off queue
			nodeArray.add(HMap.get(b).label); // adds first thing from queue to
												// array

			// loop to get outgoing
			for (String o : HMap.get(b).outgoingHMap.keySet()) { // HMap.get(b)
																	// is A
				// o will give you B and then C
				HMap.get(o).indegree--; // dec indegree of B and C

				if (HMap.get(o).indegree == 0) { // add to queue if node's
													// indegree is 0
					nodesQueue.add(o); // add label o to queue
				}
			}
		}
		// check cycles at the beginning and at the end
		// at the end, you should have all nodes that should be in array, check
		// size of solution and check if it is same size of original graph, this
		// means that it is the same, if not, you are missing some stuff.

		// has a cycle: case in which number points back to the previous number
		// (4 -> 6 but also 4 <- 6 )
		// check if size of arraylist is equal to size of all the nodes

		if (numNodes == nodeArray.size()) { // valid
			String[] result = nodeArray.toArray(new String[numNodes]);
			return result;
		} else {
			return null; // if numNodes != size of nodeArray
		}

	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		// look at Dijkstra's Single Source Shortest Path (Weighted Shortest
		// Path)
		// set starting distance (to infinity)
		// put A in queue, pop off A, put in output list, look at A's outgoing
		// edges and for each edge give shortest path distance
		// pop nodes that have no indegree
		// then pop b off and put in list. then 5+4 and then update C if C is
		// less than 10.

		Node start = HMap.get(label); // starting node
		EntryPair entry = new EntryPair(label, 0); // start entry is at 0
		start.distance = 0; // set starting node distance to 0
		MinBinHeap shortPathQueue = new MinBinHeap();
		ShortestPathInfo[] solution = new ShortestPathInfo[numNodes]; // array
																		// for
																		// solution

		// create shortestpathinfo subject
		shortPathQueue.insert(entry);

		while (shortPathQueue.size() != 0) { // while queue is not empty

			Node n = HMap.get(shortPathQueue.getMin().value); // pop out one
																// with
																// smaller

			long d = shortPathQueue.getMin().priority; // grab value of the
														// distance
			shortPathQueue.delMin(); // delmin from minbinheap
			// check if n is known
			if (n.known) // k if temp is known then get out of while loop
				continue;
			n.known = true; // mark n as known
			// add temp.known to solution here:
			// loop through and grab neighbors and assign distance
			for (Edge e : n.outgoingHMap.values()) {
				Node a = HMap.get(e.dLabel);
				if (a.known) // check known
					continue;
				// gets destination label from HMap if current path is not
				// better than the path we just got from new edge, then we
				// update it to better path.

				if (a.distance > d + e.weight) { // found "better" edge
					a.distance = n.distance + e.weight;
					shortPathQueue.insert(new EntryPair(a.label, e.weight));
				}

			}
		}
		int counter = 0;
		// for each through vertices....load up solution[counter] with a new
		// ShortestInfo
		for (Node n : HMap.values()) {
			if (n.distance == Long.MAX_VALUE)
				n.distance = -1;
			solution[counter] = new ShortestPathInfo(n.label, n.distance);
			counter++;
		}
		return solution;
	}
}