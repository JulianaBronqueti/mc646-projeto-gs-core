package main;

import java.util.Random;
import java.util.HashSet;
import java.util.Set;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class MainExecution {
	
	public static void addNodesToGraph(Graph graph, int n) {
	    for (int i = 0; i < n; i++) {
	        Node node = graph.addNode("Node " + (i + 1));
	        // Faça o que for necessário com o nó adicionado
	    }
	}
	
    public static void addEdgesToGraph(Graph graph, int numEdges) {
        Node[] nodes = graph.nodes().toArray(Node[]::new);
        int numNodes = nodes.length;

        if (numNodes < 2) {
            System.out.println("The graph must have at least two nodes to add edges.");
            return;
        }

        Set<String> usedPairs = new HashSet<>();
        int pairCount = 0;

        for (int indexA = 0; indexA < numNodes; indexA++) {
            for (int indexB = 0; indexB < numNodes; indexB++) {
                if (pairCount >= numEdges) {
                	System.out.println(pairCount);
                	System.out.println("retornando");
                    return;
                }

                String pair1 = indexA + "-" + indexB;
                String pair2 = indexB + "-" + indexA;
                System.out.println("tentando aresta entre");
                System.out.println(pair1);
                System.out.println(pair2);

                // Ensure that both pairs are unique and haven't been used before
                if (!usedPairs.contains(pair1) && !usedPairs.contains(pair2)) {
                    usedPairs.add(pair1);

                    Node nodeA = nodes[indexA];
                    Node nodeB = nodes[indexB];

                    String edgeName = "edge" + pairCount;
                    graph.addEdge(edgeName, nodeA, nodeB);
                    System.out.println("aresta entre");
                    System.out.println(nodeA);
                    System.out.println(nodeB);

                    pairCount++;
                }
                
                System.out.println(indexB);
            }
            System.out.println(indexA);
        }
        
        if (numEdges > pairCount) {
        	System.out.println("numero de arestas eh impossivel sem quebrar o grafo");
        }
    }

    public static void addEdgesToNode(Graph graph, Node node, int numEdges) {
        Node[] nodes = graph.nodes().toArray(Node[]::new);
        int numNodes = nodes.length;

        if (numNodes < 2) {
            System.out.println("The graph must have at least two nodes to add edges.");
            return;
        }

        Random random = new Random();

        for (int i = 0; i < numEdges; i++) {
            String edgeName = "edge" + i;
            int randomIndex = random.nextInt(numNodes);
            Node randomNode = nodes[randomIndex];
            graph.addEdge(edgeName, node, randomNode);
        }
    }
	
	public static void main(String args[]) {
		System.setProperty("org.graphstream.ui", "swing");
		
		Graph graph = new SingleGraph("Tutorial 1", true, false, 5, 10);
		Node A = graph.addNode("oi");
		Node B = graph.addNode("B");
		
		
		Graph graph2 = new SingleGraph("Tutorial 1", true, false, 5, 10);
		Node C = graph2.addNode("C");
		Node D = graph2.addNode("D");

	
		Edge AB = graph.addEdge("edgeAB", A, B);
		Edge CD = graph2.addEdge("edgeAB", C, D);
		System.out.println(graph2.getEdgeCount());
		//graph2.removeEdge(AB);
		System.out.println(graph2.getEdgeCount());
		graph2.removeNode(A);
		System.out.println(graph2.getEdgeCount());
		System.out.println(graph2.getNodeCount());

		//Node F = graph2.addNode("F");
		//addEdgesToGraph(graph, 20);
		//System.out.println(graph.getEdgeCount());
		
		/*graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");
		
		for(Node n:graph) {
			System.out.println(n.getId());
		}
		
		System.out.println(A.toString());
		
		graph.edges().forEach(e -> {
			System.out.println(e.getId());
		});
		
		Graph graph2 = new MultiGraph("Tutorial 1");

		graph2.addNode("A");
		graph2.addNode("B");
		graph2.addNode("C");
		graph2.addEdge("a", "A", "B");
		graph2.addEdge("b", "A", "B");
		graph2.addEdge("c", "A", "B");
		graph2.addEdge("d", "B", "A");
		graph2.addEdge("e", "B", "C");
		graph2.addEdge("f", "C", "A");
		
		for(Node n:graph2) {
			System.out.println(n.getId());
		}
		
		graph2.edges().forEach(e -> {
			System.out.println(e.getId());
		});
		
		
		Path path = new Path();
		
		path.setRoot(graph2.getNode(0));
		path.add(graph2.getNode(0), graph2.getEdge(0));
		path.add(graph2.getNode(1), graph2.getEdge(4));
		path.add(graph2.getNode(2), graph2.getEdge(5));

		
		System.out.println(path.getNodeCount());*/
		
	}
}