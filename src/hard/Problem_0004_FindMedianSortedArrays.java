package hard;

/**
 * 4. 寻找两个正序数组的中位数
 */
public class Problem_0004_FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;

        if (((n + m) & 1) == 0) {
            return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left)
                    + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) / 2.0;
        }

        return getKth(nums1, 0, n - 1, nums2, 0, m - 1, left);
    }

    public int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] < nums2[j]) {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - Math.min(len1, k / 2));
        } else {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - Math.min(len2, k / 2));
        }
    }


    // 其他解法
    public double findMedianSortedArraysII(int[] nums1, int[] nums2) {
        int s = nums1.length, l = nums2.length;
        if (s > l) return findMedianSortedArraysII(nums2, nums1);

        int k = (s + l + 1) >> 1;
        int left = 0, right = s;
        int m1, m2;
        while (left < right) {
            m1 = left + ((right - left) >> 1);
            m2 = k - m1;

            if (nums1[m1] < nums2[m2]) {
                left = m1 + 1;
            } else {
                right = m1;
            }
        }

        m1 = left;
        m2 = k - m1;

        int leftMax1 = m1 == 0 ? Integer.MIN_VALUE : nums1[m1 - 1];
        int rightMin1 = m1 == s ? Integer.MAX_VALUE : nums1[m1];
        int leftMax2 = m2 == 0 ? Integer.MIN_VALUE : nums2[m2 - 1];
        int rightMin2 = m2 == l ? Integer.MAX_VALUE : nums2[m2];

        if ((s + l) % 2 == 1) return Math.max(leftMax1, leftMax2);
        return (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2.0d;
    }
}
