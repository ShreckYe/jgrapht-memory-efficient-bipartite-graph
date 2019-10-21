package shreckye.graph.memoryefficientmatching

import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList
import org.jgrapht.Graph
import org.jgrapht.GraphType
import org.jgrapht.graph.DefaultGraphType
import java.util.*
import java.util.function.Supplier

/**
 * Represents a simple memory-efficient bipartite graph.
 * Vertices added to this graph should have continuous or roughly continuous [Int] labels.
 * Edges are represented by [Int]s pointing to the target vertex stored in Eclipse Collections memory-efficient [IntArrayList]s
 * indexed by vertices in the first part of the graph.
 * The overall space usage is asymptotic to the capacity of edges.
 *
 * All positive vertices are added to the first part of the bipartite graph, and all negative vertices are added to the second part.
 *
 * @param vertex1sCapacity the capacity of vertices in the first part of the graph
 * @param vertex2sCapacity the capacity of vertices in the second part of the graph
 */
class SimpleMemoryEfficientBipartiteGraph(
    vertex1sCapacity: Int,
    vertex2sCapacity: Int
) : Graph<Int, Long> {
    companion object {
        private val TYPE = DefaultGraphType.Builder()
            .undirected().allowMultipleEdges(false).allowSelfLoops(false).weighted(false)
            .build()
    }

    /** Represents a vertex in the first part of the bipartite graph */
    /*inline*/ class IntrusiveVertex1(val edges: IntArrayList = IntArrayList())

    /** Target vertices of edges in [IntArrayList]s indexed by vertices in the first part of the graph. */
    private val vertices1 = Array<IntrusiveVertex1?>(vertex1sCapacity) { null }
    /** Existence of vertices indexed by their absolute values in the second part of the graph. */
    private val vertices2 = BooleanArray(vertex2sCapacity) { false }

    /**
     * Adds a vertex [v] to the bipartite graph.
     * Positive vertices are added to the first part of the bipartite graph, and negative vertices are added to the second part.
     */
    override fun addVertex(v: Int): Boolean {
        return if (v >= 0)
            if (vertices1[v] == null) {
                vertices1[v] = IntrusiveVertex1()
                true
            } else
                false
        else {
            val index = v.inv()
            if (!vertices2[index]) {
                vertices2[index] = true
                true
            } else
                false
        }
    }

    /**
     * Adds an vertex connecting [sourceVertex] and [targetVertex] to the bipartite graph.
     * The [sourceVertex] must be in the first part thus must be positive.
     * The [targetVertex] must be in the second part thus must be negative.
     * After you add all the edges, you might want to call [getIntrusiveVertex1] and [IntArrayList.trimToSize] to optimize space usage.
     * @throws IndexOutOfBoundsException if the vertex label is greater than or equal to the capacity
     * @throws NoSuchElementException if the vertex label wasn't previously added to the graph
     */
    override fun addEdge(sourceVertex: Int, targetVertex: Int): Long {
        if (!vertices2[targetVertex.inv()])
            throw NoSuchElementException()
        val edge = sourceVertex edgeTo targetVertex
        vertices1[sourceVertex]!!.edges.add(targetVertex)
        return edge
    }

    fun getIntrusiveVertex1(vertex: Int): IntrusiveVertex1 =
        vertices1[vertex]!!

    override fun getType(): GraphType =
        TYPE

    override fun getEdgeSource(e: Long): Int =
        e.sourceVertex

    override fun edgesOf(vertex: Int): MutableSet<Long> =
        vertices1[vertex]!!.edges.asEdgeSet(vertex)

    override fun getEdgeTarget(e: Long): Int =
        e.targetVertex

    override fun getEdge(sourceVertex: Int, targetVertex: Int): Long =
        if (sourceVertex >= 0) sourceVertex edgeTo targetVertex
        else targetVertex edgeTo sourceVertex

    override fun addVertex(): Int {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun addEdge(sourceVertex: Int?, targetVertex: Int?, e: Long?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun edgeSet(): MutableSet<Long> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun getEdgeSupplier(): Supplier<Long> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun outgoingEdgesOf(vertex: Int?): MutableSet<Long> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeVertex(v: Int?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeAllVertices(vertices: MutableCollection<out Int>?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun setEdgeWeight(e: Long?, weight: Double) {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeAllEdges(edges: MutableCollection<out Long>?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeAllEdges(sourceVertex: Int?, targetVertex: Int?): MutableSet<Long> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun getAllEdges(sourceVertex: Int?, targetVertex: Int?): MutableSet<Long> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun getVertexSupplier(): Supplier<Int> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun degreeOf(vertex: Int?): Int {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun containsEdge(sourceVertex: Int?, targetVertex: Int?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun containsEdge(e: Long?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun containsVertex(v: Int?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun vertexSet(): MutableSet<Int> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun outDegreeOf(vertex: Int?): Int {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun inDegreeOf(vertex: Int?): Int {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeEdge(sourceVertex: Int?, targetVertex: Int?): Long {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeEdge(e: Long?): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun incomingEdgesOf(vertex: Int?): MutableSet<Long> {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun getEdgeWeight(e: Long?): Double {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }
}