package caseStudy;

import com.net2plan.interfaces.networkDesign.*;
import com.net2plan.utils.Pair;
import com.net2plan.utils.Triple;

import javax.swing.text.html.Option;
import java.util.*;

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
public class NodeLocation_LocalSearch implements IAlgorithm {
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
        NetPlan bestSolutioFoundByTheAlgorithm = np.copy();
        double costBestSolutioFoundByTheAlgorithm = Double.MAX_VALUE;

        /* Students code go here. It should leave the best solution found in the variable: bestSolutioFoundByTheAlgorithm */


        /* Creating init solution */
        // Random mode
        final Random rng = new Random(1);
        // On 2 eject diff of the alg. The random seq maybe be different. On algorithms, we need to be pseudorandom
        // Using generate of random numbers. We need a same seed over the ejecutions.

        // Total of nodes
        final int N = np.getNumberOfNodes();
        for(Node accessLocation : np.getNodes()){
            // Location over we find core node
            final int firstLocationCoreNodeIndex = rng.nextInt(N);
            int secondLocationCoreNodeIndex = rng.nextInt(N);
            // Cant be the same, wrong solution
            while (firstLocationCoreNodeIndex == secondLocationCoreNodeIndex)
                // iterating to get diff number
                secondLocationCoreNodeIndex = rng.nextInt(N);

            // Creating the links to the access node and the randoms Core Index
            addLink(accessLocation, np.getNode(firstLocationCoreNodeIndex));
            addLink(accessLocation, np.getNode(secondLocationCoreNodeIndex));
        }
        // Evaluating the random solution
        costBestSolutioFoundByTheAlgorithm = evaluateDesign(np,M,C).getFirst();

        /* Main loop of local search */
        final List<Node> shuffleNodes = new ArrayList<>(np.getNodes());
        Collections.shuffle(shuffleNodes, rng);

        boolean solutionWasImproved = true;

        // Adding label to jump here
        mainLocalSearchLoop:
        while (solutionWasImproved){
            // To print how to the solutions were improved
            System.out.println("Local Search Loop:" + costBestSolutioFoundByTheAlgorithm);
            solutionWasImproved = false;

            /* One local search iteration */
            // First, we need to get all the AccessNode
            // Thats good to walk over getNodes where to start or the pseudorandom list.
            for(Node accessLocation : shuffleNodes){
                // Node access is conected with two corelocations
                // firstCore always set, but secondCore maybe not be set, because loop links are not setted.
                final Node firstOriginalCoreLocation = accessLocation.getOutgoingLinks().first().getDestinationNode();
                final Node secondOriginalCoreLocation = accessLocation.getOutgoingLinks().size() == 1? accessLocation : accessLocation.getOutgoingLinks().last().getDestinationNode();

                // We need to choice what we need to modify.
                for(Node originalCoreNode : new Node[] {firstOriginalCoreLocation, secondOriginalCoreLocation}){
                    // Enumerate all posible solutions, that have to be similar to np, unless the changing link

                    // Delete actual link from access to original
                    final boolean isSelfLink = accessLocation.equals(originalCoreNode);
                    // Because case of study, only have one value on set, so get first
                    final Link linkToRemove = isSelfLink? null : np.getNodePairLinks(accessLocation, originalCoreNode, false).first();
                    if(linkToRemove != null) linkToRemove.remove();

                    // Changing to other options
                    for(Node diffCoreNode : shuffleNodes){
                        // if equals to first or second, nothings changes => continue.
                        // Testing the same
                        if(diffCoreNode.equals(firstOriginalCoreLocation)) continue;
                        // Connection 2 times on same accesNode, error on case study
                        if(diffCoreNode.equals(secondOriginalCoreLocation)) continue;

                        // Adding the link. Remember, optional maybe be a null
                        final Optional<Link> newLinkCreated = addLink(accessLocation, diffCoreNode);
                        // USING FIRST FIT
                        final double costNeighborSolution = evaluateDesign(np, M, C).getFirst();
                        if(costNeighborSolution < costBestSolutioFoundByTheAlgorithm){
                            costBestSolutioFoundByTheAlgorithm = costNeighborSolution;
                            solutionWasImproved = true;

                            // New thing, someones thinks that the code its less readable
                            // Because First Fit, we want to skip to the next iteration of while
                            // Add a label
                            continue  mainLocalSearchLoop; // Closing all for, going to the init loop
                        }
                        // Removing the link created
                        if(newLinkCreated.isPresent()) newLinkCreated.get().remove();
                    }

                    // Reset the remove
                    if(linkToRemove != null) addLink(accessLocation, originalCoreNode);
                }
            }
        }

        bestSolutioFoundByTheAlgorithm = np.copy();

        /* Return the solution in bestSolutioFoundByTheAlgorithm */
        final double totalRunningTimeInSeconds = (System.nanoTime() - algorithmStartTime) / 1e9;
        np.assignFrom(bestSolutioFoundByTheAlgorithm); // this line is for storing in the np variable (the design to return), the best solution found
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
        return "This algorithm is a template for developing the node location algorithm. Please, use it!";
    }

    @Override
    public List<Triple<String, String, String>> getParameters()
    {
        final List<Triple<String, String, String>> res = new ArrayList<> ();
        res.add(Triple.of("M", "5", "Maximum number of access nodes that can be connceted to a single core node."));
        res.add(Triple.of("C", "100", "The cost of a core node."));
        res.add(Triple.of("maxExecTimeSecs", "60", "Maximum running time of the algorithm."));
        return res;
    }

    /* Function helper */
    // Interesting for reduce length of code
    public Optional<Link> addLink(Node accesNodeLocation, Node coreNodeLocation ){
        // Detect if access and core are using diferent instance of netplan
        final NetPlan thisNetPlan = accesNodeLocation.getNetPlan();
        if(!thisNetPlan.equals(coreNodeLocation.getNetPlan())) throw new Net2PlanException("Wrong net2plan object");

        // If is autoenlace, wrong solution. Ilegal thing on netplan, skiping
        if(accesNodeLocation.equals(coreNodeLocation)) return Optional.empty();

        final Link createdLink = thisNetPlan.addLink(accesNodeLocation, coreNodeLocation, 1, 1, 200000, null);
        return Optional.of(createdLink);

        // we need to use Optional. Appears on Java8. Something that contains something inside, could be null. We can return null or Link, so go to Optional
        // If we see an Optional<T> we need to think that they may be a null
    }
}