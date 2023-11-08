package medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈
 *
 * 使用数组代替栈
 */
public class Problem_0155_MinStack {

    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }

    static class MinStack {
        private final int[] valueStack;
        private final int[] minStack;
        private int valueIndex;
        private int minIndex;

        public MinStack() {
            valueStack = new int[30000];
            minStack = new int[30000];
            valueIndex = -1;
            minIndex = -1;
        }

        public void push(int val) {
            valueStack[++valueIndex] = val;

            if (minIndex < 0) {
                minStack[++minIndex] = val;
            } else {
                int tmp = Math.min(minStack[minIndex], val);
                minStack[++minIndex] = tmp;
            }
        }

        public void pop() {
            valueIndex--;
            minIndex--;
        }

        public int top() {
            return valueIndex < 0 ? -1 : valueStack[valueIndex];
        }

        public int getMin() {
            return minIndex < 0 ? -1 : minStack[minIndex];
        }
    }
}
