package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 */
public class Problem_0017_LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;

        StringBuilder builder = new StringBuilder();
        process(digits, 0, builder, ans);
        return ans;
    }

    String[] letterMap = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public void process(String digits, int index, StringBuilder builder, List<String> ans) {
        if (index == digits.length()) {
            ans.add(builder.toString());
            return;
        }

        int num = digits.charAt(index) - '0';
        String letter = letterMap[num];
        for (int i = 0; i < letter.length(); i++) {
            builder.append(letter.charAt(i));
            process(digits, index + 1, builder, ans);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
