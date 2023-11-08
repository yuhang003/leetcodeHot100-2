package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. 全排列
 */
public class Problem_0046_Permute {
    List<List<Integer>> ans;
    List<Integer> list;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        boolean[] used = new boolean[nums.length];
        process(nums, used);

        return ans;
    }

    public void process(int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                process(nums, used);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }


    /**
     * 全排列2，该题nums中有重复元素，返回所有不重复的全排列。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        process2(nums, used);

        return ans;
    }

    public void process2(int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同一树枝nums[i - 1]使用过
            // used[i - 1] == false，说明同一树层nums[i - 1]使用过
            // 如果同一树层nums[i - 1]使用过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;

            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                process2(nums, used);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

}
