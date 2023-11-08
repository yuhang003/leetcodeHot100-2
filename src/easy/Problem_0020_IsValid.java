package easy;

/**
 * 20. 有效的括号
 *
 * 每遍历一个左括号，就往数组中添加一个右括号，
 * 遍历到右括号，就和数组最后一个右括号进行对比
 */
public class Problem_0020_IsValid {

    public boolean isValid(String s) {
        int n = s.length();
        char[] chars = new char[n];

        int index = -1;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') chars[++index] = ')';
            else if (ch == '{') chars[++index] = '}';
            else if (ch == '[') chars[++index] = ']';
            else if (index == -1 || chars[index--] != ch) return false;
        }

        return index == -1;
    }
}
