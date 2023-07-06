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
	int nodesIndex = 0, edgesIndex = 0;
	Set<String> usedPairs = new HashSet<>();

    @Vertex()
    void v_ExcecaoIdJaUsado();

    @Edge()
    void e_removeAresta() {
    	int init = graph.getEdgeCount();
    	removeEdges();//TODO
    	int end = graph.getEdgeCount();
    	edgesIndex = edgesIndex - 1;
    	
    	Assertions.assertEquals(init-1, end);
    }

    @Edge()
    void e_removeNoInexistente() {
    	
    	try {
    		graph.removeNode(100000);
    	} catch (Exception e) {
    		//TODO
    	}
    }

    @Edge()
    void e_limpaGrafo() {
    	graph.clear();
    }

    @Vertex()
    void v_inicio();

    @Vertex()
    void v_ExcecaoArestaRejeitada();

    @Vertex()
    void v_grafoNaoVazio();

    @Edge()
    void e_removeUltimoNo() {
    	int init = graph.getNodeCount();
    	graph.addNode("node" + nodesIndex);
    	int end = graph.getNodeCount();
    	nodesIndex = nodesIndex + 1;
    	
    	Assertions.assertEquals(init-1, end);
    }

    @Edge()
    void e_adicionaNoExistente() {
    	try {
    		graph.addEdge("node0");
    	} catch (Exception e) {
    		//TODO
    	}
    }

    @Edge()
    void e_adicionaArestaEmNoInexistente() {
    	Graph graph2 = new SingleGraph("graph2", true, false, 5, 10);
    	Node A = graph2.addNode("A");
    	Node B = graph2.addNode("B");
    	
    	try {
    		graph.addEdge("brokeEdge", A, B);
    	} catch (Exception e) {
    		//TODO
    	}
    }

    @Edge()
    void e_removeNo() {
    	int init = graph.getNodeCount();
    	graph.addNode("node" + nodesIndex);
    	int end = graph.getNodeCount();
    	nodesIndex = nodesIndex + 1;
    	
    	Assertions.assertEquals(init-1, end);
    }

    @Edge()
    void e_adicionaArestaEmNoCheio() {
    	try {
    		graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));
    		graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));
    		
    	} catch (Exception e){
    		//TODO
    	}
    }

    @Edge()
    void e_criaGrafo() {
    	graph = new SingleGraph("graph1", true, false, 5, 10);
    }
    

    @Edge()
    void e_adicionaNo() {
    	int init = graph.getNodeCount();
    	graph.addNode("node" + nodesIndex);
    	int end = graph.getNodeCount();
    	nodesIndex = nodesIndex + 1;
    	
    	Assertions.assertEquals(init+1, end);
    }

    @Edge()
    void e_adicionaAresta() {
    	int init = graph.getEdgeCount();
    	addEdges();//TODO
    	int end = graph.getEdgeCount();
    	edgesIndex = edgesIndex + 1;
    	
    	Assertions.assertEquals(init+1, end);
    }

    @Vertex()
    void v_grafoVazio() {
    	int nodes = graph.getNodeCount();
    	int edges = graph.getEdgeCount();
    	
    	Assertions.assertEquals(0, nodes);
    	Assertions.assertEquals(0, edges);
    }

    @Edge()
    void e_adicionaArestaExistente() {
    	try {
    		graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));
    		graph.addEdge("exception1", graph.getNode(0), graph.getNode(1));
    		
    	} catch (Exception e){
    		//TODO
    	}
    }

    @Vertex()
    void v_ElementoNaoEncontrado();
}
