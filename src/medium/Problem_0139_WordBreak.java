package medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem_0139_WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Node root = new Node();
        int index;
        Node cur;

        for (String word : wordDict) {
            cur = root;

            for (char ch : word.toCharArray()) {
                index = ch - 'a';

                if (cur.nodes[index] == null) {
                    cur.nodes[index] = new Node();
                }
                cur = cur.nodes[index];
            }
            cur.isEnd = true;
        }

        int n = s.length();
        // dp含义：在 s 字符串中，以 i 到 n - 1的字符，能不能被字符串列表拆分
        // 所以最终返回dp[0]
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;

        for (int i = n - 1; i >= 0; i--) {
            cur = root;
            for (int end = i; end < n; end++) {
                index = s.charAt(end) - 'a';
                cur = cur.nodes[index];

                if (cur == null) break;
                if (cur.isEnd) dp[i] = dp[end + 1];
                if (dp[i]) break;
            }
        }

        return dp[0];
    }

    class Node {
        public boolean isEnd;
        public Node[] nodes;

        public Node() {
            nodes = new Node[26];
        }
    }
}
