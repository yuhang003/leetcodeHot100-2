package medium;

/**
 * 300. 最长递增子序列
 */
public class Problem_0300_LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int[] ends = new int[nums.length];

        int ans = 0;
        for (int num : nums) {
            int L = 0, R = ans;
            // 从左到右找到第一个 >= num的数字的下标，替换成num
            while (L < R) {
                int mid = L + ((R - L) >> 1);
                if (ends[mid] < num) {
                    L = mid + 1;
                } else {
                    R = mid;
                }
            }
            ends[L] = num;

            if (L == ans) {
                ans++;
            }
        }

        return ans;
    }
}
