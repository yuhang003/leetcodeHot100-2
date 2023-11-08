package medium;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class Problem_0034_SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int n = nums.length;
        int start = lessMostRight(nums, target, 0, n - 1) + 1;
        if (start == n || nums[start] != target) return new int[]{-1, -1};

        int end = lessMostRight(nums, target + 1, start, n - 1);
        return new int[]{start, end};
    }

    public int lessMostRight(int[] nums, int target, int start, int end) {
        int m, ans = -1;

        while (start <= end) {
            m = start + ((end - start) >> 1);

            if (nums[m] < target) {
                ans = m;
                start = m + 1;
            } else {
                end = m - 1;
            }
        }

        return ans;
    }
}
