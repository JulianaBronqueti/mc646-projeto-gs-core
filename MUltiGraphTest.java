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
import java.util.concurrent.TimeUnit;

@GraphWalker(value = "quick_random(edge_coverage(100))", start = "v_inicio")
public class MultiGraphTest extends ExecutionContext implements MultiGraph {
	BoundedQueue boundedQueue = null;
	int element1 = 1;
	boolean nullPointerException = false;
	boolean illegalStateException = false;

    @Vertex()
    void v_ExcecaoIdJaUsado();

    @Edge()
    void e_removeAresta();

    @Edge()
    void e_removeNoInexistente();

    @Edge()
    void e_limpaGrafo();

    @Vertex()
    void v_inicio();

    @Vertex()
    void v_ExcecaoArestaRejeitada();

    @Vertex()
    void v_grafoNaoVazio();

    @Edge()
    void e_removeUltimoNo();

    @Edge()
    void e_adicionaNoExistente();

    @Edge()
    void e_adicionaArestaEmNoInexistente();

    @Edge()
    void e_removeNo();

    @Edge()
    void e_adicionaArestaEmNoCheio();

    @Edge()
    void e_criaGrafo();

    @Edge()
    void e_adicionaNo();

    @Edge()
    void e_adicionaAresta();

    @Vertex()
    void v_grafoVazio();

    @Edge()
    void e_adicionaArestaExistente();

    @Vertex()
    void v_ElementoNaoEncontrado();
}
