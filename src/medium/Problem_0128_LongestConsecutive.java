package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 */
public class Problem_0128_LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestCount = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int count = 1;

                while (set.contains(num + 1)) {
                    num++;
                    count++;
                }

                longestCount = Math.max(longestCount, count);
            }
        }

        return longestCount;
    }
}
