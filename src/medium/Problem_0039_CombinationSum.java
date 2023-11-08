package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 */
public class Problem_0039_CombinationSum {

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        process(candidates, 0, target, new ArrayList<>());
        return ans;
    }

    public void process(int[] candidates, int start, int rest, List<Integer> list) {
        if (rest == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (rest >= candidates[i]) {
                list.add(candidates[i]);
                process(candidates, i, rest - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }
    }
}
