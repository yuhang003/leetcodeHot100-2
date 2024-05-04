package medium;

/**
 * 97. 交错字符串
 */
public class Problem_0097_IsInterleave {

    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();
        if (s1Len + s2Len != s3Len) return false;

        // dp[i][j]代表前 i 个 s1的字符串和前 j 个 s2的字符串，能不能组成前 (i + j) 个的s3字符串
        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[0][0] = true;
        // 如果不用s2的字符，将dp[i][0]进行初始化
        for (int i = 1; i <= s1Len; i++) {
            // 因为dp代表的是前多少个字符串，假如前2个字符，那么最后一个字符的下标就是 2 - 1 = 1
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        // 同理，不使用s1的字符，将dp[0][j]初始化
        for (int j = 1; j <= s2Len; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                // 根据前面初始化的，判断后续，无论是s1还是s2，只能能组成s3都行
                // 所以动态转移方程是这样的
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[s1Len][s2Len];
    }
}
