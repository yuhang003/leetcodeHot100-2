package medium;

/**
 * 416. 分割等和子集
 */
public class Problem_0416_CanPartition {

    public static void main(String[] args) {
        Problem_0416_CanPartition tmp = new Problem_0416_CanPartition();
        int[] nums = {1,5,11,5};
        System.out.println(tmp.canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) return false;

        int maxNum = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        if ((sum & 1) == 1) return false;
        int target = sum / 2;
        if (maxNum > target) return false;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }

        return dp[target];
    }
}
