package easy;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
//        String source = "abcd", target = "acbe";
//        String source = "abcd", target = "abce";
//        char[] original = {'a','b','c','c','e','d'}, changed = {'b','c','b','e','b','e'};
//        char[] original = {'a'}, changed = {'e'};
//        int[] cost = {2,5,5,1,2,20};
//        int[] cost = {10000};

//        System.out.println(test.minimumCost(source, target, original, changed, cost));

        int m = 4, n = 3;
        int[] hFences = {2, 3}, vFences = {2};
//        int m = 6, n = 7;
//        int[] hFences = {2}, vFences = {4};
        System.out.println(test.maximizeSquareArea(m, n, hFences, vFences));
    }


    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> h = f(toArray(hFences), m);
        Set<Integer> v = f(toArray(vFences), n);
        h.retainAll(v);

        if (h.isEmpty()) {
            return -1;
        }
        long ans = Collections.max(h);
        return (int) (Math.pow(ans, 2) % 1_000_000_007);
    }

    public List<Integer> toArray(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            ans.add(num);
        }
        return ans;
    }

    public Set<Integer> f(List<Integer> a, int mx) {
        a.add(1);
        a.add(mx);
        Collections.sort(a);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.size(); i++) {
            for (int j = i + 1; j < a.size(); j++) {
                set.add(a.get(j) - a.get(i));
            }
        }
        return set;
    }








    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        Map<Character, List<Edge>> graph = buildGraph(original, changed, cost);
        Map<Character, long[]> map = new HashMap<>();

        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            char s = source.charAt(i);
            char t = target.charAt(i);
            if (s != t) {
                long search = search(map, s, t, graph);
                if (search == -1) {
                    return -1;
                }
                ans += search;
            }
        }

        return ans;
    }

    private long search(Map<Character, long[]> map, char s, char t, Map<Character, List<Edge>> graph) {
        if (map.containsKey(s) && map.get(s)[t - 'a'] != 0) {
            return map.get(s)[t - 'a'];
        }
        long tmp = dijkstra(graph, s, t);

        if (tmp == -1) {
            return -1;
        }

        long[] count = map.getOrDefault(s, new long[26]);
        count[t - 'a'] = tmp;
        map.put(s, count);
        return tmp;
    }

    private Map<Character, List<Edge>> buildGraph(char[] original, char[] changed, int[] cost) {
        Map<Character, List<Edge>> graph = new HashMap<>();

        int n = original.length;
        for (int i = 0; i < n; i++) {
            char char1 = original[i];
            char char2 = changed[i];
            long edgeCost = cost[i];

            graph.putIfAbsent(char1, new ArrayList<>());
            graph.putIfAbsent(char2, new ArrayList<>());

            graph.get(char1).add(new Edge(char2, edgeCost));
        }

        return graph;
    }

    private long dijkstra(Map<Character, List<Edge>> graph, char source, char target) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        Map<Character, Long> distance = new HashMap<>();

        minHeap.offer(new Node(source, 0));
        distance.put(source, 0L);

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            char currentChar = currentNode.character;
            long currentCost = currentNode.cost;

            if (currentChar == target) {
                return currentCost;
            }

            if (currentCost > distance.getOrDefault(currentChar, 0L)) {
                continue;
            }

            for (Edge neighbor : graph.getOrDefault(currentChar, Collections.emptyList())) {
                char nextChar = neighbor.target;
                long newCost = currentCost + neighbor.cost;

                if (!distance.containsKey(nextChar) || newCost < distance.get(nextChar)) {
                    distance.put(nextChar, newCost);
                    minHeap.offer(new Node(nextChar, newCost));
                }
            }
        }

        return -1;
    }

    class Edge {
        char target;
        long cost;

        public Edge(char target, long cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    class Node {
        char character;
        long cost;

        public Node(char character, long cost) {
            this.character = character;
            this.cost = cost;
        }
    }
}
