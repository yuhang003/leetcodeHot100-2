package medium;

/**
 * 198. 打家劫舍
 */
public class Problem_0198_Rob {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int preP = nums[0];
        int pre = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int tmp = pre;
            pre = Math.max(pre, preP + nums[i]);
            preP = tmp;
        }

        return pre;
    }
}
