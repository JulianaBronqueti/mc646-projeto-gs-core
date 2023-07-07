package testes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class SingleandMultiGraphLimitTests {

    // Tests for the class SingleGraph constructor

    // Partition: Empty string
    @Test
    public void testSingleGraphEmptyId() {
        Graph graph = new SingleGraph("", true, true, 10, 5);
        assertEquals("", graph.getId());
    }

    // Partition: Non-empty string
    @Test
    public void testSingleGraphNonEmptyId() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertEquals("Graph1", graph.getId());
    }

    // Partition: True (strictChecking)
    @Test
    public void testSingleGraphStrictCheckingTrue() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isStrict());
    }

    // Partition: False (strictChecking)
    @Test
    public void testSingleGraphStrictCheckingFalse() {
        Graph graph = new SingleGraph("Graph1", false, true, 10, 5);
        assertFalse(graph.isStrict());
    }

    // Partition: True (autoCreate)
    @Test
    public void testSingleGraphAutoCreateTrue() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isAutoCreationEnabled());
    }

    // Partition: False (autoCreate)
    @Test
    public void testSingleGraphAutoCreateFalse() {
        Graph graph = new SingleGraph("Graph1", true, false, 10, 5);
        assertFalse(graph.isAutoCreationEnabled());
    }

    // Partition: Negative values
    @Test
    public void testSingleGraphNegativeInitialNodeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, -10, 5);
        assertTrue(graph.getNodeCount() >= 0);
    }

    // Partition: Zero
    @Test
    public void testSingleGraphZeroInitialNodeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 0, 5);
        assertEquals(0, graph.getNodeCount());
    }

    // Partition: Positive values
    @Test
    public void testSingleGraphPositiveInitialNodeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getNodeCount());
    }

    // Partition: Negative values
    @Test
    public void testSingleGraphNegativeInitialEdgeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, -5);
        assertTrue(graph.getEdgeCount() >= 0);
    }

    // Partition: Zero
    @Test
    public void testSingleGraphZeroInitialEdgeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 0);
        assertEquals(0, graph.getEdgeCount());
    }

    // Partition: Positive values
    @Test
    public void testSingleGraphPositiveInitialEdgeCapacity() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getEdgeCount());
    }

    // Partition: Empty string limit
    @Test
    public void testSingleGraphEmptyIdLimit() {
        Graph graph = new SingleGraph("A", true, true, 10, 5);
        assertEquals("A", graph.getId());
    }

    // Partition: Non-empty string limit
    @Test
    public void testSingleGraphNonEmptyIdLimit() {
        String longId = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Graph graph = new SingleGraph(longId, true, true, 10, 5);
        assertEquals(longId, graph.getId());
    }

    // Partition: True (strictChecking) limit
    @Test
    public void testSingleGraphStrictCheckingTrueLimit() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isStrict());
    }

    // Partition: False (strictChecking) limit
    @Test
    public void testSingleGraphStrictCheckingFalseLimit() {
        Graph graph = new SingleGraph("Graph1", false, true, 10, 5);
        assertFalse(graph.isStrict());
    }

    // Partition: True (autoCreate) limit
    @Test
    public void testSingleGraphAutoCreateTrueLimit() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isAutoCreationEnabled());
    }

    // Partition: False (autoCreate) limit
    @Test
    public void testSingleGraphAutoCreateFalseLimit() {
        Graph graph = new SingleGraph("Graph1", true, false, 10, 5);
        assertFalse(graph.isAutoCreationEnabled());
    }

    // Partition: Negative values limit
    @Test
    public void testSingleGraphNegativeInitialNodeCapacityLimit() {
        Graph graph = new SingleGraph("Graph1", true, true, Integer.MIN_VALUE, 5);
        assertTrue(graph.getNodeCount() >= 0);
    }

    // Partition: Positive values limit
    @Test
    public void testSingleGraphPositiveInitialNodeCapacityLimit() {

        assertThrows(OutOfMemoryError.class, () -> {
            Graph graph = new SingleGraph("Graph1", true, false, Integer.MAX_VALUE, 5);
        });
    }

    // Partition: Negative values limit
    @Test
    public void testSingleGraphNegativeInitialEdgeCapacityLimit() {
        Graph graph = new SingleGraph("Graph1", true, true, 10, Integer.MIN_VALUE);
        assertTrue(graph.getEdgeCount() >= 0);
    }

    // Partition: Positive values limit
    @Test
    public void testSingleGraphPositiveInitialEdgeCapacityLimit() {
        assertThrows(OutOfMemoryError.class, () -> {
            Graph graph = new SingleGraph("Graph1", true, true, 10, Integer.MAX_VALUE);
        });

    }

    // Tests for the class MultiGraph constructor
    // Partition: Empty string
    @Test
    public void testMultiGraphEmptyId() {
        Graph graph = new MultiGraph("", true, true, 10, 5);
        assertEquals("", graph.getId());
    }

    // Partition: Non-empty string
    @Test
    public void testMultiGraphNonEmptyId() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertEquals("Graph1", graph.getId());
    }

    // Partition: True (strictChecking)
    @Test
    public void testMultiGraphStrictCheckingTrue() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isStrict());
    }

    // Partition: False (strictChecking)
    @Test
    public void testMultiGraphStrictCheckingFalse() {
        Graph graph = new MultiGraph("Graph1", false, true, 10, 5);
        assertFalse(graph.isStrict());
    }

    // Partition: True (autoCreate)
    @Test
    public void testMultiGraphAutoCreateTrue() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isAutoCreationEnabled());
    }

    // Partition: False (autoCreate)
    @Test
    public void testMultiGraphAutoCreateFalse() {
        Graph graph = new MultiGraph("Graph1", true, false, 10, 5);
        assertFalse(graph.isAutoCreationEnabled());
    }

    // Partition: Negative values
    @Test
    public void testMultiGraphNegativeInitialNodeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, -10, 5);
        assertTrue(graph.getNodeCount() >= 0);
    }

    // Partition: Zero
    @Test
    public void testMultiGraphZeroInitialNodeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 0, 5);
        assertEquals(0, graph.getNodeCount());
    }

    // Partition: Positive values
    @Test
    public void testMultiGraphPositiveInitialNodeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getNodeCount());
    }

    // Partition: Negative values
    @Test
    public void testMultiGraphNegativeInitialEdgeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, -5);
        assertTrue(graph.getEdgeCount() >= 0);
    }

    // Partition: Zero
    @Test
    public void testMultiGraphZeroInitialEdgeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 0);
        assertEquals(0, graph.getEdgeCount());
    }

    // Partition: Positive values
    @Test
    public void testMultiGraphPositiveInitialEdgeCapacity() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertEquals(0, graph.getEdgeCount());
    }

    // Partition: Empty string limit
    @Test
    public void testMultiGraphEmptyIdLimit() {
        Graph graph = new MultiGraph("A", true, true, 10, 5);
        assertEquals("A", graph.getId());
    }

    // Partition: Non-empty string limit
    @Test
    public void testMultiGraphNonEmptyIdLimit() {
        String longId = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Graph graph = new MultiGraph(longId, true, true, 10, 5);
        assertEquals(longId, graph.getId());
    }

    // Partition: True (strictChecking) limit
    @Test
    public void testMultiGraphStrictCheckingTrueLimit() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isStrict());
    }

    // Partition: False (strictChecking) limit
    @Test
    public void testMultiGraphStrictCheckingFalseLimit() {
        Graph graph = new MultiGraph("Graph1", false, true, 10, 5);
        assertFalse(graph.isStrict());
    }

    // Partition: True (autoCreate) limit
    @Test
    public void testMultiGraphAutoCreateTrueLimit() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, 5);
        assertTrue(graph.isAutoCreationEnabled());
    }

    // Partition: False (autoCreate) limit
    @Test
    public void testMultiGraphAutoCreateFalseLimit() {
        Graph graph = new MultiGraph("Graph1", true, false, 10, 5);
        assertFalse(graph.isAutoCreationEnabled());
    }

    // Partition: Negative values limit
    @Test
    public void testMultiGraphNegativeInitialNodeCapacityLimit() {
        Graph graph = new MultiGraph("Graph1", true, true, Integer.MIN_VALUE, 5);
        assertTrue(graph.getNodeCount() >= 0);
    }

    // Partition: Positive values limit
    @Test
    public void testMultiGraphPositiveInitialNodeCapacityLimit() {
        assertThrows(OutOfMemoryError.class, () -> {
            Graph graph = new MultiGraph("Graph1", true, true, Integer.MAX_VALUE, 5);
        });
    }

    // Partition: Negative values limit
    @Test
    public void testMultiGraphNegativeInitialEdgeCapacityLimit() {
        Graph graph = new MultiGraph("Graph1", true, true, 10, Integer.MIN_VALUE);
        assertTrue(graph.getEdgeCount() >= 0);
    }

    // Partition: Positive values limit
    @Test
    public void testMultiGraphPositiveInitialEdgeCapacityLimit() {
        assertThrows(OutOfMemoryError.class, () -> {
            Graph graph = new MultiGraph("Graph1", true, true, 10, Integer.MAX_VALUE);
        });
    }
    // Tests for the method getEdge(String id)

    // Partition: Existing edge id
    @Test
    public void testGetEdgeExistingEdgeId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge edge = graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge("Edge1");

        assertNotNull(result);
        assertEquals(edge, result);
    }

    // Partition: Non-existing edge id
    @Test
    public void testGetEdgeNonExistingEdgeId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge("Edge2");

        assertNull(result);
    }

    // Partition: Null id
    @Test
    public void testGetEdgeNullId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge(null);

        assertNull(result);
    }

    // Partition: Existing edge id limit
    @Test
    public void testGetEdgeExistingEdgeIdLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge edge = graph.addEdge("A", A, B, true);

        Edge result = graph.getEdge("A");

        assertNotNull(result);
        assertEquals(edge, result);
    }

    // Partition: Non-existing edge id limit
    @Test
    public void testGetEdgeNonExistingEdgeIdLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge("");

        assertNull(result);
    }

    // Partition: Null id limit
    @Test
    public void testGetEdgeNullIdLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge(null);

        assertNull(result);
    }

    // Tests for the method getEdge(int index)

    // Partition: Valid index
    @Test
    public void testGetEdgeValidIndex() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge edge = graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge(0);

        assertNotNull(result);
        assertEquals(edge, result);
    }

    // Partition: Invalid index (negative)
    @Test
    public void testGetEdgeInvalidIndexNegative() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(-3);
        });
    }

    // Partition: Invalid index (out of range)
    @Test
    public void testGetEdgeInvalidIndexOutOfRange() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(10000000);
        });
    }

    // Partition: Valid index limit
    @Test
    public void testGetEdgeValidIndexLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge edge = graph.addEdge("Edge1", A, B, true);

        Edge result = graph.getEdge(0);

        assertNotNull(result);
        assertEquals(edge, result);
    }

    // Partition: Invalid index (negative) limit
    @Test
    public void testGetEdgeInvalidIndexNegativeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(Integer.MIN_VALUE);
        });
    }

    // Partition: Invalid index (out of range) limit
    @Test
    public void testGetEdgeInvalidIndexOutOfRangeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("Edge1", A, B, true);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getEdge(Integer.MAX_VALUE);
        });
    }

    // Tests for the method getNode(String id)

    // Partition: Existing node id
    @Test
    public void testGetNodeExistingNodeId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");

        Node result = graph.getNode("A");

        assertNotNull(result);
        assertEquals(A, result);
    }

    // Partition: Non-existing node id
    @Test
    public void testGetNodeNonExistingNodeId() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        Node result = graph.getNode("B");

        assertNull(result);
    }

    // Partition: Null id
    @Test
    public void testGetNodeNullId() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        Node result = graph.getNode(null);

        assertNull(result);
    }

    // Partition: Existing node id limit
    @Test
    public void testGetNodeExistingNodeIdLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");

        Node result = graph.getNode("A");

        assertNotNull(result);
        assertEquals(A, result);
    }

    // Partition: Non-existing node id limit
    @Test
    public void testGetNodeNonExistingNodeIdLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        Node result = graph.getNode("");

        assertNull(result);
    }

    // Partition: Null id limit
    @Test
    public void testGetNodeNullIdLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        Node result = graph.getNode(null);

        assertNull(result);
    }

    // Tests for the method getNode(int index)

    // Partition: Valid index
    @Test
    public void testGetNodeValidIndex() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");

        Node result = graph.getNode(0);

        assertNotNull(result);
        assertEquals(A, result);
    }

    // Partition: Invalid index (negative)
    @Test
    public void testGetNodeInvalidIndexNegative() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getNode(-1);
        });
    }

    // Partition: Invalid index (out of range)
    @Test
    public void testGetNodeInvalidIndexOutOfRange() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getNode(10000);
        });
    }

    // Partition: Valid index limit
    @Test
    public void testGetNodeValidIndexLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");

        Node result = graph.getNode(0);

        assertNotNull(result);
        assertEquals(A, result);
    }

    // Partition: Invalid index (negative) limit
    @Test
    public void testGetNodeInvalidIndexNegativeLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getNode(Integer.MIN_VALUE);
        });
    }

    // Partition: Invalid index (out of range) limit
    @Test
    public void testGetNodeInvalidIndexOutOfRangeLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            graph.getNode(Integer.MAX_VALUE);
        });
    }

    // Tests for the method addEdge(String id, Node from, Node to, boolean directed)

    // Partition: Empty string id
    @Test
    public void testEmptyId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge AB = graph.addEdge("", A, B, true);

        assertEquals("", AB.getId());
    }

    // Partition: Non-empty string id
    @Test
    public void testNonEmptyId() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("teste", A, B, true);

        assertNotNull(graph.getEdge("teste"));
    }

    // Partition: Null from node
    @Test
    public void testNullFromNode() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");
        Node B = graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.addEdge("teste", null, B, true);
        });
    }

    // Partition: Non-existing to node
    @Test
    public void testNonExistingToNode() {
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node A = graph.addNode("A");
        graph.addNode("B");
        Node C = graph2.addNode("C");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.addEdge("Edge1", A, C, true);
        });
    }

    // Partition: Null to node
    @Test
    public void testNullToNode() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.addEdge("Edge1", A, null, true);
        });
    }

    // Partition: Directed is true
    @Test
    public void testDirectedTrue() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("teste", A, B, true);

        assertNotNull(graph.getEdge("teste"));
        assertTrue(graph.getEdge("teste").isDirected());
    }

    // Partition: Directed is false
    @Test
    public void testDirectedFalse() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("teste", A, B, false);

        assertNotNull(graph.getEdge("teste"));
        assertFalse(graph.getEdge("teste").isDirected());
    }

    // Partition: Empty string id limit
    @Test
    public void testEmptyIdLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        Edge AB = graph.addEdge("", A, B, true);

        assertEquals("", AB.getId());
    }

    // Partition: Non-empty string id limit
    @Test
    public void testNonEmptyIdLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("x", A, B, true);

        assertNotNull(graph.getEdge("x"));
    }

    // Partition: Null from node limit
    @Test
    public void testNullFromNodeLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");
        Node B = graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.addEdge("x", null, B, true);
        });
    }

    // Partition: Non-existing to node limit
    @Test
    public void testNonExistingToNodeLimit() {
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node A = graph.addNode("A");
        graph.addNode("B");
        Node C = graph2.addNode("C");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.addEdge("x", A, C, true);
        });
    }

    // Partition: Null to node limit
    @Test
    public void testNullToNodeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.addEdge("x", A, null, true);
        });
    }

    // Partition: Directed is true limit
    @Test
    public void testDirectedTrueLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("x", A, B, true);

        assertNotNull(graph.getEdge("x"));
        assertTrue(graph.getEdge("x").isDirected());
    }

    // Partition: Directed is false limit
    @Test
    public void testDirectedFalseLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = graph.addNode("A");
        Node B = graph.addNode("B");
        graph.addEdge("x", A, B, false);

        assertNotNull(graph.getEdge("x"));
        assertFalse(graph.getEdge("x").isDirected());
    }

    // Tests for the method addNode(String id)

    // Partition: Empty string id
    @Test
    public void testAddNodeEmptyId() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("");

        assertNotNull(graph.getNode(""));
    }

    // Partition: Non-empty string id
    @Test
    public void testAddNodeNonEmptyId() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("A");

        assertNotNull(graph.getNode("A"));
    }

    // Partition: Null id
    @Test
    public void testAddNodeNullId() {
        Graph graph = new SingleGraph("grafo");

        assertThrows(AssertionError.class, () -> {
            graph.addNode(null);
        });
    }

    // Partition: Empty string id limit
    @Test
    public void testAddNodeEmptyIdLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("");

        assertNotNull(graph.getNode(""));
    }

    // Partition: Non-empty string id limit
    @Test
    public void testAddNodeNonEmptyIdLimit() {
        Graph graph = new SingleGraph("grafo");
        graph.addNode("x");

        assertNotNull(graph.getNode("x"));
    }

    // Partition: Null id limit
    @Test
    public void testAddNodeNullIdLimit() {
        Graph graph = new SingleGraph("grafo");
        assertThrows(AssertionError.class, () -> {
            graph.addNode(null);
        });
    }

    // Tests for the method removeNode(Node node)

    // Partition: Existing node
    @Test
    public void testRemoveExistingNode() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");

        graph.removeNode(nodeA);

        assertNull(graph.getNode("A"));
    }

    // Partition: Non-existing node
    @Test
    public void testRemoveNonExistingNode() {
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node nodeA = graph2.addNode("A");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.removeNode(nodeA);
        });
    }

    // Partition: Null node
    @Test
    public void testRemoveNullNode() {
        Graph graph = new SingleGraph("grafo");
        Node A = null;

        graph.removeNode(A);

        assertNull(graph.getNode("A"));
    }

    // Partition: Existing node limit
    @Test
    public void testRemoveExistingNodeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");

        graph.removeNode(nodeA);

        assertNull(graph.getNode("A"));
    }

    // Partition: Non-existing node limit
    @Test
    public void testRemoveNonExistingNodeLimit() {
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node nodeA = graph2.addNode("A");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.removeNode(nodeA);
        });
    }

    // Partition: Null node limit
    @Test
    public void testRemoveNullNodeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node A = null;

        graph.removeNode(A);

        assertNull(graph.getNode("A"));
    }

    // Tests for the method removeEdge(Edge edge)

    // Partition: Existing edge
    @Test
    public void testRemoveEdgeRemoveExistingEdge() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        graph.removeEdge(edge);

        assertTrue(graph.getEdgeCount() == 0);
    }

    // Partition: Non-existing edge
    @Test
    public void testRemoveEdgeRemoveNonExistingEdge() {
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        assertThrows(ElementNotFoundException.class, () -> {
            graph2.removeEdge(edge);
        });
    }

    // Partition: Null edge
    @Test
    public void testRemoveEdgeRemoveNullEdge() {
        Graph graph = new SingleGraph("grafo");
        Edge edge = null;

        graph.removeEdge(edge);

        assertTrue(graph.getEdgeCount() == 0);
    }

    // Partition: Existing edge limit
    @Test
    public void testRemoveEdgeRemoveExistingEdgeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        graph.removeEdge(edge);

        assertTrue(graph.getEdgeCount() == 0);
    }

    // Partition: Non-existing edge limit
    @Test
    public void testRemoveEdgeRemoveNonExistingEdgeLimit() {
        Graph graph = new SingleGraph("grafo");
        Graph graph2 = new SingleGraph("grafo2");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        assertThrows(ElementNotFoundException.class, () -> {
            graph2.removeEdge(edge);
        });
    }

    // Partition: Null edge limit
    @Test
    public void testRemoveEdgeRemoveNullEdgeLimit() {
        Graph graph = new SingleGraph("grafo");
        Edge edge = null;

        graph.removeEdge(edge);

        assertTrue(graph.getEdgeCount() == 0);
    }

    // Tests for the method removeEdge(Node node1, Node node2)

    // Partition: Existing node
    @Test
    public void testRemoveExistingEdge() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        Edge removedEdge = graph.removeEdge(nodeA, nodeB);

        assertEquals(edge, removedEdge);
    }

    // Partition: Non-existing node
    @Test
    public void testRemoveNonExistingEdge() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Null node1
    @Test
    public void testRemoveNullNode1() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = null;
        Node nodeB = graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Null node2
    @Test
    public void testRemoveNullNode2() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = null;

        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Null nodes
    @Test
    public void testRemoveNullNodes() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = null;
        Node nodeB = null;

        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Existing node limit
    @Test
    public void testRemoveExistingEdgeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");
        Edge edge = graph.addEdge("AB", nodeA, nodeB);

        Edge removedEdge = graph.removeEdge(nodeA, nodeB);

        assertEquals(edge, removedEdge);
    }

    // Partition: Non-existing node limit
    @Test
    public void testRemoveNonExistingEdgeLimit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = graph.addNode("B");

        assertThrows(ElementNotFoundException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Null node1 limit
    @Test
    public void testRemoveNullNode1Limit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = null;
        Node nodeB = graph.addNode("B");

        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Null node2 limit
    @Test
    public void testRemoveNullNode2Limit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = graph.addNode("A");
        Node nodeB = null;

        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

    // Partition: Null nodes limit
    @Test
    public void testRemoveNullNodesLimit() {
        Graph graph = new SingleGraph("grafo");
        Node nodeA = null;
        Node nodeB = null;

        assertThrows(NullPointerException.class, () -> {
            graph.removeEdge(nodeA, nodeB);
        });
    }

}
