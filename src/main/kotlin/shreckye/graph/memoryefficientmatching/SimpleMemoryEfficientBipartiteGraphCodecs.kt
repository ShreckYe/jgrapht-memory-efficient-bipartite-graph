package shreckye.graph.memoryefficientmatching

@ExperimentalUnsignedTypes
infix fun Int.edgeTo(targetVertex: Int): Long =
    toLong().shl(Int.SIZE_BITS) or targetVertex.toUInt().toLong()

val Long.sourceVertex
    get() = shr(Int.SIZE_BITS).toInt()

val Long.targetVertex
    get() = toInt()