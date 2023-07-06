package main;

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
import java.util.concurrent.TimeUnit;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

@GraphWalker(value = "quick_random(edge_coverage(100))", start = "v_inicio")
public class SingleGraphTest extends ExecutionContext implements SingleGraph {
	Graph graph = new SingleGraph("graph1", true, false, 5, 10);;
	int nodesIndex = 0, edgesIndex = 0, lastEdgeCount = 0, lastNodeCount = 0, newNodeCount = 0, newEdgeCount = 0;
	String cameFrom = "";
	boolean jaExistente = false, naoEncotrado = false, arestaRejeitada = false;
	Set<String> usedPairs = new HashSet<>();
	usedPairs.size()

	public static void addEdgesToGraph(Graph graph, int numEdges, int pairCount) {
		Node[] nodes = graph.nodes().toArray(Node[]::new);
		int numNodes = nodes.length;

		if (numNodes < 2) {
			System.out.println("The graph must have at least two nodes to add edges.");
			return;
		}

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


	@Override
	public void v_ExcecaoIdJaUsado() {
		System.out.println("Running v_ExcecaoIdJaUsado");
		Assertions.assertTrue(jaExistente);
		jaExistente = false;
	}

	@Override
	public void e_removeAresta() {
		System.out.println("Running e_removeAresta");
		int lastNodeCount = graph.getEdgeCount();
		int newEdgeCount = graph.getEdgeCount();
		edgesIndex = edgesIndex - 1;
		cameFrom = "e_removeAresta";
	}

	@Override
	public void e_removeNoInexistente() {
		System.out.println("Running e_removeNoInexistente");

		try {
			graph.removeNode(100000);
		} catch (Exception e) {
			jaExistente = true;
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
		// Your implementation here
	}

	@Override
	public void v_ExcecaoArestaRejeitada() {
		System.out.println("Running v_ExcecaoArestaRejeitada");
		Assertion.assertTrue(arestaRejeitada);
	}

	@Override
	public void v_grafoNaoVazio() {
		System.out.println("Running v_grafoNaoVazio");
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
				throw new IllegalArgumentException("Invalid operation: " + cameFrom);
		}
	}

	@Override
	public void e_removeUltimoNo() {
		System.out.println("Running e_removeUltimoNo");
		int lastEdgeCount = graph.getNodeCount();
		graph.removeNode(0);
		int newEdgeCount = graph.getNodeCount();
		nodesIndex = nodesIndex - 1;
	}

	@Override
	public void e_adicionaNoExistente() {
		System.out.println("Running e_adicionaNoExistente");

		try {
			graph.addEdge("node0");
		} catch (Exception e) {
			naoEncotrado = true;
		}
	}

	@Override
	public void e_adicionaArestaEmNoInexistente() {
		System.out.println("Running e_adicionaArestaEmNoInexistente");
		Graph graph2 = new SingleGraph("graph2", true, false, 5, 10);
		Node A = graph2.addNode("A");
		Node B = graph2.addNode("B");

		try {
			graph.addEdge("brokeEdge", A, B);
		} catch (Exception e) {
			jaExistente = true;
		}
	}

	@Override
	public void e_removeNo() {
		System.out.println("Running e_removeNo");
		int lastNodeCount = graph.getNodeCount();
		graph.removeNode("node" + nodesIndex);
		int newNodeCount = graph.getNodeCount();
		nodesIndex = nodesIndex - 1;
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
		jaExistente = false;
		naoEncotrado = false;
		arestaRejeitada = false;
		usedPairs = new HashSet<>();

	}

	@Override
	public void e_adicionaNo() {
		System.out.println("Running e_adicionaNo");
		int lastNodeCount = graph.getNodeCount();
		graph.addNode("node" + nodesIndex);
		int newNodeCount = graph.getNodeCount();
		nodesIndex = nodesIndex + 1;

		Assertions.assertEquals(init + 1, end);
	}

	@Override
	public void e_adicionaAresta() {
		System.out.println("Running e_adicionaAresta");
		int lastEdgeCount = graph.getEdgeCount();
		addEdgesToGraph(graph, 1, usedPairs.size());
		int newEdgeCount = graph.getEdgeCount();
		edgesIndex = edgesIndex + 1;
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
			graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));
			graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));

		} catch (Exception e) {
			jaExistente = true;
		}
	}

	@Override
	public void v_ElementoNaoEncontrado() {
		System.out.println("Running v_ElementoNaoEncontrado");
		Assertions.assertTrue(naoEncotrado);
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
		v_ExcecaoIdJaUsado();

	}
}
