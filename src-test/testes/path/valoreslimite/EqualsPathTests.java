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

public class EqualsPathTests {
	
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
	public void TC9PVazioTrue() {
		System.out.println("Testando TC9PVazioTrue: Tanto path quanto p estão instanciados, mas vazios\n");
		Path p = new Path();
		Assertions.assertTrue(path.equals(p));
	}

	@Test
	public void TC9PVazioFalse() {
		System.out.println("Testando TC9PVazioFalse: Tanto path quanto p estão instanciados, mas path não está vazio e p está\n");
		Node node0 = graph.addNode("node0");
		path.setRoot(node0);
		Path p = new Path();
		Assertions.assertFalse(path.equals(p));
	}

	@Test
	public void TC9PRaizTrue() {
		System.out.println("Testando TC9PRaizTrue: Tanto path quanto p tem a mesma raiz, e só isso\n");
		Node node0 = graph.addNode("node0");
		path.setRoot(node0);
		Path p = path.getACopy();
		Assertions.assertTrue(path.equals(p));
	}

	@Test
	public void TC9PRaizFalse() {
		System.out.println("Testando TC9PRaizFalse: path tem raiz node0 e p tem raiz node1\n");
		Node node0 = graph.addNode("node0");
		path.setRoot(node0);
		Path p = new Path();
		Node node1 = graph.addNode("node1");
		p.setRoot(node1);
		Assertions.assertFalse(path.equals(p));
	}

	@Test
	public void TC9PArestaTrue() {
		System.out.println("Testando TC9PArestaTrue: path e p tem mesma raiz e mesma aresta\n");
		Node node0 = graph.addNode("node0");
		Node node1 = graph.addNode("node1");
		Edge edge = graph.addEdge("edge1", node0, node1);
		path.add(node0, edge);
		Path p = path.getACopy();
		Assertions.assertTrue(path.equals(p));
	}

	@Test
	public void TC9PArestaIdDifFalse() {
		Assertions.assertThrows(org.graphstream.graph.EdgeRejectedException.class, 
				() -> {
					System.out.println("Testando TC9PArestaIdDifFalse: path e p tem mesma raiz node0, mas aresta diferente por do id desta. Não é permitido adicionar a mesma aresta com id diferente.\n");
					Node node0 = graph.addNode("node0");
					Node node1 = graph.addNode("node1");
					Edge edge = graph.addEdge("edge1", node0, node1);
					path.add(node0, edge);
					
					Path p = new Path();
					Edge edgeDif = graph.addEdge("edgeDif", node0, node1);
					p.add(node0, edgeDif);
				},
				"Espera org.graphstream.graph.EdgeRejectedException\n");
	}

	@Test
	public void TC9PArestaNodeDifFalse() {
		System.out.println("Testando TC9PArestaNodeDifFalse: path e p tem mesma raiz node0, mas aresta diferente por conta de um dos nós\n");
		Node node0 = graph.addNode("node0");
		Node node1 = graph.addNode("node1");
		Edge edge = graph.addEdge("edge1", node0, node1);
		path.add(node0, edge);
		
		Path p = new Path();
		Node nodeDif = graph.addNode("nodeDif");
		Edge edgeDif = graph.addEdge("edgeDif", node0, nodeDif);
		p.add(node0, edgeDif);
		
		Assertions.assertFalse(path.equals(p));
	}

	@Test
	public void TC10() {
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC10: path só é instanciado e p é nulo\n");
					Path p = null;
					path.equals(p);
				},
				"Espera NullPointerException\n");
	}

	@Test
	public void TC11() {
		System.out.println("Testando TC10: path só é instanciado e p não é da classe Path, e sim string\n");
		Assertions.assertFalse(path.equals("string"));
	}


}
