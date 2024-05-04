package medium;

/**
 * 72. 编辑距离
 */
public class Problem_0072_MinDistance {

    public int minDistance(String word1, String word2) {
        return minCost(word1, word2, 1, 1, 1);
    }

    // 本题每种变化花费都是1，如果有的变种题每种花费不一样的时候，这三个参数才有意义
    public int minCost(String word1, String word2, int ic, int rc, int dc) {
        int m = word1.length(), n = word2.length();
        // dp[i][j] 代表 前 i 个 word1的字符最少需要变换几次，才能变成word2中前 j 个字符
        int[][] dp = new int[m + 1][n + 1];
        // word2中前0个字符，word1中有几个字符，就要删除几次
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * dc;
        }
        // word1中前0个字符，word2中有几个字符，word1就要添加几个字符
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j * ic;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果比较的字符相同，那么不需要变化，直接等于之前的变化次数就行
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果比较的字符不一样，有三种变化方式
                    // 1、替换，将当前的word1的字符替换成word2的字符即可，替换之后i和j值相同了，
                    // 变化次数就是dp[i - 1][j - 1] + 替换所需的花费
                    // 2、添加，在当前的word1的字符后添加一个word2的字符，相当于 i + 1 和 j值相同了，
                    // 变化次数就是dp[i][j - 1] + 添加所需的花费
                    // 3、删除，将当前word1的字符删除，但是删除的值一定不是word2的字符，所以还是得判断 i - 1和j是否相同
                    // 所以变化次数就是dp[i - 1][j] + 删除需要的花费
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + rc, Math.min(dp[i - 1][j] + dc, dp[i][j - 1] + ic));
                }
            }
        }

        return dp[m][n];
    }
}
