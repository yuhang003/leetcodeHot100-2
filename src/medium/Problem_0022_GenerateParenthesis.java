package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 */
public class Problem_0022_GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        process(0, 0, n, new StringBuilder(), ans);

        return ans;
    }

    public void process(int open, int close, int max, StringBuilder builder, List<String> ans) {
        if (builder.length()== max << 1) {
            ans.add(builder.toString());
            return;
        }

        if (open < max) {
            builder.append("(");
            process(open + 1, close, max, builder, ans);
            builder.deleteCharAt(builder.length() - 1);
        }

        if (close < open) {
            builder.append(")");
            process(open, close + 1, max, builder, ans);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
