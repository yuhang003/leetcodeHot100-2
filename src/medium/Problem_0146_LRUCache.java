package medium;

import java.util.HashMap;
import java.util.Map;

public class Problem_0146_LRUCache {

    static class LRUCache {

        int size;
        Map<Integer, Node<Integer, Integer>> keyNodeMap;
        NodeDoubleLinkedList<Integer, Integer> nodeList;

        public LRUCache(int capacity) {
            size = capacity;
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
        }

        public int get(int key) {
            Node<Integer, Integer> node = keyNodeMap.get(key);
            if (node != null) {
                nodeList.moveNodeToTail(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node<Integer, Integer> node = keyNodeMap.get(key);
            if (node != null) {
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else {
                node = new Node<>(key, value);
                nodeList.addNode(node);

                if (keyNodeMap.size() >= size) {
                    Node<Integer, Integer> removeNode = nodeList.removeHead();
                    keyNodeMap.remove(removeNode.key);
                }
                keyNodeMap.put(key, node);
            }
        }
    }


    static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> pre;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    static class NodeDoubleLinkedList<K, V> {
        public Node<K, V> head;
        public Node<K, V> tail;

        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) return;

            // 移除该节点
            removeNode(node);
            // 将该节点添加到尾部
            addNode(node);
        }

        public void removeNode(Node<K, V> node) {
            if (head == node) {
                removeHead();
            } else if (tail == node) {
                tail = node.pre;
                tail.next = null;
                node.pre = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.pre = null;
                node.next = null;
            }
        }

        public void addNode(Node<K, V> node) {
            if (tail == node) return;

            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        public Node<K, V> removeHead() {
            if (head == null) return null;

            Node<K, V> res = head;
            head = res.next;
            res.next = null;
            head.pre = null;

            return res;
        }
    }
}
