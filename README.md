# GRASP Heuristic to solve networks planning problems
Algorithm for Net2Plan to solve the caseStudy of my university subject know as "Planificación y Gestión de Redes".

## Problem to solve
Given the topology of the problem;

Let i = 1;...;N a set of locations for a node. In each location i:
* There always exists exactly one access node.
* There can be any number zi = 0, 1, 2,... of core nodes, placed in that location.
Each access node must be connected to exactly two core nodes in dierent locations (one of them
can be the same location as the access node). The cost of each access link is equal to the Euclidean
distance between the locations of the access and core node.

Each core node has a limited capacity, and because of this, each core node can be connected to a
maximum of M access nodes.

The solution found should minimize the total network cost TotalCost computed as the sum of the access link costs, the cost of the core nodes:

![Cost = \sum_{e}^{} d_e + C \sum_{e}^{}z_i](https://render.githubusercontent.com/render/math?math=Cost%20%3D%20%5Csum_%7Be%7D%5E%7B%7D%20d_e%20%2B%20C%20%5Csum_%7Be%7D%5E%7B%7Dz_i)


## Input parameters
* C => Cost of core
* M => Maximum number of core that can be connected to a core node
* alpha => 0 to 1.0 value that randomize the greedy algorithm
* maxExecTimeSecs => maximum execution time
* verbose => allow debug mode


## Best values found
Actually, with alpha = 0.45 found the min cost, but i dont think so that the its the best solution ever :(