# Djikstra-ShortestPath

Djikstra's Algorithm for weighted single source shortest path (digraph).

Graph ADT class that contains the data structures for representing graph structure and content; provides methods to efficiently build and manipulate that structure and content. The graph has directed edges and weights on both edges and labels.

A DIGRAPH will provide this collection of operations:

addNode:
      
            in: 
                  - unique id number of the node (0 or greater)
                  - string for name
                  - you might want to generate the unique number automatically but this operation allows you to specify any                       integer
                  - both id number and label must be unique
          
            return: boolean
                  - returns false if node number is not unique, or less than 0
                  - returns false if label is not unique (or is null)
                  - returns true if node is successfully added 
          
addEdge:

            in: 
                  - unique id number for the new edge, 
                  - label of source node,
                  - label of destination node,
                  - weight for new edge (use 1 by default)
                  - label for the new edge (allow null)
          
            return: boolean
                  - returns false if edge number is not unique or less than 0
                  - returns false if source node is not in graph
                  - returns false if destination node is not in graph
                  - returns false is there already is an edge between these 2 nodes
                  - returns true if edge is successfully added 
            
delNode:

            in: string (label for the node to remove)
      
            out: boolean
                  - return false if the node does not exist
                  - return true if the node is found and successfully removed
delEdge:

            in: 
                  - string label for source node
                  - string label for destination node
          
            out: boolean
                  - return false if the edge does not exist
                  - return true if the edge is found and successfully removed
numNodes:

            in: nothing
      
            return: integer 0 or greater (reports how many nodes are in the graph)
numEdges:

            in: nothing
            return: integer 0 or greater (reports how many edges are in the graph)
