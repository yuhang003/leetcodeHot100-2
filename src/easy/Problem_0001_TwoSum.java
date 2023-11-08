package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 */
public class Problem_0001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }

            map.put(target - nums[i], i);
        }

        return new int[0];
    }
}
