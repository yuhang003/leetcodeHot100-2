package medium;

/**
 * 647. 回文子串
 */
public class Problem_0647_CountSubstrings {

    public int countSubstrings(String s) {
        return manacher(s);
    }

    public int manacher(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];

        int C = -1, R = -1;
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;

            while (i + pArr[i] < str.length && i - pArr[i] >= 0) {
                if (str[i - pArr[i]] == str[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            ans += (pArr[i] >> 1);
        }

        return ans;
    }

    public char[] manacherString(String s) {
        char[] chars = new char[s.length() * 2 + 1];

        int index = 0;
        chars[index++] = '#';

        for (char ch : s.toCharArray()) {
            chars[index++] = ch;
            chars[index++] = '#';
        }

        return chars;
    }
}
