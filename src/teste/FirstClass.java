package teste;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

import org.graphstream.graph.Path;

public class FirstClass {
	
	private static String styleSheet =
            "node {"+
            "   size-mode: dyn-size;"+
            "   shape: circle;"+
            "   size: 20px;"+
            "   fill-mode: plain;"+
            "   fill-color: #CCC;"+
            "   stroke-mode: plain;"+
            "   stroke-color: black;"+
            "   stroke-width: 1px;"+
            "}";

	public static void main(String[] args) {
		Graph graph = new SingleGraph("graph");
		
		// Visualização do grafo
//		System.setProperty("org.graphstream.ui", "swing"); 
//		graph.display();
//		graph.setAttribute("ui.stylesheet", styleSheet);
		
		Node A = graph.addNode("A");
		Node B = graph.addNode("B");
		Node C = graph.addNode("C");
		Node D = graph.addNode("D");
		Node E = graph.addNode("E");
		Node F = graph.addNode("F");
//
		Edge AB = graph.addEdge("AB", "A", "B");
		Edge BC = graph.addEdge("BC", "B", "C");
		Edge CA = graph.addEdge("CA", "C", "A");
		Edge CE = graph.addEdge("CE", "C", "E");		
		Edge AD = graph.addEdge("AD", "A", "D");
		Edge DE = graph.addEdge("DE", "D", "E");
		Edge DF = graph.addEdge("DF", "D", "F");
		Edge EF = graph.addEdge("EF", "E", "F");
		
		// Atributo peso
		AB.setAttribute("peso", 1.0);
		BC.setAttribute("peso", 1.0);
		CA.setAttribute("peso", 3.0);
		CE.setAttribute("peso", 4.0);
		AD.setAttribute("peso", 5.0);
		DE.setAttribute("peso", 6.0);
		DF.setAttribute("peso", 7.0);
		EF.setAttribute("peso", 8.0);
		
//		graph.setStrict(false);
//		graph.setAutoCreate(true);

//		System.out.printf("Arestas: %s\n\n", graph.edges().toArray()[0]);
//        
//        for (Node node : graph) {
//            node.setAttribute("ui.label", node.getId());
//        }
		
		//////// Path
		Path path = new Path();
		
		// Testando método equals.
//		Path pathDif = new Path();
//		System.out.printf("Caminho diferente: %s\n\n", path.equals(pathDif));
//		Path pathEq = path;
//		System.out.printf("Caminho igual: %s\n\n", path.equals(pathEq));
//		Path pathNull = null;
//		System.out.printf("Caminho igual: %s\n\n", path.equals(pathNull));
//		System.out.printf("Caminho igual: %s\n\n", path.equals("string"));
		
//		path.setRoot(A);
//		Path pathDif = new Path();
//		System.out.printf("Caminho diferente: %s\n\n", path.equals(pathDif));
//		Path pathEq = path;
//		System.out.printf("Caminho igual: %s\n\n", path.equals(pathEq));
		
//		path.add(AB);
		
//		Node root = path.getRoot();		
//		System.out.printf("Raiz: %s\n\n", root.toString());
		
//		int tamanho = path.size();
//		System.out.printf("Tamanho: %d\n\n", tamanho);
		
		
//		// Adicionando raiz
//		System.out.printf("Antes %s\n\n", path.getNodePath().toString());
		// Nó nulo
//		path.setRoot(null);
//		// No com id nulo 
//		Node semId = graph.addNode(null);
//		path.setRoot(semId);
//		// No com id numérico
//		Node idNum = graph.addNode(123);
		
//		System.out.printf("Depois %s\n", path.getNodePath().toString());
//		System.out.printf("Raiz %s\n\n", path.getRoot());
//		
//		Edge semIdA = graph.addEdge("semIdA", null, "B");
//		path.add(semIdA);
//		System.out.printf("Depois %s\n\n", path.getNodePath().toString());
		
//		path.setRoot(A);
//		System.out.printf("Vazio? %s\n\n", path.empty());
//		
//		path.setRoot(B);
//		System.out.printf("Raiz: %s\n\n", path.getRoot());
		
//		
//		// Adicionando aresta 
		path.setRoot(A);
		path.add(AB);
		path.add(BC);
		path.add(CA);
		path.add(AD);
		path.add(DE);
		path.add(EF);
		
		// Peso
//		System.out.printf("Peso: %.1f\n\n", path.getPathWeight("peso"));
//		System.out.printf("Peso: %.1f\n\n", path.getPathWeight(""));
//		System.out.printf("Peso: %.1f\n\n", path.getPathWeight(null));
//		System.out.printf("Peso: %.1f\n\n", path.getPathWeight(123));
		
		// Contem
//		System.out.printf("Contém nulo: %s\n\n", path.contains(null));
//		Node noIdNulo = graph.addNode(null);
//		System.out.printf("Contém nó com id nulo: %s\n\n", path.contains(noIdNulo));
//		Edge semIdC = graph.addEdge("semIdA", "C", null);
//		path.add(semIdC);
//		System.out.printf("Contém nó com id nulo: %s\n\n", path.contains(semIdC));
//		System.out.printf("Antes %s\n", path.getEdgePath().toString());
//		Edge idNulo = graph.addEdge(null, "C", "A");
//		path.add(idNulo);
//		System.out.printf("Depois %s\n", path.getEdgePath().toString());
//		System.out.printf("Contém nó com id nulo: %s\n\n", path.contains(idNulo));
//		Edge idNum = graph.addEdge(123, "C", "A");
		
		// add
//		System.out.printf("Ultima aresta %s\n\n", path.peekEdge().getId());
//		path.add(null);
//		path.add(graph.addEdge("id1", "C", null));
//		path.add(graph.addEdge(null, "C", "A"));
//		System.out.printf("Ultima aresta %s\n\n", path.peekEdge().getId());
//		path.add(graph.addEdge(123, "C", "A"));
//		System.out.printf("Ultima aresta %s\n\n", path.peekEdge().getId());
		
		// No origem igual a no destino
//		Edge AA = graph.addEdge("AA", "A", "A");
//		path.add(AA);
//		
//		// Transformar em string
//		System.out.printf("toString(): %s\n\n", path.toString());
//		
//		// Apagar path
//		path.clear();
//		System.out.printf("Vazio? %s\n\n", path.empty());
//		System.out.printf("toString(): %s\n\n", path.toString());
//		
//		
//		// Verificar lista de nós
//		System.out.printf("Contagem de nós: %d\n", path.getNodeCount());		
//		System.out.printf("Lista de nós: %s\n\n", path.getNodePath().toString());
//		
//		// Verificar lista de arestas
//		System.out.printf("Contagem de arestas: %d\n", path.getEdgeCount());
//		System.out.printf("Lista de arestas: %s\n\n", path.getEdgePath().toString());
//		
//		// Verificando últimos valores da pilha
//		Edge lastEdge = path.peekEdge();
//		System.out.printf("Última aresta: %s\n\n", lastEdge.getId());
//		
//		Node lastNode = path.peekNode();
//		System.out.printf("Último nó: %s\n\n", lastNode.getId());	
//		
//		// Remover
//		Path pathCopy1 = path.getACopy();
//		Edge removedEdge = pathCopy1.popEdge();
//		System.out.printf("Aresta removida: %s\n\n", removedEdge.getId());	
//		System.out.printf("Caminho removendo última aresta: %s\n\n", pathCopy1.toString());	
//		
//		Path pathCopy2 = path.getACopy();
//		Node removedNode = pathCopy2.popNode();
//		System.out.printf("Nó removido: %s\n\n", removedNode.getId());
//		System.out.printf("Caminho removendo último nó: %s\n\n", pathCopy2.toString());
	
	}

}
