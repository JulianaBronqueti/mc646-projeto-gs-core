package testes.path.valoreslimite;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContainsNodeTests {
	Path path = null;
	Graph graph = null;
	Node node0 = null;
	Node node1 = null;
	Edge edge = null;
	
	@BeforeEach
	void setUp() throws Exception {
		path = new Path();
		graph = new SingleGraph("graph");
		
		// Inicializando nós e arestas
		node0 = graph.addNode("node0");
		node1 = graph.addNode("node1");
		edge = graph.addEdge("edge1", node0, node1);
		
		// Adicionando no caminho
		path.add(node0, edge);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void TC1True() {
		System.out.println("Testando TC1: Nó válido e presente no caminho\n");
		Assertions.assertTrue(path.contains(node0));	// Nó está presente no caminho
	}
	
	@Test
	public void TC1False() {
		System.out.println("Testando TC1: Nó válido e não presente no caminho\n");
		Node node = graph.addNode("node");
		Assertions.assertFalse(path.contains(node));	// Nó não está presente no caminho
	}
	
//	@Test
//	public void TC3() {		
//		Assertions.assertThrows(java.lang.AssertionError.class, 
//				() -> {
//					System.out.println("Testando TC3: Id do node é nulo\n");
//					Node node = graph.addNode(null);
//					Assertions.assertFalse(path.contains(node));
//				},
//				"Espera AssertionError\n");
//	}
}
