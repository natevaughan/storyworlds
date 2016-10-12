package storyworlds.service.console;

public class Solution {
//    public static void main(String args[] ) throws Exception {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        String result = parse(reader);
//        System.out.println(result);
//
//    }
//
//    public static String parse(BufferedReader reader) throws Exception {
//        Double threshold = Double.parseDouble(reader.readLine().trim());
//        Integer lineCount = Integer.parseInt(reader.readLine().trim());
//        int i = 0;
//
//        Map<String, AffinityNode> affinityNodes = new HashMap<String, AffinityNode>();
//
//        // provides instances of private classes
//        Solution factory = new Solution();
//
//        while (i < lineCount) {
//            ++i;
//            String line = reader.readLine();
//            String[] lineArgs = line.split(" ");
//
//            // fail fast
//            if (lineArgs.length < 3) {
//                throw new Exception("Could not parse input. Line was: \n" + line);
//            }
//
//            String name1 = lineArgs[0].trim();
//            String name2 = lineArgs[1].trim();
//            Double affinity = Double.parseDouble(lineArgs[2].trim());
//
//            AffinityNode node1;
//            AffinityNode node2;
//
//            if (!affinityNodes.containsKey(name1)) {
//                node1 = factory.getAffinityNode(name1);
//                affinityNodes.put(name1, node1);
//            } else {
//                node1 = affinityNodes.get(name1);
//            }
//
//            if (!affinityNodes.containsKey(name2)) {
//                node2 = factory.getAffinityNode(name2);
//                affinityNodes.put(name2, node2);
//            } else {
//                node2 = affinityNodes.get(name2);
//            }
//
//            // were this a map of nodes that could be crawled more than
//            // once with different thresholds, we would bind all nodes
//            // however, since this will be once with a known threshold,
//            // we will throw away any node links that are below threshold.
//            if (affinity > threshold) {
//                node1.bind(node2, affinity);
//            }
//
//            NodeCrawler crawler = factory.getNodeCrawler(affinityNodes.values());
//            return crawler.crawlAll().getName();
//    }
//
//    private class NodeCrawler {
//        private final Collection<AffinityNode> nodesToCrawl;
//        private Set<AffinityNode> crawledNodes;
//        private List<Collection<AffinityNode>> clusters;
//
//        public NodeCrawler(Collection<AffinityNode> nodesToCrawl) {
//            this.nodesToCrawl = nodesToCrawl;
//            this.crawledNodes = new HashSet<AffinityNode>();
//            this.clusters = new ArrayList<Collection<AffinityNode>>();
//        }
//
//        public AffinityNode crawlAll() throws Exception {
//            if (nodesToCrawl.size() < 1) {
//                throw new Exception("No nodes to crawl.");
//            }
//
//            for (AffinityNode node : nodesToCrawl) {
//                if (!crawledNodes.contains(node)) {
//                    Collection<AffinityNode> cluster = crawl(node, new HashSet<AffinityNode>());
//                    crawledNodes.addAll(cluster);
//                    clusters.add(cluster);
//                }
//            }
//
//            Collection<AffinityNode> largest = clusters.get(0);
//            for (Collection<AffinityNode> cluster : clusters) {
//                if (cluster.size() > largest.size()) {
//                    largest = cluster;
//                }
//            }
//
//            AffinityNode first = null;
//
//            for (AffinityNode node : largest) {
//                if (first == null) {
//                    first = node;
//                    break;
//                }
//                if (first.getName().compareTo(node.getName()) < 0) {
//                    first = node;
//                }
//            }
//            return first;
//        }
//
//        private Collection<AffinityNode> crawl(AffinityNode crawlable, Collection<AffinityNode> cluster) {
//            if (!cluster.contains(crawlable)) {
//                crawledNodes.add(crawlable);
//                for (AffinityNode node : crawlable.getLinkedNodes().keySet()) {
//                    crawl(node, cluster);
//                }
//            }
//            return cluster;
//        }
//    }
//
//    private class AffinityNode {
//        private final String name;
//        private final Map<AffinityNode, Double> linkedNodes;
//
//        public AffinityNode(String name) {
//            this.name = name;
//            this.linkedNodes = new HashMap<AffinityNode, Double>();
//        }
//
//        public void bind(AffinityNode node, Double strength) {
//            if (!linkedNodes.containsKey(node)) {
//                linkedNodes.put(node, strength);
//            }
//            if (node.getLinkedNodes().containsKey(this)) {
//                node.getLinkedNodes().put(this, strength);
//            }
//        }
//
//        public Map<AffinityNode, Double> getLinkedNodes() {
//            return linkedNodes;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o instanceof AffinityNode) {
//                return name.equals(((AffinityNode) o).getName());
//            }
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            return name.hashCode();
//        }
//    }
//
//    private AffinityNode getAffinityNode(String name) {
//        return new AffinityNode(name);
//    }
//
//    private NodeCrawler getNodeCrawler(Collection<AffinityNode> nodes) {
//        return new NodeCrawler(nodes);
//    }
}