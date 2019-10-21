package shreckye.graph.memoryefficientmatching

import org.eclipse.collections.api.iterator.IntIterator
import org.eclipse.collections.api.list.primitive.IntList

class WrappedEdgeSet(val source: Int, val wrapped: IntList) : MutableSet<Long> {
    class WrappedIterator(val source: Int, val wrapped: IntIterator) : MutableIterator<Long> {
        override fun hasNext(): Boolean =
            wrapped.hasNext()

        override fun next(): Long =
            source edgeTo wrapped.next()

        override fun remove() {
            throw NotImplementedError("No need to implement this for the matching algorithm")
        }
    }

    override fun iterator(): MutableIterator<Long> =
        WrappedIterator(source, wrapped.intIterator())

    override val size: Int =
        wrapped.size()

    override fun add(element: Long): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun addAll(elements: Collection<Long>): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun clear() {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun remove(element: Long): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun removeAll(elements: Collection<Long>): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun retainAll(elements: Collection<Long>): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun contains(element: Long): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun containsAll(elements: Collection<Long>): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }

    override fun isEmpty(): Boolean {
        throw NotImplementedError("No need to implement this for the matching algorithm")
    }
}

fun IntList.asEdgeSet(source: Int) = WrappedEdgeSet(source, this)