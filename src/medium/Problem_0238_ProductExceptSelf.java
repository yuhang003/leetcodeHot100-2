package medium;

/**
 * 238. 除自身以外数组的乘积
 */
public class Problem_0238_ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;

        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int cur = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= cur;
            cur *= nums[i];
        }

        return ans;
    }
}
