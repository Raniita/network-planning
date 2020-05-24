package caseStudy;

import java.lang.reflect.Array;
import java.util.*;

import com.net2plan.interfaces.networkDesign.*;
import com.net2plan.utils.Constants;
import com.net2plan.utils.DoubleUtils;
import com.net2plan.utils.Pair;
import com.net2plan.utils.Triple;

/* See in this template recommendations for a well structured code:
 *
 * NAMES OF CLASSES AND VARIABLES:
 * 1) Java best practices mandate that classes start with capital letters (e.g. NodeLocation_TEMPLATE), while variable names start with lowercase letters.
 * 2) Variable and class names can be long. They are recommended to express in its name, exactly what they do,
 * e.g. a name like 'numberOfAccessNodesConnectedToThisLocation' is preferred to 'n'.
 * 3) Use the so-called CAMEL CASE for the class and variable names: writing phrases such that each word or abbreviation in the middle of the
 * phrase begins with a capital letter, with no intervening spaces or punctuation. E.g. numberOfAccessNodesConnectedToThisLocation. In Java it is less common
 * to use underscore (e.g. number_of_access_nodes_connected_to_this_location is not within best practices in Java naming conventions)
 * 4) It is in general accepted that global constants that are very heavily used, can be an exception to the Java naming conventions, using short (1 letter or two) variable names in capital letters.
 * In this template, constants M and C are used in that form. Some people accept as a good practice also long constant names, all upercase letters, and words separated with underscore (e.g MAX_NUMBER_CONNECTED_ACCESS_NODES).
 *
 * VARIABLES THAT ARE CONSTANTS:
 * - Use the "final" keyword when a created variable is actually a constant that will not change during its existence in the code. This informs
 * other developers looking t your code that they are constants, and makes the code more readable (additionally, the Java compiler can make some optimizations that can make your code to be a little bit faster)
 *
 * USE LESS LINES OF CODE:
 * - A code that makes the job with less lines of code, is more readable, simpler to maintain, better. This can be achieved by structuring the code well.
 * Also, by re-using the built-in libraries that Java provides, instead of writing ourselves a code for simple things that typically are already implemented there.
 * Reusing code of these libraries is always better than "re-invent the wheel", coding again what is already there. E.g. use intelligently the
 * functionalities of the List, Set and Map containers of Java:
 * - A Java List (of type ArrayList, LinkedList,...) is an ordered sequence of elements, same element can appear more than once.
 * - A Java Set (a HashSet, a TreeSet...) is an unordered set of elements, if any element is added twice, the second addition is ignored
 * - A Java Map (HashMap, TreeMap,...) is a set of entries [key , value]. When adding two entries with the same key, the last removes any previous one.
 *
 * COMMENTING THE CODE:
 * - By using a structured code, with expressive variable names, your code can be read "like a book". Therefore, it should not need many comments.
 * Just add the right amount of comments. For this, google "best practices commenting code" and read a bit
 *
 * REFACTORING:
 * - Use Eclipse options for code refactoring. Read about this NOW!! (e.g https://www.baeldung.com/eclipse-refactoring).
 *  The more you use these options, the more time you save. This saves tons of time. No serious coding can be done without this.
 *
 * INDENTATION AND CODE FORMATTING:
 * - Eclipse (and other IDEs) provide powerful tools to reindent and reformat the code, so it follows Java conventions. USE THEM! READ NOW ABOUT THEM!
 * (e.g. Google "eclipse java code formatting"). No serious coding can be done without this.
 *
 * FINAL LEMMA, THE BEST ADVICE I CAN GIVE: There are different opinions on how a well-structured code is, well documented, etc etc.
 * You are encouraged to read about this in Internet sources, and accept what experienced programmers suggest about this. Please, also
 * read about what your IDE (e.g. Eclipse) can make for you, to make your life simpler, your code better. Will save you TONS of time.
 *
 *
 *
 * */

/** This is a template for the students, with an skeleton for solving the node location problem of the use case 2019-20.
 */
public class NodeLocation_EnriqueFer implements IAlgorithm {
    @Override
    public String executeAlgorithm(NetPlan np, Map<String, String> algorithmParams, Map<String, String> n2pParams)
    {
        /* Initialize algorithm parameters  */
        final int M = Integer.parseInt(algorithmParams.get("M"));
        final double C = Double.parseDouble(algorithmParams.get("C"));
        final double maxExecTimeSecs = Double.parseDouble(algorithmParams.get("maxExecTimeSecs"));

        /* Main loop goes here, it */
        np.removeAllLinks(); // remove all links of the input design
        final long algorithmStartTime = System.nanoTime();
        final long algorithmEndTime = algorithmStartTime + (long) (maxExecTimeSecs * 1e9);

        /* Typically, the algorithm stores the best solution find so far,
         * which is the one to return when the running time reaches the user-defined limit */
        NetPlan bestSolutionFoundByTheAlgorithm = np.copy();
        double costBestSolutionFoundByTheAlgorithm = Double.MAX_VALUE;

        /* Students code go here. It should leave the best solution found in the variable: bestSolutionFoundByTheAlgorithm */

        /* Pulling my specific algorithm parameters */
        final double alpha = Double.parseDouble(algorithmParams.get("alpha"));
        final boolean verbose = Boolean.parseBoolean(algorithmParams.get("verbose"));

        // TODO: Maybe insert seed as a parameter?
        /* Init random solution */
        final Random rng = new Random(1);

        final int N = np.getNumberOfNodes();

        //NetPlan greedyRandSolution = np.copy();

        /* Filling the init best solution */
        ArrayList<ArrayList<Integer>> initSolution = new ArrayList<>(N);
        for(Node node : np.getNodes()){
            initSolution.add(new ArrayList<Integer>());
        }
        Pair<ArrayList<ArrayList<Integer>>, Double> bestSolutionEncoded = Pair.of(initSolution, costBestSolutionFoundByTheAlgorithm);

        // TODO: Add a temporary ping pong time checker to estimate the average time over a greedy iteration. Trying to be fine with the 60secs
        /* Main Loop. Stopped when the maximum execution time is reached */
        while(System.nanoTime() < algorithmEndTime){
            // Problema1. Como codificar la solución
            // Una especie de vector doble. Cada coordenada es localización de acceso
            // Una coordenada para el firstCoreNode
            // La otra para el secondCoreNode
            // Parece ser interesante que sea una lista ordenada.

            // trying something (https://www.baeldung.com/java-graphs) (https://www.baeldung.com/java-multi-dimensional-arraylist)
            //ArrayList<ArrayList<Integer>> codSolution = new ArrayList<>(N);

            // Init the codSolution
            //for(Node accesNode : np.getNodes()){
            //    codSolution.add(new ArrayList<Integer>());
            //}

            //for(Node accessLocation : np.getNodes()){
                // Location over we find core node
            //    final int firstLocationCoreNodeIndex = rng.nextInt(N);
            //    int secondLocationCoreNodeIndex = rng.nextInt(N);
                // Cant be the same, wrong solution
            //    while (firstLocationCoreNodeIndex == secondLocationCoreNodeIndex)
            //        secondLocationCoreNodeIndex = rng.nextInt(N);

                // Adding Link on topology, and adding to codificate solution
            //    addLink(accessLocation, np.getNode(firstLocationCoreNodeIndex));
            //    add2CodificationSolution(codSolution, accessLocation.getIndex(), firstLocationCoreNodeIndex);
            //    addLink(accessLocation, np.getNode(secondLocationCoreNodeIndex));
            //    add2CodificationSolution(codSolution, accessLocation.getIndex(), secondLocationCoreNodeIndex);
            //}

            // Evaluate the random solution
            //costBestSolutionFoundByTheAlgorithm = evaluateDesign(np,M,C).getFirst();

            // Test codificate solution
            //printCodificationSolution(codSolution);

            // Testing other way to codificate
            //System.out.println("new codification");
            //ArrayList<ArrayList<Integer>> codSolution2 = new ArrayList<>(N);

            // Init the codSolution
            //for(Node accessNode : np.getNodes()){
            //    codSolution2.add(new ArrayList<Integer>());
            //}
            //codificationSolution(np, codSolution2);

            //System.out.println("Second way to codificate");
            //printCodificationSolution(codSolution2);

            //System.out.println("Testing the recover function");
            //np.removeAllLinks();

            System.out.println("Calling greedy algorithm...");
            //recoverTopologyCostCodification(np, codSolution);

            /* FIRST TRY ALGORITHM 1. GRASP */

            // GREEDY
            // TODO: move to parameters of net2plan
            //final double alpha = 0.25;

            // Executing GreedyRandomized with alpha
            // TODO: should add a parameters know as verbose, activate/deactivate the debug output
            Pair<ArrayList<ArrayList<Integer>>, Double> greedySolution = computeGreedyRandomized(np, rng, N, alpha, M, C);

            // Saving actual solution to codified solution
            // TODO: change to Pair<> with codified solution and cost of the solution
            //ArrayList<ArrayList<Integer>> greedyRandSolutionCodified = new ArrayList<>(N);
            //for(Node node : np.getNodes()){
            //    greedyRandSolutionCodified.add(new ArrayList<Integer>());
            //}
            //codificationSolution(np, greedyRandSolutionCodified);

            // Getting total cost
            //greedyRandSolution = np.copy();
            //final double costGreedyRandSolution = evaluateDesign(greedyRandSolution, M, C).getFirst();

            // Printing GreedySolution
            System.out.println("costGreedySolution: "+ greedySolution.getSecond());
            //printCodificationSolution(greedySolution.getFirst());

            /*  */
            //np.removeAllLinks();
            //break;

            /* Calling Local Search*/
            //computeLocalSearchStep(np, rng, M, C, greedyRandSolutionCodified);
            Pair<ArrayList<ArrayList<Integer>>, Double> localSearchSolution = computeLocalSearchStep(np, rng, N, M, C, greedySolution.getFirst());

            // Printing the localSearch output
            System.out.println("costLocalSearch: "+localSearchSolution.getSecond());
            //printCodificationSolution(localSearchSolution.getFirst());

            // TODO: check if localSearch improve the best solution found ever
            if(localSearchSolution.getSecond() < costBestSolutionFoundByTheAlgorithm){
                // Updating best solution
                System.out.println("IMPROVED!! Updating bestSolution.");
                bestSolutionFoundByTheAlgorithm = np.copy();
                costBestSolutionFoundByTheAlgorithm = localSearchSolution.getSecond();
                //final ArrayList<ArrayList<Integer>> newBestSolution = encodeSolution(np,N);


                bestSolutionEncoded.setFirst(encodeSolution(np,N));
                bestSolutionEncoded.setSecond(costBestSolutionFoundByTheAlgorithm);
                //printCodificationSolution(bestSolutionEncoded.getFirst());
            }

            //bestSolutionFoundByTheAlgorithm = np.copy();
            // Only execute one iteration of GRASP
            //break;

            /* RESET! Go to the init topology */
            np.removeAllLinks();
        }

        //bestSolutionFoundByTheAlgorithm = .copy();

        /* Printing the bestSolution Found! */
        System.out.println("Finished!. Cost: "+bestSolutionEncoded.getSecond());
        printCodificationSolution(bestSolutionEncoded.getFirst());

        /* Return the solution in bestSolutionFoundByTheAlgorithm */
        final double totalRunningTimeInSeconds = (System.nanoTime() - algorithmStartTime) / 1e9;
        np.assignFrom(bestSolutionFoundByTheAlgorithm); // this line is for storing in the np variable (the design to return), the best solution found
        final Pair<Double,Integer> returnedDesignInfo = evaluateDesign(np, M, C); // compute the cost and number of core nodes, to return it, and check the design validity
        final double returnedDesignCost = returnedDesignInfo.getFirst();
        final int returnedDesignNumCoreNodes = returnedDesignInfo.getSecond();
        if (returnedDesignCost == Double.MAX_VALUE)
            return "Wrong solution. Does not pass the validation";
        else
            return "Ok. Cost: " + returnedDesignCost + ". Num core nodes: " + returnedDesignNumCoreNodes + ". Total running time (seconds): " + totalRunningTimeInSeconds;
    }

    /** Help function to evaluate the validity of a design (returns a cost of Double.MAX_VALUE if not valid)
     * Returns a pair of two numbers:
     * 1) the total network cost,
     * 2) the total number of core nodes in the design (this information may help the algorithm to make decisions).
     * IMPORTANT: If the design violates the constraints: every access node is not connected to two core nodes, it returns a cost of Double.MAX_VALUE */
    public static Pair<Double,Integer> evaluateDesign (NetPlan np , int M , double C)
    {
        int numCoreNodes = 0;
        double totalDistanceOfAccessLinks = 0;
        for (Node location : np.getNodes())
        {
            final SortedSet<Link> outgoingAccessLinks = location.getOutgoingLinks();
            if (outgoingAccessLinks.size() > 2)
            {
                System.out.println("Wrong design. A location " + location + " cannot have more than two outgoing links, since this would mean an access node connected to more than two core nodes");
                return Pair.of(Double.MAX_VALUE, 0);
            }
            if (outgoingAccessLinks.size() == 0)
            {
                System.out.println("Wrong design. A location "+ location + " cannot have zero outgoing links, since this would mean an access node connected to only one core node");
                return Pair.of(Double.MAX_VALUE, 0);
            }
            if (outgoingAccessLinks.size() == 2)
            {
                if (outgoingAccessLinks.first().getDestinationNode().equals(outgoingAccessLinks.last().getDestinationNode()))
                {
                    System.out.println("Wrong design. A location " + location + " cannot be connected twice to the same location: the connected core nodes must be in different locations");
                    return Pair.of(Double.MAX_VALUE, 0);
                }
            }
            final boolean theAccessNodeInThisLocationIsConnectedToThisLocation = (outgoingAccessLinks.size() == 1);
            final int numAccessNodesConnectedToThisLocationCoreNodes = location.getIncomingLinks().size() + (theAccessNodeInThisLocationIsConnectedToThisLocation ? 1 : 0);
            final int numCoreNodesThisLocation = (int) Math.ceil(((double) numAccessNodesConnectedToThisLocationCoreNodes) / (double) M);
            numCoreNodes += numCoreNodesThisLocation;
            for (Link outLink : outgoingAccessLinks)
                totalDistanceOfAccessLinks += getCostAccessLink (outLink);
        }
        final double networkTotalCost = C * numCoreNodes +  totalDistanceOfAccessLinks;
        //System.out.println ("Evaluation - Total network cost: " + networkTotalCost + ". Num core nodes: " + numCoreNodes + " (cost core nodes: " + (C * numCoreNodes) + "). Total distance access links: " + totalDistanceOfAccessLinks);
        return Pair.of(networkTotalCost , numCoreNodes);
    }


    /** Help function to compute the cost of an access link from its end locations */
    private static double getCostAccessLink (Node a , Node b)
    {
        return a.getNetPlan().getNodePairEuclideanDistance(a, b);
    }
    /** Help function to compute the cost of an access link between two locations  */
    private static double getCostAccessLink (Link link)
    {
        return link.getNetPlan().getNodePairEuclideanDistance(link.getOriginNode(), link.getDestinationNode());
    }

    @Override
    public String getDescription()
    {
        return "Initially an algorithm template but already its a complete algorithm OwO! Solution implemented with GRASP method";
    }

    @Override
    public List<Triple<String, String, String>> getParameters()
    {
        final List<Triple<String, String, String>> res = new ArrayList<> ();
        res.add(Triple.of("M", "5", "Maximum number of access nodes that can be connceted to a single core node."));
        res.add(Triple.of("C", "100", "The cost of a core node."));
        res.add(Triple.of("maxExecTimeSecs", "60", "Maximum running time of the algorithm."));
        res.add(Triple.of("alpha", "0.25", "Randomized parameter to GRASP based on RCL"));
        res.add(Triple.of("verbose", "1", "Print debug messages on output console (0 == disable, 1 == enable)"));
        return res;
    }

    /** UTILS FUNCTIONS */

    /** Snippet for create a link between 2 nodes (Given by the teacher :D)*/
    public Optional<Link> addLink(Node accessNodeLocation, Node coreNodeLocation){
        // Detect if access and core are using diferent instance of netplan
        final NetPlan thisNetPlan = accessNodeLocation.getNetPlan();
        if(!thisNetPlan.equals(coreNodeLocation.getNetPlan())) throw new Net2PlanException("Wrong net2plan object.");

        // Check if is a autoloop. Wrong solution, not autoloop allow
        if(accessNodeLocation.equals(coreNodeLocation)) return Optional.empty();

        final Link createdLink = thisNetPlan.addLink(accessNodeLocation, coreNodeLocation, 1, 1, 200000, null);
        return Optional.of(createdLink);
    }

    /**
     * Encoding part by part the solution. Given a encoded solution, add CoreNode to an AccessNode
     * First approach to costCodification encoding solution
     * Source: (https://www.geeksforgeeks.org/graph-and-its-representations/) */
    public void add2CodificationSolution(ArrayList<ArrayList<Integer>> costCod, int index, int nodeIndex){
        costCod.get(index).add(nodeIndex);
    }

    /** Help function to get the Core Nodes associated to a Access Node (given a encoded solution and the AccessIndex) */
    public List<Integer> getPairCoreIndexCodificationSolution(ArrayList<ArrayList<Integer>> costCod, int accessNodeIndex){
        final int firstNode =  costCod.get(accessNodeIndex).get(0);
        final int secondNode = costCod.get(accessNodeIndex).get(1);

        return Arrays.asList(firstNode,secondNode);
    }

    /** Help function to print a encoded solution*/
    public static void printCodificationSolution(ArrayList<ArrayList<Integer>> costCod){
        for(int i=0; i<costCod.size();i++){
            System.out.println("\nAccess Node: "+i);
            for(int j=0;j < costCod.get(i).size();j++){
                System.out.print("Core "+(j+1)+ ": " + costCod.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    /**
     * Given a Encoded Solution, add to NetPlan all the links listed on it.
     * Util to restore the topology over the info of the solution encoded */
    public void recoverTopologyCostCodification(NetPlan np, ArrayList<ArrayList<Integer>> costCod){
        for(Node accessLocation : np.getNodes()){
            final List<Integer> coreNodes = getPairCoreIndexCodificationSolution(costCod, accessLocation.getIndex());
            addLink(accessLocation, np.getNode(coreNodes.get(0)));
            addLink(accessLocation, np.getNode(coreNodes.get(1)));
        }
    }

    /**
     * Function to encode the solution of the algorithm
     * In args -> NetPlan object and number of nodes
     * Output, a solution encoded. 1 AccessNode -> 2 CoreNode associated
     * Source: (https://www.baeldung.com/java-graphs [Adjacency List]) (https://www.baeldung.com/java-multi-dimensional-arraylist) */
    public ArrayList<ArrayList<Integer>> encodeSolution(NetPlan np, int N){
        // Init the empty solution
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>(N);
        for(Node node :  np.getNodes()){
            solution.add(new ArrayList<Integer>());
        }

        // Filling the solution with NetPlan actual canvas
        for(Node accessNodeLocation : np.getNodes()){
            // Getting Locations
            final int firstCoreLocation = accessNodeLocation.getOutgoingLinks().first().getDestinationNode().getIndex();
            final int secondCoreLocation = accessNodeLocation.getOutgoingLinks().size() == 1? accessNodeLocation.getIndex() : accessNodeLocation.getOutgoingLinks().last().getDestinationNode().getIndex();

            // Codification solution
            add2CodificationSolution(solution, accessNodeLocation.getIndex(), firstCoreLocation);
            add2CodificationSolution(solution, accessNodeLocation.getIndex(), secondCoreLocation);
        }

        return solution;
    }

    /**
     * Help function for greedy randomized algorithm
     * Given a list with the Nodes not visited and a random object, generate the next node to go
     * Disclaimer: the value -1 its used as a flag for the node that is already visited */
    public int getNextAccessNodeGreedy(List<Integer> notVisited, Random rng){
        // Values with -1 are used, not count on the pool
        List<Integer> randomList = new ArrayList<>();
        for(int i=0; i<notVisited.size();i++){
            if(notVisited.get(i)==-1) continue;
            randomList.add(notVisited.get(i));
        }
        // If all values are -1, so we are finished the alg. Not need to getNextNode
        System.out.println("rngNextAccess: "+randomList.toString());
        if(randomList.size() == 0)
            return -1;

        return randomList.get(rng.nextInt(randomList.size()));
    }

    // TODO: Remove the useless comments and println :D or change it to verbose things
    /** Greedy Randomized Logic, using RCL decision
     * Input: NetPlan np
     *        Random rng,
     *        N number of nodes,
     *        alpha as 0 to 1 value to randomize the alg,
     *        M max of connection on core nodes,
     *        C cost of core node
     * Output: Pair of values => encoded solution (first) cost of encoded solution (second) */
    public Pair<ArrayList<ArrayList<Integer>>,Double> computeGreedyRandomized(NetPlan np, Random rng, int N, double alpha, int M, double C){
        System.out.println("\n\n\n");

        /* Greedy Randomized (Diversificación) */
        ArrayList<ArrayList<Integer>> greedySolution = new ArrayList<>(N);
        for(Node accessNode : np.getNodes()){
            greedySolution.add(new ArrayList<Integer>());
        }

        // Funcionamiento greedy, construimos poco a poco minimizando el coste del enlace.
        // No olvidar las reestricciones del problema

        // try greedy1
        //double alpha = 0.25;
        ArrayList<Integer> nodeSequence = new ArrayList<Integer>(N);
        final int initialNode = rng.nextInt(N);
        nodeSequence.add(initialNode);

        // List of all coreNode candidates
        HashSet<Integer> notVisitedCoreNode = new LinkedHashSet<Integer>();
        for(int i=0;i<N;i++){
            notVisitedCoreNode.add(i);
        }

        List<Integer> notVisitedAccessNode = new ArrayList<Integer>(N);
        for(Node node : np.getNodes())
            notVisitedAccessNode.add(node.getIndex());

        List<Integer> eliminatedCoreNodes = new ArrayList<Integer>(N);

        // usedCoreNode count
        List<Integer> usedCoreNode = new ArrayList<Integer>(N);
        for(Node node : np.getNodes()){
            // Filling with zero values
            System.out.println(node.getIndex());
            usedCoreNode.add(node.getIndex(),0);
        }

        double nodeSequenceCost = 0;
        int countRemoves = 1;
        //while (nodeSequence.size() < N | notVisitedCoreNode.isEmpty() | notVisitedCoreNode.size() == 0 | notVisitedAccessNode.size() == 0){
         while (nodeSequence.size() < (N+1)  | notVisitedCoreNode.size() == 0 | notVisitedAccessNode.size() == 0){
            /* Create a list with the costs of possible next nodes */
            final int indexAccessNode = nodeSequence.get(nodeSequence.size() - 1);
            System.out.println("INDEX: "+indexAccessNode);
            final Node accessNode = np.getNode(indexAccessNode);
            //double[] costs = new double[notVisitedCoreNode.size()];
            //int[] nextNodes = new int[notVisitedCoreNode.size()];
            double[] costs = new double[N];
            int[] nextNodes = new int[N];
            System.out.println("Start info. indexAcc:"+indexAccessNode+" costsSize: "+costs.length+" nextNodesSize: "+nextNodes.length+" notVisited:"+notVisitedCoreNode.size());
            System.out.println("notVisitedAccessNode:"+notVisitedAccessNode.size());

            int counter = 0;
            for(Node coreNode: np.getNodes()){
                //final Node coreNode = np.getNode(indexCoreNode);
                final int indexCoreNode = coreNode.getIndex();
                if(eliminatedCoreNodes.contains(indexCoreNode)){
                    System.out.println("CACA");
                    costs[counter] = Double.MAX_VALUE;
                } else if(coreNode == accessNode){
                    costs[counter] = Double.MAX_VALUE;
                } else {
                    costs[counter] = getCostAccessLink(accessNode, coreNode);
                }

                //if(indexCoreNode == indexAccessNode) System.out.println("CACA");   // Fixing the gap created on visitedNode
                //if(coreNode == accessNode){
                //    costs[counter]=Double.MAX_VALUE;
                //} else {
                //    costs[counter] = getCostAccessLink(accessNode, coreNode);
                //}
                nextNodes[counter] = indexCoreNode;
                System.out.println(counter + " | costs: " + costs[counter] + " nextNodes: " + nextNodes[counter]);
                counter++;
            }

            /* Order the list of possible next nodes according to its cost */
            int[] orderedNextNodeIndexes = DoubleUtils.sortIndexes(costs, Constants.OrderingType.ASCENDING);
            System.out.println("orderedNexNode:" +orderedNextNodeIndexes.length);
            System.out.println("maxCost1(el malo): "+costs[orderedNextNodeIndexes[orderedNextNodeIndexes.length-1]]+" maxCost2:"+ costs[orderedNextNodeIndexes[orderedNextNodeIndexes.length-2]]);

            /* Compute the number of elements in the restricted next node list */
            final double minCost = costs[orderedNextNodeIndexes[0]];
            // maxCost == N-1, but we skip the coreNode==accessNode with Double.MAX_VALUE so => -2
            System.out.println("orderedNex1: " + Arrays.toString(orderedNextNodeIndexes));
            System.out.println("eliminatedCoreNodes: "+eliminatedCoreNodes.size()+" indexMax: "+(orderedNextNodeIndexes.length-2-eliminatedCoreNodes.size()));
            System.out.println("costsSize: "+costs.length);
            final double maxCost = costs[orderedNextNodeIndexes[orderedNextNodeIndexes.length-2-eliminatedCoreNodes.size()]];
            final double thresholdCost = minCost + alpha * (maxCost - minCost);
            int numberLinksInRCL = 0;
            countRemoves++;

            System.out.println("Min: "+minCost+" Max: "+maxCost+" Thresh: "+thresholdCost);
            // Looking for the max number for threshold
            System.out.println("nextNodes:" + Arrays.toString(nextNodes));
            System.out.println("orderedNex2: " + Arrays.toString(orderedNextNodeIndexes));

            List<Integer> RCL = new ArrayList<>();
            for(int index : orderedNextNodeIndexes){
                final int nextCoreNode = orderedNextNodeIndexes[index];
                //System.out.println("nextCoreNode: "+nextCoreNode);
                if(nextCoreNode == indexAccessNode) continue;
                if(eliminatedCoreNodes.contains(nextCoreNode)) continue;
                if(costs[nextCoreNode] > thresholdCost) continue;
                RCL.add(nextCoreNode);
            }

            System.out.println("RCL:"+RCL.toString());

            /* The next node is chosen randomly in the restricted next node list */

            // Hacking things
            //notVisitedAccessNode.remove(accessNode.getIndex());
            notVisitedAccessNode.set(accessNode.getIndex(), -1);
            System.out.println(notVisitedAccessNode.toString());
            final int nextAccessNode = getNextAccessNodeGreedy(notVisitedAccessNode, rng);
            System.out.println("nextAccessNode: "+nextAccessNode);
            //System.out.println("el siguiente seria: " +nextAccessNode);

            // Chosen randomly the nextCore
            //final int nextCoreNode1 = nextNodes[rng.nextInt(numberLinksInRCL)];
            //int nextCoreNode2 = nextNodes[rng.nextInt(numberLinksInRCL)];
            //if they are the same
            //while(nextCoreNode1 == nextCoreNode2)
             //   nextCoreNode2 = nextNodes[rng.nextInt(numberLinksInRCL)];
            System.out.println("ping");
            int nextCoreNode1;
            int nextCoreNode2;
            if(RCL.size() == 1){
                nextCoreNode1 = RCL.get(RCL.size()-1);
                nextCoreNode2 = -1;
            } else {
                nextCoreNode1 = RCL.get(rng.nextInt(RCL.size()));
                nextCoreNode2 = RCL.get(rng.nextInt(RCL.size()));
                while(nextCoreNode1 == nextCoreNode2)
                    nextCoreNode2 = RCL.get(rng.nextInt(RCL.size()));
            }

            System.out.println("pong");
            //nodeSequence.add(nextCoreNode1);
            //nodeSequence.add(nextCoreNode2);

            //notVisitedCoreNode.remove(accessNode.getIndex());

            // AccessNode
            nodeSequence.add(nextAccessNode);

            // CoreNode1
            int incrementUsedCoreNode1 = usedCoreNode.get(nextCoreNode1);
            usedCoreNode.set(nextCoreNode1, incrementUsedCoreNode1+1);
            if(usedCoreNode.get(nextCoreNode1) > M-1){
                notVisitedCoreNode.remove(nextCoreNode1);
                eliminatedCoreNodes.add(nextCoreNode1);
            }
            //notVisitedCoreNode.remove(nextCoreNode1);
            //eliminatedCoreNodes.add(nextCoreNode1);
            nodeSequenceCost += costs[nextCoreNode1];
            addLink(accessNode, np.getNode(nextCoreNode1));
            // Adding to codificated solution
            System.out.println("Solution added, coreIndex: " + nextCoreNode1);
            add2CodificationSolution(greedySolution, accessNode.getIndex(), nextCoreNode1);

            // CoreNode2
            if(nextCoreNode2 != -1){
                int incrementUsedCoreNode2 = usedCoreNode.get(nextCoreNode2);
                usedCoreNode.set(nextCoreNode2, incrementUsedCoreNode2+1);
                if(usedCoreNode.get(nextCoreNode2) > M-1){
                    notVisitedCoreNode.remove(nextCoreNode2);
                    eliminatedCoreNodes.add(nextCoreNode2);
                }
                nodeSequenceCost += costs[nextCoreNode2];
                addLink(accessNode, np.getNode(nextCoreNode2));
                System.out.println("Solution added, coreIndex: " + nextCoreNode2);
                // Adding to codificated solution
                add2CodificationSolution(greedySolution, accessNode.getIndex(), nextCoreNode2);
            }

            System.out.println(eliminatedCoreNodes.toString());

            System.out.println("Condiciones del while:");
            //nodeSequence.size() < N | notVisitedCoreNode.isEmpty() | notVisitedCoreNode.size() == 0 | notVisitedAccessNode.size() == 0
            System.out.println("nodeSeq: "+nodeSequence.size()+" notVisitedCore: "+notVisitedCoreNode.isEmpty()+" notVisitedCoreSize: "+notVisitedCoreNode.size()+" notVisitedAccessSize: "+notVisitedAccessNode.size());

             // Check if we are on the last AccessNode iteration
             if(nextAccessNode == -1){
                 System.out.println("breaking greedy. finished");
                 break;
             }
        }

        // Here may have a solution greedy randomized
        printCodificationSolution(greedySolution);
        //return  greedySolution;
        final double greedyCost = evaluateDesign(np, M, C).getFirst();

        return Pair.of(greedySolution,greedyCost);
    }

    // TODO: Improve a bit the alg on it
    // TODO: Maybe go to best-fit? Lees greedy iterations but more intensify
    /**
     * Local Search Step for a start solution
     * Over a init solution, apply a local search first-fit based to intensify the cost solution */
    public Pair<ArrayList<ArrayList<Integer>>, Double> computeLocalSearchStep(NetPlan np, Random rng, int N, int M, double C, ArrayList<ArrayList<Integer>> greedySolution){
        /* Parsing some values */
        double costBestSolution = Double.MAX_VALUE;
        // Shuffle Nodes. More intensification
        final List<Node> shuffleNodes = new ArrayList<>(np.getNodes());
        Collections.shuffle(shuffleNodes, rng);

        /* Executing Local Search Iteration */
        boolean solutionWasImproved = true;
        localSearchLoop:
        while (solutionWasImproved){
            solutionWasImproved = false;

            // Local Search Iteration
            // Working with actual np state
            for(Node accessNode : shuffleNodes){
                // Getting coreNode connecteds
                final Node coreNode1Original = accessNode.getOutgoingLinks().first().getDestinationNode();
                final Node coreNode2Original = accessNode.getOutgoingLinks().size() == 1 ? accessNode : accessNode.getOutgoingLinks().last().getDestinationNode();

                // We can change both coreNodes
                for(Node originalCoreNode : new Node[]{coreNode1Original, coreNode2Original}){
                    // Remove the current link (access -> core)
                    final boolean isSelfLocation = accessNode.equals(originalCoreNode);
                    final Link removedLink = isSelfLocation ? null : np.getNodePairLinks(accessNode,originalCoreNode, false).first();

                    if(removedLink != null) removedLink.remove();
                    for(Node tryCore : shuffleNodes){
                        if(tryCore.equals(coreNode1Original)) continue;
                        if(tryCore.equals(coreNode2Original)) continue;
                        final Optional<Link> newLink = addLink(accessNode, tryCore);
                        final double costNeighbor = evaluateDesign(np, M, C).getFirst();

                        // First-Fit
                        if(costNeighbor < costBestSolution){
                            costBestSolution = costNeighbor;
                            solutionWasImproved = true;

                            // Here saved to codified solution

                            // breaking the local-search
                            // dislike this uwu
                            continue localSearchLoop;
                        }
                        if(newLink.isPresent()) newLink.get().remove();
                    }

                    // Need to go back
                    if(removedLink != null) addLink(accessNode, originalCoreNode);
                }
            }
        }

        // Here finish the local search iteration

        // Saving the encoded solution
        ArrayList<ArrayList<Integer>> localSearchSolution = encodeSolution(np, N);
        final double localSearchCost = evaluateDesign(np, M, C).getFirst();

        // Returning the pair of encoded solution and cost
        return Pair.of(localSearchSolution,localSearchCost);
    }
}
