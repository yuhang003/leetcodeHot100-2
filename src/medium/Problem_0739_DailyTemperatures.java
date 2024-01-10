package medium;

import java.util.Arrays;

/**
 * 739. 每日温度
 */
public class Problem_0739_DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[]{};

        int n = temperatures.length;
        int[] stack = new int[n];
        int[] ans = new int[n];

        int index = -1;
        for (int i = 0; i < n; i++) {
            while (index >= 0 && temperatures[i] > temperatures[stack[index]]) {
                int preIndex = stack[index--];
                ans[preIndex] = i - preIndex;
            }
            stack[++index] = i;
        }

        while (index >= 0) {
            ans[stack[index--]] = 0;
        }

        return ans;
    }
}
