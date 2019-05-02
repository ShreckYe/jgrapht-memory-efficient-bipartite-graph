# Memory-efficient matching based on JGraphT
A memory-efficient matching algorithm (Kuhn–Munkres and Hopcroft–Karp) implementation based on JGraphT in Java
## Introduction
This repo includes a memory-efficient implementation of A bipartite graph based on [JGraphT](https://jgrapht.org/) and [Eclipse Collections](https://www.eclipse.org/collections/), along with examples on how to call optimal matching algorithms (assignment algorithms) such as Kuhn–Munkres algorithm (Hungarian algorithm) and Hopcroft–Karp algorithm on such a graph, intended to solve optimal matching problems for really large data on a single machine. A project based on this implemetation has successfully solved a matching problem of 400000 vertices in each bipartite set, each with 20000 edges on average in half a day on a machine with 32GB RAM.
## Apache License
```
   Copyright 2019 Yongshun Ye

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
