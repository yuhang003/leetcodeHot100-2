package medium;

/**
 * 581. 最短无序连续子数组
 */
public class Problem_0581_FindUnsortedSubarray {

    public static void main(String[] args) {
        Problem_0581_FindUnsortedSubarray tmp = new Problem_0581_FindUnsortedSubarray();

        int[] nums = {2,6,4,8,10,9,15};
        System.out.println(tmp.findUnsortedSubarray(nums));
    }

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int max = nums[0], right = 0;
        int min = nums[n - 1], left = n - 1;

        // 找到最后一个乱序的下标
        for (int i = 1; i < n; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                right = i;
            }
        }

        if (right == 0) return 0;

        // 找到第一个乱序的下标
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                left = i;
            }
        }

        return right - left + 1;
    }
}
