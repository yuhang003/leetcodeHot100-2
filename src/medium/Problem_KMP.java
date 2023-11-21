package medium;

/**
 * KMP算法  解决在一个字符串中判断是否有另一个字符串，类比java中的indexOf方法
 */
public class Problem_KMP {

    public static void main(String[] args) {
        Problem_KMP kmp = new Problem_KMP();

        String s1 = "abcdff";
        String s2 = "cbdef";
        System.out.println(kmp.KMP(s1, s2));
    }

    public int KMP(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] nextArr = getNextArr(str2);

        int x = 0, y = 0;
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (nextArr[y] == -1) {
                x++;
            } else {
                y = nextArr[y];
            }
        }

        return y == str2.length ? x - y : -1;
    }

    public int[] getNextArr(char[] str) {
        if (str.length == 1) return new int[]{-1};

        int[] nextArr = new int[str.length];
        nextArr[0] = -1;
        nextArr[1] = 0;

        int i = 2, cn = 0;
        while (i < str.length) {
            if (str[i - 1] == str[cn]) {
                nextArr[i++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[i++] = 0;
            }
        }

        return nextArr;
    }
}
