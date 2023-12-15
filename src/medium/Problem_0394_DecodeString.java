package medium;

/**
 * 394. 字符串解码
 */
public class Problem_0394_DecodeString {

    public static void main(String[] args) {
        Problem_0394_DecodeString tmp = new Problem_0394_DecodeString();
        String s = "3[a2[c]]";
        System.out.println(tmp.decodeString(s));
    }

    public String decodeString(String s) {
        return process(s.toCharArray(), 0)[0];
    }

    public String[] process(char[] str, int i) {
        StringBuilder builder = new StringBuilder();
        int cur = 0;

        while (i < str.length && str[i] != ']') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '[') {
                builder.append(str[i++]);
            } else {
                String[] ans = process(str, i + 1);
                while (cur-- > 0) {
                    builder.append(ans[0]);
                }
                cur = 0;
                i = Integer.parseInt(ans[1]) + 1;
            }
        }

        return new String[]{builder.toString(), String.valueOf(i)};
    }
}
