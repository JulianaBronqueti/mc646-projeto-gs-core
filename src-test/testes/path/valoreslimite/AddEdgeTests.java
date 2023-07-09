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

public class AddEdgeTests {
	
	Path path = null;
	Graph graph = null;
	
	@BeforeEach
	void setUp() throws Exception {
		path = new Path();
		graph = new SingleGraph("graph");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void TC4() {
		System.out.println("Testando TC4: Aresta válida\n");
		Node node0 = graph.addNode("node0");
		Node node1 = graph.addNode("node1");
		Edge edge = graph.addEdge("edge1", node0, node1);
		path.setRoot(node0);
		path.add(edge);
		Assertions.assertTrue(path.peekEdge().equals(edge));	// Última aresta colocada é edge
	}

	@Test
	public void TC5() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC5: Aresta nula\n");
					Node node0 = graph.addNode("node0");
					path.setRoot(node0);
					path.add(null);
				},
				"Espera NullPointerException\n");
	}

	@Test
	public void TC6() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC6: Nó 0 da aresta nulo\n");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", null, node1);
					path.setRoot(node1);
					path.add(edge);
				},
				"Espera NullPointerException\n");
	}

	@Test
	public void TC7() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC7: Nó 1 da aresta nulo\n");
					Node node0 = graph.addNode("node0");
					Edge edge = graph.addEdge("edge1", node0, null);
					path.setRoot(node0);
					path.add(edge);
				},
				"Espera NullPointerException\n");
	}

	@Test
	public void TC8() {
		Assertions.assertThrows(java.lang.AssertionError.class, 
				() -> {
					System.out.println("Testando TC8: Aresta com id nulo\n");
					Node node0 = graph.addNode("node0");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge(null, node0, node1);
					path.setRoot(node0);
					path.add(edge);
				},
				"Espera AssertionError\n");
	}

	@Test
	public void TCExtra() {
		Assertions.assertThrows(java.lang.IllegalArgumentException.class, 
				() -> {
					System.out.println("Testando TCExtra: Adicionar aresta válida sem ter raiz\n");
					Node node0 = graph.addNode("node0");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(edge);
				},
				"Espera IllegalArgumentException\n");
	}


}
