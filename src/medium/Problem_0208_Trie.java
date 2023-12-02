package medium;

/**
 * 208. 实现 Trie (前缀树)
 */
public class Problem_0208_Trie {

    class Trie {
        private final Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            Node cur = root;
            for (char ch : chars) {
                Node node = cur.nextNodes[ch - 'a'];
                if (node == null) {
                    cur.nextNodes[ch - 'a'] = new Node();
                    node = cur.nextNodes[ch - 'a'];
                }
                cur = node;
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();

            Node cur = root;
            for (char ch : chars) {
                Node node = cur.nextNodes[ch - 'a'];
                if (node == null) {
                    return false;
                }
                cur = node;
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            Node cur = root;
            for (char ch : chars) {
                Node node = cur.nextNodes[ch - 'a'];
                if (node == null) {
                    return false;
                }
                cur = node;
            }
            return true;
        }
    }

    class Node {
        public Node[] nextNodes;
        public boolean isEnd;

        public Node() {
            nextNodes = new Node[256];
        }
    }
}
