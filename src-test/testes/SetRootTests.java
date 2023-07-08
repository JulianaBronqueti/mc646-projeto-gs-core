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

public class SetRootTests {

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
	public void TC1() {
		System.out.println("Testando TC1: raiz tem id como string\n");
		Node node = graph.addNode("id1");
		path.setRoot(node);
		Assertions.assertTrue(path.getRoot().equals(node));	// Raiz é o node
	}
	
	@Test
	public void TC2SemExcecao() {
		System.out.println("Testando TC2SemExcecao: node é nulo, mas path não tem adição de aresta\n");
		path.setRoot(null);
		Assertions.assertNull(path.getRoot());	// Raiz é nula
	}
	
	@Test
	public void TC2ComExcecao() {
		Assertions.assertThrows(java.lang.IllegalArgumentException.class, 
				() -> {
					System.out.println("Testando TC2ComExcecao: node é nulo e path tem adição de aresta\n");
					path.setRoot(null);
					Node node0 = graph.addNode("node0");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(edge);
				},
				"Espera IllegalArgumentException\n");
	}
	
	@Test
	public void TC3() {
		Assertions.assertThrows(java.lang.AssertionError.class, 
				() -> {
						System.out.println("Testando TC3: node tem id nulo\n");
						Node node = graph.addNode(null);
						path.setRoot(node);
						Assertions.assertNull(path.getRoot());	// Raiz é nula
				},
				"Espera AssertionError\n");
	}
	
	@Test
	public void TC4() {
		System.out.println("Testando TC4: nó do caminho já foi definido\n");
		Node node0 = graph.addNode("node0");
		path.setRoot(node0);
		// Redefinição
		Node node1 = graph.addNode("node1");
		path.setRoot(node1);
		Assertions.assertTrue(path.getRoot().equals(node0));
	}
}
