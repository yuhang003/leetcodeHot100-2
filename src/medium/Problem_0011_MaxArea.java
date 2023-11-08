package medium;

/**
 * 11. 盛最多水的容器
 */
public class Problem_0011_MaxArea {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;

        int maxArea = 0;
        while (l < r) {
            maxArea = Math.max(maxArea, (r - l) * (height[l] < height[r] ? height[l++] : height[r--]));
        }

        return maxArea;
    }
}
