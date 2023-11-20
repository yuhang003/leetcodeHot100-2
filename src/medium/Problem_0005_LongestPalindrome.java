package medium;

/**
 * 5. 最长回文子串
 */
public class Problem_0005_LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        char[] str = manacherString(s);
        int n = str.length;
        int[] pArr = new int[n];
        int C = -1, R = -2;
        int max = -1;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            while (i + pArr[i] < n && i - pArr[i] > -1 && str[i + pArr[i]] == str[i - pArr[i]]) {
                pArr[i]++;
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            if (max < pArr[i]) {
                max = pArr[i];
                builder.setLength(0);
                builder.append(s, (i - pArr[i] + 1) >> 1, (i + pArr[i] - 1) >> 1);
            }
        }

        return builder.toString();
    }

    public char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        char[] ans = new char[n * 2 + 1];

        for (int i = 0; i < chars.length; i++) {
            ans[i * 2] = '#';
            ans[i * 2 + 1] = chars[i];
        }

        ans[n * 2] = '#';
        return ans;
    }
}
