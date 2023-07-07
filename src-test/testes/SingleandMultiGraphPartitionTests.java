package testes;

import org.junit.jupiter.api.Test;

import com.ibm.icu.impl.Assert;

import static org.junit.jupiter.api.Assertions.*;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class SingleandMultiGraphPartitionTests {
    // Tests for the class SingleGraph constructor
    @Test
    public void testSingleGraphEmptyId() {
        Graph graph = new SingleGraph("", true, true, 10, 5);
        assertEquals("", graph.getId());
    }

    @Test
    public void testSingleGraphNonEmptyId() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertEquals("Graph1", graph.getId());
    }

    @Test
    public void testSingleGraphStrictCheckingTrue() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isStrict());
    }

    @Test
    public void testSingleGraphStrictCheckingFalse() {
        Graph graph = new SingleGraph("Graph1", false, true, 10, 5);
        assertFalse(graph.isStrict());
    }

    @Test
    public void testSingleGraphAutoCreateTrue() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isAutoCreationEnabled());
    }

    @Test
    public void testSingleGraphAutoCreateFalse() {
        Graph graph = new SingleGraph("Graph1", true, false, 10, 5);
        assertFalse(graph.isAutoCreationEnabled());
    }

    @Test
    public void testSingleGraphNegativeInitialNodeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, -10, 5);
        assertTrue(graph.getNodeCount() >= 0);
    }

    @Test
    public void testSingleGraphZeroInitialNodeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 0, 5);
        assertEquals(0, graph.getNodeCount());
    }

    @Test
    public void testSingleGraphPositiveInitialNodeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getNodeCount());
    }

    @Test
    public void testSingleGraphNegativeInitialEdgeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, -5);
        assertTrue(graph.getEdgeCount() >= 0);
    }

    @Test
    public void testSingleGraphZeroInitialEdgeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 0);
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    public void testSingleGraphPositiveInitialEdgeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getEdgeCount());
    }

    // Tests for the class MultiGraph constructor
    @Test
    public void testMultiGraphEmptyId() {
        Graph graph = new MultiGraph("", true, true, 10, 5);
        assertEquals("", graph.getId());
    }

    @Test
    public void testMultiGraphNonEmptyId() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertEquals("Graph1", graph.getId());
    }

    @Test
    public void testMultiGraphStrictCheckingTrue() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isStrict());
    }

    @Test
    public void testMultiGraphStrictCheckingFalse() {
        Graph graph = new MultiGraph("Graph1", false, true, 10, 5);
        assertFalse(graph.isStrict());
    }

    @Test
    public void testMultiGraphAutoCreateTrue() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isAutoCreationEnabled());
    }

    @Test
    public void testMultiGraphAutoCreateFalse() {
        Graph graph = new MultiGraph("Graph1", true, false, 10, 5);
        assertFalse(graph.isAutoCreationEnabled());
    }

    @Test
    public void testMultiGraphNegativeInitialNodeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, -10, 5);
        assertTrue(graph.getNodeCount() >= 0);
    }

    @Test
    public void testMultiGraphZeroInitialNodeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 0, 5);
        assertEquals(0, graph.getNodeCount());
    }

    @Test
    public void testMultiGraphPositiveInitialNodeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getNodeCount());
    }

    @Test
    public void testMultiGraphNegativeInitialEdgeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, -5);
        assertTrue(graph.getEdgeCount() >= 0);
    }

    @Test
    public void testMultiGraphZeroInitialEdgeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 0);
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    public void testMultiGraphPositiveInitialEdgeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getEdgeCount());
    }

    // test for the method getEdge(String id)
    @Test
    public void testGetEdgeExistingEdgeId() {
        // Create a graph and add an edge with a specific id
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge edge = graph.addEdge("Edge1", A, B, true);

        // Retrieve the edge using the existing id
        Edge result = graph.getEdge("Edge1");

        // Assert that the retrieved edge is not null and matches the expected edge
        assertNotNull(result);
        assertEquals(edge, result);
    }

    @Test
    public void testGetEdgeNonExistingEdgeId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        // Retrieve the edge using the existing id
        Edge result = graph.getEdge("Edge2");

        // Assert that the retrieved edge is not null and matches the expected edge
        assertNull(result);
    }

    @Test
    public void testGetEdgeNullId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        // Retrieve the edge using the existing id
        Edge result = graph.getEdge(null);

        // Assert that the retrieved edge is not null and matches the expected edge
        assertNull(result);
    }

    // test for the method getEdge(int index)
    @Test
    public void testGetEdgeValidIndex() {
        // Create a graph and add an edge with a specific id
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge edge = graph.addEdge("Edge1", A, B, true);

        // Retrieve the edge using the existing id
        Edge result = graph.getEdge(0);

        // Assert that the retrieved edge is not null and matches the expected edge
        assertNotNull(result);
        assertEquals(edge, result);
    }

    @Test
    public void testGetEdgeInvalidIndexNegative() {
        // Create a graph and add an edge with a specific id
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        // Retrieve the edge using the invalid negative index
        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(-3);
        });
    }

    @Test
    public void testGetEdgeInvalidIndexOutOfRange() {
        // Create a graph and add an edge with a specific id
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        // Retrieve the edge using the invalid out of range index
        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(10000000);
        });
    }

    // test for method getNode(String id)
    @Test
    public void testGetNodeExistingNodeId() {
        // Create a graph and add a node with a specific id
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");

        // Retrieve the node using the existing id
        Node result = graph.getNode("A");

        // Assert that the retrieved node is not null and matches the expected node
        assertNotNull(result);
        assertEquals(A, result);
    }

    @Test
    public void testGetNodeNonExistingNodeId() {
        // Create a graph and add a node with a specific id
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        // Retrieve the node using the existing id
        Node result = graph.getNode("B");

        // Assert that the retrieved node is null as it does not exist
        assertNull(result);
    }

    @Test
    public void testGetNodeNullId() {
        // Create a graph and add a node with a specific id
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        // Retrieve the node using the existing id
        Node result = graph.getNode(null);

        // Assert that the retrieved node is null as the id is null
        assertNull(result);
    }

    // test for method getNode(int index)
    @Test
    public void testGetNodeValidIndex() {
        // Create a graph and add a node with a specific id
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");

        // Retrieve the node using a valid index
        Node result = graph.getNode(0);

        // Assert that the retrieved node is not null and matches the expected node at
        // the given index
        assertNotNull(result);
        assertEquals(A, result);
    }

    @Test
    public void testGetNodeInvalidIndexNegative() {
        // Create a graph and add a node with a specific id
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        // Retrieve the node using a negative index
        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getNode(-1);
        });
    }

    @Test
    public void testGetNodeInvalidIndexOutOfRange() {
        // Create a graph and add a node with a specific id
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        // Retrieve the node using an index that exceeds the range
        // Retrieve the node using a negative index
        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getNode(10000);
        });
    }

    // test for method addEdge(String id, Node from, Node to, boolean directed)
    @Test
    public void testEmptyId() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge AB = graph.addEdge("", A, B, true);

        // Assert that the edge was not added to the graph
        assertEquals("", AB.getId());
    }

    @Test
    public void testNonEmptyId() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("teste", A, B, true);

        // Assert that the edge exists in the graph with the specified id
        assertNotNull(graph.getEdge("teste"));
    }

    @Test
    public void testNullFromNode() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");
        Node B = graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.addEdge("teste", null, B, true);
        });
    }

    @Test
    public void testNonExistingToNode() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node A = graph.addNode("A");
        graph.addNode("B");
        Node C = graph2.addNode("C");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.addEdge("Edge1", A, C, true);
        });
    }

    @Test
    public void testNullToNode() {
        // Create a graph and nodes
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.addEdge("Edge1", A, null, true);
        });
    }

    @Test
    public void testDirectedTrue() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("teste", A, B, true);

        // Assert that the edge exists in the graph with the specified id
        assertNotNull(graph.getEdge("teste"));
        assertTrue(graph.getEdge("teste").isDirected());
    }

    @Test
    public void testDirectedFalse() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("teste", A, B, false);

        // Assert that the edge exists in the graph with the specified id
        assertNotNull(graph.getEdge("teste"));
        assertFalse(graph.getEdge("teste").isDirected());
    }

    // test for method addNode(String id)
    @Test
    public void testAddNodeEmptyId() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        graph.addNode("");

        // Assert that the node was not added to the graph
        assertNotNull(graph.getNode(""));
    }

    @Test
    public void testAddNodeNonEmptyId() {
        // Create a graph and nodes
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        // Assert that the node was not added to the graph
        assertNotNull(graph.getNode("A"));
    }

    // test for method removeNode(Node node)
    @Test
    public void testRemoveExistingNode() {
        // Create a graph and add a node
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");

        // Remove the existing node
        graph.removeNode(nodeA);
        assertNull(graph.getNode("A"));

    }

    @Test
    public void testRemoveNonExistingNode() {
        // Create a graph without any nodes
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node nodeA = graph2.addNode("A");

        // Remove a non-existing node
        assertThrows(ElementNotFoundException.class, () -> {
            graph.removeNode(nodeA);
        });

    }

    @Test
    public void testRemoveNullNode() {
        // Create a graph without any nodes
        Graph graph = new SingleGraph("grafo");
        Node A = null;

        // Remove a null node
        graph.removeNode(A);
        assertNull(graph.getNode("A"));

    }

    // test for method removeEdge(Edge edge)
    @Test
    public void testRemoveEdgeRemoveExistingEdge() {
        // Create a graph and add nodes and an edge
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        // Remove the existing edge
        graph.removeEdge(edge);

        // Assert that the edge no longer exists in the graph
        assertTrue(graph.getEdgeCount() == 0);
    }

    @Test
    public void testRemoveEdgeRemoveNonExistingEdge() {
        // Create a graph and add nodes without any edges
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        // Remove a non-existing edge
        assertThrows(ElementNotFoundException.class, () -> {
            graph2.removeEdge(edge);
        });
    }

    @Test
    public void testRemoveEdgeRemoveNullEdge() {
        // Create a graph without any edges
        Graph graph = new SingleGraph("grafo");
        Edge edge = null;

        // Remove a null edge

        graph.removeEdge(edge);

        // Assert that the result is false as the edge is null
        assertTrue(graph.getEdgeCount() == 0);
    }

    // test for method removeEdge(Node node1, Node node2)
    @Test
    public void testRemoveExistingEdge() {
        // Create a graph and add nodes and an edge
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        // Remove the existing edge using the existing nodes
        Edge C = graph.removeEdge(nodeA, nodeB);
        assertEquals(edge, C);

    }

    @Test
    public void testRemoveNonExistingEdge() {
        // Create a graph and add nodes without any edges
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");

        // Remove a non-existing edge
        assertThrows(ElementNotFoundException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    @Test
    public void testRemoveNullNode1() {
        // Create a graph without any edges
        Graph graph = new SingleGraph("grafo");
        Node A = null;
        Node nodeB = graph.addNode("B");

        // Remove an edge with null node1
        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(A, nodeB);
        });

    }

    @Test
    public void testRemoveNullNode2() {
        // Create a graph without any edges
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node B = null;

        // Remove an edge with null node2
        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, B);
        });
    }

    @Test
    public void testRemoveNullNodes() {
        // Create a graph without any edges
        Graph graph = new SingleGraph("grafo");
        Node A = null;
        Node B = null;

        // Remove an edge with both nodes as null
        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(A, B);
        });
    }

}
