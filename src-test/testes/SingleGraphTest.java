package testes;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.generator.SingletonRandomGenerator;
import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.test.TestBuilder;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.java.test.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

@GraphWalker(value = "quick_random(edge_coverage(100))", start = "v_inicio")
public class SingleGraphTest extends ExecutionContext implements SingleGraphTestInterface {
	Graph graph = new SingleGraph("graph1");
	int nodesIndex = 0, edgesIndex = 0, lastEdgeCount = 0, lastNodeCount = 0, newNodeCount = 0, newEdgeCount = 0;
	String cameFrom = "";
	boolean jaExistente = false, naoEncontrado = false, arestaRejeitada = false;
	List<String> usedPairs = new ArrayList<>();
	
    public static void removeStringsContainingCharacter(String string, List<String> stringList) {
        List<String> stringsToRemove = new ArrayList<>();

        for (String str : stringList) {
            if (str.contains(string)) {
                stringsToRemove.add(str);
            }
        }

        stringList.removeAll(stringsToRemove);
    }

	public static boolean addEdgesToGraph(Graph graph, int numEdges, int pairCount, List<String> usedPairs) {
		Node[] nodes = graph.nodes().toArray(Node[]::new);
		int numNodes = nodes.length;

		if (numNodes < 2) {
			System.out.println("The graph must have at least two nodes to add edges.");
			return false;
		}

		for (int indexA = 0; indexA < numNodes; indexA++) {
			for (int indexB = 0; indexB < numNodes; indexB++) {
				if (pairCount >= numEdges) {
					System.out.println(pairCount);
					System.out.println("retornando");
					return true;
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
			return false;
		}
		return true;
	}

	@Override
	public void v_ExcecaoIdJaUsado() {
		System.out.println("Running v_ExcecaoIdJaUsado");
		System.out.println(jaExistente);
		Assertions.assertTrue(jaExistente);
	}

	@Override
	public void e_removeAresta() {
		System.out.println("Running e_removeAresta");
		
		try {
			lastEdgeCount = graph.getEdgeCount();
			newEdgeCount = graph.getEdgeCount();
			Edge removed_edge = (Edge) graph.getEdge(usedPairs.get(0));
			System.out.println(removed_edge);
			graph.removeEdge(usedPairs.get(0));
			usedPairs.remove(0);
			edgesIndex = edgesIndex - 1;
			cameFrom = "e_removeAresta";
		} catch (Exception e) {
			naoEncontrado = true;
		}
	}

	@Override
	public void e_removeNoInexistente() {
		System.out.println("Running e_removeNoInexistente");

		try {
			graph.removeNode(100000);
		} catch (Exception e) {
			naoEncontrado = true;
		}
	}

	@Override
	public void e_limpaGrafo() {
		System.out.println("Running e_limpaGrafo");
		graph.clear();
	}

	@Override
	public void v_inicio() {
		System.out.println("Running v_inicio");
	}

	@Override
	public void v_ExcecaoArestaRejeitada() {
		System.out.println("Running v_ExcecaoArestaRejeitada");
		Assertions.assertTrue(arestaRejeitada);
	}

	@Override
	public void v_grafoNaoVazio() {
		System.out.println("Running v_grafoNaoVazio");

		Assertions.assertNotNull(graph.nodes());
		Assertions.assertNotNull(graph.edges());
		switch (cameFrom) {
			case "e_adicionaAresta":
				System.out.println("Adding an edge");
				Assertions.assertEquals(lastEdgeCount + 1, newEdgeCount);
				break;
			case "e_removeAresta":
				System.out.println("Removing an edge");
				Assertions.assertEquals(lastEdgeCount - 1, newEdgeCount);
				break;
			case "e_adicionaNo":
				System.out.println("Adding a node");
				Assertions.assertEquals(lastNodeCount + 1, newNodeCount);
				break;
			case "e_removeNo":
				System.out.println("Removing a node");
				Assertions.assertEquals(lastNodeCount - 1, newNodeCount);
				break;
			default:
				System.out.println("Nao caiu em nenhum caso esperado");
				throw new IllegalArgumentException("Invalid operation: " + cameFrom);
		}
	}

	@Override
	public void e_removeUltimoNo() {
		System.out.println("Running e_removeUltimoNo");
		lastNodeCount = graph.getNodeCount();
		graph.removeNode(0);
		newNodeCount = graph.getNodeCount();
		nodesIndex = nodesIndex - 1;
	}

	@Override
	public void e_adicionaNoExistente() {
		System.out.println("Running e_adicionaNoExistente");

		try {
			graph.addNode("node0");
			graph.addNode("node0");
		} catch (Exception e) {
			jaExistente = true;
		}
	}

	@Override
	public void e_adicionaArestaEmNoInexistente() {
		System.out.println("Running e_adicionaArestaEmNoInexistente");
		Graph graph2 = new MultiGraph("graph2", true, false, 5, 10);
		Node A = graph2.addNode("A");
		Node B = graph2.addNode("B");
		graph2.addEdge("E", A, B);
		System.out.println(graph2.getEdge(0));


		try {
			graph.addEdge("brokeEdge", A, B);
		} catch (Exception e) {
			System.out.println("pegou a excecao de aresta em no inexistente");
			naoEncontrado = true;
		}
	}

	@Override
	public void e_removeNo() {
		System.out.println("Running e_removeNo");
		try {
			lastNodeCount = graph.getNodeCount();
			Node node = graph.getNode(0);
			graph.removeNode(node);
			removeStringsContainingCharacter(node.getId(), usedPairs);
			newNodeCount = graph.getNodeCount();
			nodesIndex = nodesIndex - 1;
			cameFrom = "e_removeNo";
		} catch (Exception e) {
			naoEncontrado = true;
		}
	}

	@Override
	public void e_adicionaArestaEmNoCheio() {
		System.out.println("Running e_adicionaArestaEmNoCheio");
		try {
			graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));
			graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));

		} catch (Exception e) {
			arestaRejeitada = true;
		}
	}

	@Override
	public void e_criaGrafo() {
		System.out.println("Running e_criaGrafo");
		graph = new SingleGraph("graph1", true, false, 5, 10);
		cameFrom = "";
		jaExistente = false; naoEncontrado = false; arestaRejeitada = false;
		usedPairs = new ArrayList<>();
		nodesIndex = 0; edgesIndex = 0; lastEdgeCount = 0; lastNodeCount = 0; newNodeCount = 0; newEdgeCount = 0;

	}

	@Override
	public void e_adicionaNo() {
		System.out.println("Running e_adicionaNo");
		lastNodeCount = graph.getNodeCount();
		graph.addNode("node" + nodesIndex);
		newNodeCount = graph.getNodeCount();
		nodesIndex = nodesIndex + 1;
		cameFrom = "e_adicionaNo";
		System.out.println(lastNodeCount);
		System.out.println(newNodeCount);
		System.out.println(nodesIndex);
	}

	@Override
	public void e_adicionaAresta() {
		System.out.println("Running e_adicionaAresta");
		try {
			lastEdgeCount = graph.getEdgeCount();
			boolean exception = addEdgesToGraph(graph, 1, usedPairs.size(), usedPairs);
			if (!exception) {
				throw new Exception("The graph must have at least two nodes to add edges.");
			}
			newEdgeCount = graph.getEdgeCount();
			edgesIndex = edgesIndex + 1;
			cameFrom = "e_adicionaAresta";
		} catch (Exception e) {
			naoEncontrado = true;
		}
	}

	@Override
	public void v_grafoVazio() {
		System.out.println("Running v_grafoVazio");
		int nodes = graph.getNodeCount();
		int edges = graph.getEdgeCount();

		Assertions.assertEquals(0, nodes);
		Assertions.assertEquals(0, edges);
	}
	
	@Override
	public void e_adicionaArestaExistente() {
		System.out.println("Running e_adicionaArestaExistente");
		try {
			Node A = graph.addNode("aNodeForExceptionA");
			Node B = graph.addNode("aNodeForExceptionB");
			System.out.println("Nao deu excecao1");
			graph.addEdge("AB", A, B);
			System.out.println("Nao deu excecao2");
			graph.addEdge("AB", A, B);
			System.out.println("Nao deu excecao3");

		} catch (Exception e) {
			System.out.println(e);
			jaExistente = true;
		}
	}
	
	@Override
	public void v_ElementoNaoEncontrado() {
		System.out.println("Running v_ElementoNaoEncontrado");
		Assertions.assertTrue(naoEncontrado);
	}
	

	@Test
	public void TestPath() {
		v_inicio();
		e_criaGrafo();
		v_grafoVazio();
		e_removeAresta();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_removeNo();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_removeNo();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_removeNo();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_removeAresta();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaAresta();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_removeAresta();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaArestaEmNoInexistente();
		v_ElementoNaoEncontrado();
		System.out.println("teste");
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_limpaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNo();
		v_grafoNaoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaNoExistente();
		v_ExcecaoIdJaUsado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_limpaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeNoInexistente();
		v_ElementoNaoEncontrado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaAresta();
		v_grafoNaoVazio();
		e_adicionaArestaEmNoCheio();
		v_ExcecaoArestaRejeitada();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeAresta();
		v_grafoNaoVazio();
		e_adicionaNoExistente();
		v_ExcecaoIdJaUsado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_limpaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeUltimoNo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_removeUltimoNo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_limpaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaNoExistente();
		v_ExcecaoIdJaUsado();
		e_criaGrafo();
		v_grafoVazio();
		e_adicionaNo();
		v_grafoNaoVazio();
		e_adicionaAresta();
		v_grafoNaoVazio();
		e_adicionaArestaExistente();
		System.out.println("oioioioioioioioioioio");
		v_ExcecaoIdJaUsado();

	}
}
