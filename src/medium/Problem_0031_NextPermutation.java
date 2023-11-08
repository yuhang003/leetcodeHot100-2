package medium;

/**
 * 31. 下一个排列
 */
public class Problem_0031_NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) return;

        int firstLess = -1;
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }

        if (firstLess == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        int rightClosesMore = -1;
        for (int i = n - 1; i > firstLess; i--) {
            if (nums[i] > nums[firstLess]) {
                rightClosesMore = i;
                break;
            }
        }

        swap(nums, firstLess, rightClosesMore);
        reverse(nums, firstLess + 1, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
