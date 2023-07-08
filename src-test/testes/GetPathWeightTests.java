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

public class GetPathWeightTests {
	
	Graph graph = new SingleGraph("graph");
	
	Node A = graph.addNode("A");
	Node B = graph.addNode("B");
	Node C = graph.addNode("C");
	Node D = graph.addNode("D");
	Node E = graph.addNode("E");
	Node F = graph.addNode("F");

	Edge AB = graph.addEdge("AB", "A", "B");
	Edge BC = graph.addEdge("BC", "B", "C");
	Edge CA = graph.addEdge("CA", "C", "A");		
	Edge AD = graph.addEdge("AD", "A", "D");
	Edge DE = graph.addEdge("DE", "D", "E");
	Edge EF = graph.addEdge("EF", "E", "F");
	
	Path path = null;
	
	@BeforeEach
	void setUp() throws Exception {
		path = new Path();
		path.add(A, AB);
		path.add(BC);
		path.add(CA);
		path.add(AD);
		path.add(DE);
		path.add(EF);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void TC12() {
		System.out.println("Testando TC12: characteristic é string (\"peso\") e existe em todas as arestas do caminho, sendo todos os valores double\n");
		// Atributo peso
		AB.setAttribute("peso", 1.0);
		BC.setAttribute("peso", 2.0);
		CA.setAttribute("peso", 3.0);
		AD.setAttribute("peso", 4.0);
		DE.setAttribute("peso", 5.0);
		EF.setAttribute("peso", 6.0);
		Assertions.assertEquals(21.0, path.getPathWeight("peso"));
	}
	
	@Test
	public void TC13() {		
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC13: characteristic é null\n");
					path.getPathWeight(null);
				},
				"Espera NullPointerException\n");
	}
	
	@Test
	public void TC14() {		
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC14: characteristic é string vazia\n");
					// Atributo peso
					AB.setAttribute("peso", 1.0);
					BC.setAttribute("peso", 2.0);
					CA.setAttribute("peso", 3.0);
					AD.setAttribute("peso", 4.0);
					DE.setAttribute("peso", 5.0);
					EF.setAttribute("peso", 6.0);					
					path.getPathWeight("");
				},
				"Espera NullPointerException\n");
	}	
	
	@Test
	public void TC15() {		
		Assertions.assertThrows(java.lang.NullPointerException.class, 
				() -> {
					System.out.println("Testando TC15: characteristic não existe em todas as arestas do caminho\n");
					// Atributo peso
					AB.setAttribute("peso", 1.0);
					BC.setAttribute("peso", 2.0);
//					CA.setAttribute("peso", 3.0);	// Falta essa aresta
					AD.setAttribute("peso", 4.0);
					DE.setAttribute("peso", 5.0);
					EF.setAttribute("peso", 6.0);					
					path.getPathWeight("peso");
				},
				"Espera NullPointerException\n");
	}
	

}
