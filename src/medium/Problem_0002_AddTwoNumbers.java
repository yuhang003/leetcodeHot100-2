package medium;

import entity.ListNode;

/**
 * 2. 两数相加
 */
public class Problem_0002_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        int sum = 0, num = 0;
        while (l1 != null || l2 != null) {
            int m = l1 == null ? 0 : l1.val;
            int n = l2 == null ? 0 : l2.val;

            sum = m + n + num;
            num = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (num != 0) {
            cur.next = new ListNode(num);
        }

        return head.next;
    }
}
