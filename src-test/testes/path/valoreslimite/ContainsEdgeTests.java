package testes;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContainsEdgeTests {

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
	public void TC4True() {
		System.out.println("Testando TC4True: Aresta válida e presente no caminho\n");
		Edge edgeTrue = edge;
		Assertions.assertTrue(path.contains(edgeTrue));	// Aresta está presente no caminho
	}
	
	@Test
	public void TC4False() {
		System.out.println("Testando TC4False: Aresta válida e não presente no caminho\n");
		Node node = graph.addNode("node");
		Edge edgeFalse = graph.addEdge("edgeFalse", node, node1);
		Assertions.assertFalse(path.contains(edgeFalse));	// Aresta não está presente no caminho
	}
	
	@Test
	public void TC6() {		
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC6: Nó 0 da aresta é nulo\n");
					Edge edgeTest = graph.addEdge("edgeTest", null, node1);
					Assertions.assertFalse(path.contains(edgeTest));
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC7() {		
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC7: Nó 1 da aresta é nulo\n");
					Edge edgeTest = graph.addEdge("edgeTest", node0, null);
					Assertions.assertFalse(path.contains(edgeTest));
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC8True() {
		Assertions.assertThrows(java.lang.AssertionError.class, 
				() -> {
					System.out.println("Testando TC8: Id da aresta é nulo\n");
					Edge edgeTest = graph.addEdge(null, node0, node1);
					Assertions.assertTrue(path.contains(edgeTest));
				},
				"Espera AssertionErrorException\n");
	}
}
