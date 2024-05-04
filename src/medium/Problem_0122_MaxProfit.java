package medium;

/**
 * 买卖股票的最佳时机
 */
public class Problem_0122_MaxProfit {

    // 122. 买卖股票的最佳时机 II
    // 适用于其他买卖股票题(不限制卖买次数)的动态规划方法
    public int maxProfitII(int[] prices) {
        int len = prices.length;

        // dp[i][0]代表第 i 天不持有股票可获得的最大利润
        // dp[i][1]代表第 i 天持有可获得的最大利润（注意是第 i 天持有，而不是第 i 天买入）
        int[][] dp = new int[len][2];
        // 第0天如果没有持有股票，说明要不就是没买过，要不就是当天买了当天卖了，利润都是0
        dp[0][0] = 0;
        // 第0天如果持有股票，说明肯定是第0天买的，利润就是-prices[0]
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 对于第 i 天不持有，可以从两个状态转移过来：1. 昨天也不持有；2. 昨天持有，今天卖出。两者取较大值。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 对于第 i 天持有，可以从两个状态转移过来：1. 昨天也持有；2. 昨天不持有，今天买入。两者取较大值。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        // 因为对于最后一天的利润来说，肯定是卖掉利润最大，即dp[len - 1][1]肯定没有dp[len - 1][0]大
        // 故直接返回dp[len - 1][0]即可
        return dp[len - 1][0];
    }

    // 空间优化，使用两个变量代替dp二维数组实现，滚动数组优化思想
    public int maxProfitUsedGunDong(int[] prices) {
        int len = prices.length;

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];

        int newDp0;
        for (int i = 1; i < len; i++) {
            newDp0 = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            dp[0] = newDp0;
        }

        return dp[0];
    }

    // 714. 买卖股票的最佳时机含手续费
    public int maxProfitHasFee(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    // 309. 买卖股票的最佳时机含冷冻期
    public int maxProfitHasFreeze(int[] prices) {
        int n = prices.length;
        // 因为多了一个冷冻期，所以在没有持有股票的时候，要标记一下是不是当天卖出了，便于后面递推进行判断

        // dp[i][0]代表第 i 天不持有股票且第 i 天没有卖出
        // dp[i][1]代表第 i 天持有可获得的最大利润（注意是第 i 天持有，而不是第 i 天买入）
        // dp[i][2]代表第 i 天不持有股票且第 i 天卖出了
        int[][] dp = new int[n][3];
        // 第0天不持有股票且第0天没有卖出，代表根本没有卖买，所以利润为0
        dp[0][0] = 0;
        // 第0天持有，肯定是刚买的第0天的股票，所以利润为-prices[0]
        dp[0][1] = -prices[0];
        // 第0天不持有股票且第0天卖出，说明第0天买第0天卖了，所以利润为0
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            // 第i天不持股且第i天没卖出的，也就是第 i 天没有股票，而且还不是因为第 i 天卖了它才没有的
            // 可以从两个状态转移过来：1. 昨天也不持有也没卖出；2. 昨天持有但昨天卖出了。两者取较大值。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            // 第 i 天持股，可以从两个状态转移过来：1. 昨天就持股了；
            // 2. 今天要买，因为有冷冻期，所以要求昨天没有持股，且昨天没有卖出，即dp[i - 1][0]，再减去prices[i]，因为刚买的
            // 两者取最大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 第 i 天不持有，且第 i 天卖出，说明 i - 1一定要持有，即dp[i - 1][1]，再加上prices[i]，因为卖掉了
            dp[i][2] = dp[i - 1][1] + prices[i];
        }

        // 从两个最终不持有股票的情况中，选出一个最大值
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }

    // 空间优化
    public int maxProfitHasFreezeUsedGunDong(int[] prices) {
        int n = prices.length;

        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;

        int newDp0, newDp1;
        for (int i = 1; i < n; i++) {
            newDp0 = Math.max(dp[0], dp[2]);
            newDp1 = Math.max(dp[1], dp[0] - prices[i]);
            dp[2] = dp[1] + prices[i];
            dp[0] = newDp0;
            dp[1] = newDp1;
        }

        return Math.max(dp[0], dp[2]);
    }
}
