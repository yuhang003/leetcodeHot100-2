package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 406. 根据身高重建队列
 */
public class Problem_0406_ReconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            // 按身高降序排列
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else { // 身高相等，按排在前面的人数升序排列
                return a[1] - b[1];
            }
        });

        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }

        return ans.toArray(new int[people.length][2]);
    }
}
