package medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 399. 除法求值
 */
public class Problem_0399_CalcEquation {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Node> nodes = new HashMap<>();

        // 将所有变量看做一个节点，初始化并查集
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            if (!nodes.containsKey(a)) {
                nodes.put(a, new Node(a));
            }
            if (!nodes.containsKey(b)) {
                nodes.put(b, new Node(b));
            }
            union(nodes, a, b, values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);

            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                res[i] = -1.0;
            } else {
                Node pa = find(nodes, a), pb = find(nodes, b);
                if (pa != pb) {
                    res[i] = -1.0;
                } else {
                    res[i] = nodes.get(a).weight / nodes.get(b).weight;
                }
            }
        }

        return res;
    }

    // 带权并查集中节点的数据结构
    class Node {
        String name;
        Node parent;
        double weight;

        Node(String name) {
            this.name = name;
            this.parent = this;
            this.weight = 1.0;
        }
    }

    // 找到name节点所在并查集中，最顶级的节点
    // 找到某个节点所在的祖先节点，并更新节点路径上的权值
    private Node find(Map<String, Node> nodes, String name) {
        Node node = nodes.get(name);
        if (node.parent != node) {
            // 找到最上面的节点
            Node root = find(nodes, node.parent.name);
            // 更新权重，为节点指向最上面节点做准备
            node.weight *= node.parent.weight;
            // 将节点指向最上面的节点，减少下次查询的路径
            node.parent = root;
        }
        return node.parent;
    }

    // 在带权并查集中合并两个节点所在的集合
    private void union(Map<String, Node> nodes, String a, String b, double val) {
        Node pa = find(nodes, a), pb = find(nodes, b);
        if (pa != pb) {
            pa.parent = pb;
            // 更新节点到根节点路径上所有边权的乘积
            pa.weight = val * nodes.get(b).weight / nodes.get(a).weight;
        }
    }
}
