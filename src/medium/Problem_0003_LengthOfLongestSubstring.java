package medium;

import java.util.Arrays;

/**
 * 3. 无重复字符的最长子串
 *
 * 思路：取以每一个字符结尾，往左推不重复的最大长度
 */
public class Problem_0003_LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int pre = 1, ans = 1;
        char[] str = s.toCharArray();
        int[] map = new int[256];
        Arrays.fill(map, -1);
        map[str[0]] = 0;

        for (int i = 1; i < str.length; i++) {
            pre = Math.min(pre + 1, i - map[str[i]]);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }

        return ans;
    }
}
