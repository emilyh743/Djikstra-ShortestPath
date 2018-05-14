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
            
            effect:
                  - serial numbering the nodes so that each has an unique id
                  - id number passed must be 0 or greater (in order to hash the string and get one node back)
                  - if added successfully, return true
                  - if already in the graph, return false (name not unique, id not unique)
          
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
                  
             effect: 
                  - serial number an edge so that each has a unique integer id
                  - id number must be 0 or greater
                  - since node labels are unique we will specify an edge by naming the source and destination nodes
                  - label for the edge is optional (user may pass null string; use null label as default)
                  - edge labels do not have to be unique
                  - edge weight is an integer and might be negative
                  - 1 is used as the default weight
                  - only allowing on eedge from any node M to node N (since edges are directed, we might have one edge from M                   to N, and another edge from N to M; we may also have an edge from M to M, itself)
                  - if edge is successfully added then return true, else return false (id num or label problems, edge already                   there)
            
delNode:

            in: string (label for the node to remove)
      
            out: boolean
                  - return false if the node does not exist
                  - return true if the node is found and successfully removed
                  
            effect:
                  - identify node to remove by label (since node names are unique)
                  - find node and remove it from structure of the graph
                  - will also require removing the edges in and out of that node
                  - if node was found and successfully removed then return true, else return false (node was not in the                         graph)
delEdge:

            in: 
                  - string label for source node
                  - string label for destination node
          
            out: boolean
                  - return false if the edge does not exist
                  - return true if the edge is found and successfully removed
                  
            effect:
                  - identify the edge to remove by passing in labels for the source and destination nodes (since we are only                     allowing only one such edge)
                  - removing an edge will not require removing any nodes (a set of nodes with no edges is still a valid                         graph... just unconnected)
                  - if the edge is found and successfully removed then return true, else return false

numNodes:

            in: nothing
      
            return: integer 0 or greater
            
            effect: reports how many nodes are in the graph
numEdges:

            in: nothing
            
            return: integer 0 or greater

            effect: reports how many edges are in the graph
