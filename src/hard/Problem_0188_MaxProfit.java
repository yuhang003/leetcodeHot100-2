package hard;

import java.util.Arrays;

// 新思路解决所有股票买卖问题
// 并不是要考虑买还是卖，而是要最大化手里持有的钱。
// 买股票手里的钱减少，卖股票手里的钱增加，无论什么时刻，我们要保证手里的钱最多。
public class Problem_0188_MaxProfit {

    // 121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        // 初始时，假设买完之后，手里只有 最小值的钱，卖的话，因为没有买过，所以也不存在卖，手里有0元
        int buy = Integer.MIN_VALUE, sell = 0;

        for (int i = 0; i < prices.length; i++) {
            // 因为只能做一次交易，所以买之前，手里一定没有钱，所以是 0 - prices[i]
            buy = Math.max(buy, 0 - prices[i]);
            sell = Math.max(sell, buy + prices[i]);
        }

        // 卖完之后手里剩的钱，一定就是最终的结果
        return sell;
    }

    // 122. 买卖股票的最佳时机 II
    public int maxProfitII(int[] prices) {
        // 初始时，假设买完之后，手里只有 最小值的钱，卖的话，因为没有买过，所以也不存在卖，手里有0元
        int buy = Integer.MIN_VALUE, sell = 0;

        for (int price : prices) {
            // 因为可以做无数次交易，所以买之前，手里会有上一次卖掉之后剩的钱
            // 当然也可以不作操作，还是上次买完之后剩的钱，两者取最大值，就是对prices[i]操作之后手里剩的最多的钱
            buy = Math.max(buy, sell - price);
            // 卖也是同理
            sell = Math.max(sell, buy + price);
        }

        // 卖完之后手里剩的钱，一定就是最终的结果
        return sell;
    }

    // 123. 买卖股票的最佳时机 III
    public int maxProfitIII(int[] prices) {
        // 因为最多可以完成两笔交易，那么就需要四个状态
        // 第一次买完手里剩的钱
        int buy1 = Integer.MIN_VALUE;
        // 第一次卖完手里剩的钱
        int sell1 = 0;
        // 第二次买完手里剩的钱
        int buy2 = Integer.MIN_VALUE;
        // 第二次卖完手里剩的钱
        int sell2 = 0;

        for (int price : prices) {
            // 因为是第一次买，所以最开始手里剩的是0元
            buy1 = Math.max(buy1, 0 - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }

    // 188. 买卖股票的最佳时机 IV
    public int maxProfitIV(int k, int[] prices) {
        // 上一题最多做两次交易，需要四个状态，现在是最多k次交易，需要2k个状态
        // 如果交易次数超过一半，和不限制交易次数没有区别
        k = Math.min(k, prices.length >> 1);

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                // 上一次卖之后剩的钱，买这一次，就可以得到这一次买完之后剩余的钱
                // 也可以不操作，取两者最大值
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                // 这一次卖完之后剩余的钱，等于这一次买了之后剩余的钱加上卖了股票的钱
                // 也可以不操作，取两者最大值
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }

        return sell[k];
    }

    // 714. 买卖股票的最佳时机含手续费
    public int maxProfitHasFee(int[] prices, int fee) {
        // 这题和买卖股票II是同一个题
        // 不限制交易次数，多了一个手续费而已
        // 在买的时候花费手续费还是卖的时候花费手续费，都是一样的
        int buy = Integer.MIN_VALUE, sell = 0;

        for (int price : prices) {
            buy = Math.max(buy, sell - price);
            sell = Math.max(sell, buy + price - fee);
        }

        return sell;
    }

    // 309. 买卖股票的最佳时机含冷冻期
    public int maxProfitHasFreeze(int[] prices) {
        // 因为含冷冻期，所以我们要三个状态
        // 这一次买，手里剩的钱
        int buy = Integer.MIN_VALUE;
        // 上一次卖和这一次卖之后，手里剩的钱
        int sell_pre = 0, sell = 0;

        for (int price : prices) {
            buy = Math.max(buy, sell_pre - price);
            sell_pre = sell;
            sell = Math.max(sell, buy + price);
        }

        return Math.max(sell_pre, sell);
    }
}
