package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 */
public class Problem_0560_SubarraySum {

    public static void main(String[] args) {
        Problem_0560_SubarraySum tmp = new Problem_0560_SubarraySum();

        int[] nums = {1, 2, 3};
        int k = 3;

        System.out.println(tmp.subarraySum(nums, k));
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);

        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            count += preSumMap.getOrDefault(sum - k, 0);
            preSumMap.put(sum, preSumMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
