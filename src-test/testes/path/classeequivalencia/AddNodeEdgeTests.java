package testes.path.classeequivalencia;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddNodeEdgeTests {
	
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
		System.out.println("Testando TC1: Nó 0 da aresta é igual ao nó origem\n");
		Node node0 = graph.addNode("node0");
		Node node1 = graph.addNode("node1");
		Edge edge = graph.addEdge("edge1", node0, node1);
		path.add(node0, edge);
		Assertions.assertTrue(path.peekNode().equals(node1));	// Último nó colocado é o que não é o from (node1)
		Assertions.assertTrue(path.peekEdge().equals(edge));	// Última aresta colocada é edge
	}
	
	@Test
	public void TC2() {
		System.out.println("Testando TC2: Nó 1 da aresta é igual ao nó origem\n");
		Node node0 = graph.addNode("node0");
		Node node1 = graph.addNode("node1");
		Edge edge = graph.addEdge("edge1", node0, node1);
		path.add(node1, edge);
		Assertions.assertTrue(path.peekNode().equals(node0));	// Último nó colocado é o que não é o from (node0)
		Assertions.assertTrue(path.peekEdge().equals(edge));	// Última aresta colocada é edge
	}
	
	@Test
	public void TC3() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC3: Aresta é nula\n");
					Node node0 = graph.addNode("node0");
					path.add(node0, null);
				},
				"Espera NullPointerException\n");
	}
	
//	@Test
//	public void TC4() {
//		Assertions.assertThrows(java.lang.AssertionError.class, 
//				() -> {
//					System.out.println("Testando TC4: Nó origem é nulo, mas nós da aresta não são\n");
//					Node node0 = graph.addNode("node0");
//					Node node1 = graph.addNode("node1");
//					Edge edge = graph.addEdge(null, node0, node1);
//					path.add(node0, edge);
//				},
//				"Espera AssertionError\n");
//	}
	
	@Test
	public void TC5() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC5: Nó 0 da aresta é nulo\n");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", null, node1);
					path.add(node1, edge);
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC6() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC6: Nó 1 da aresta é nulo\n");
					Node node0 = graph.addNode("node0");
					Edge edge = graph.addEdge("edge1", node0, null);
					path.add(node0, edge);
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC7() {
		Assertions.assertThrows(java.lang.IllegalArgumentException.class, 
				() -> {
					System.out.println("Testando TC7: Nó origem não pertence à aresta\n");
					Node node0 = graph.addNode("node0");
					Node node1 = graph.addNode("node1");
					Node nodeFrom = graph.addNode("nodeFrom");
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(nodeFrom, edge);
				},
				"Espera IllegalArgumentException\n");
	}
	
	@Test
	public void TC8() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC8: Nó origem é nulo e nó 0 da aresta é igual ao nó origem\n");
					Node nodeFrom = null;
					Node node0 = nodeFrom;
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(nodeFrom, edge);
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC9() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC9: Nó origem é nulo e nó 1 da aresta é igual ao nó origem\n");
					Node nodeFrom = null;
					Node node0 = graph.addNode("node0");
					Node node1 = nodeFrom;
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(nodeFrom, edge);
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC10() {
		Assertions.assertThrows(java.lang.IllegalArgumentException.class, 
				() -> {
					System.out.println("Testando TC10: Nó origem é nulo e aresta também\n");
					path.add(null, null);
				},
				"Espera IllegalArgumentException\n");
	}

	@Test
	public void TC12() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC12: Nó origem é nulo e nó 0 da aresta também\n");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", null, node1);
					path.add(null, edge);
				},
				"Espera IllegalArgumentException\n");
	}
	
	@Test
	public void TC13() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC13: Nó origem é nulo e nó 1 da aresta também\n");
					Node node0 = graph.addNode("node0");
					Edge edge = graph.addEdge("edge1", node0, null);
					path.add(null, edge);
				},
				"Espera IllegalArgumentException\n");
	}
	
	@Test
	public void TC14() {
		Assertions.assertThrows(java.lang.IllegalArgumentException.class, 
				() -> {
					System.out.println("Testando TC14: Nó origem é nulo e nenhum dos nós da aresta é igual ao nó origem\n");
					Node node0 = graph.addNode("node0");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(null, edge);
				},
				"Espera IllegalArgumentException\n");
	}
}
