package medium;

import entity.ListNode;

/**
 * 148. 排序链表
 */
public class Problem_0148_SortList {

    public static void main(String[] args) {
        Problem_0148_SortList temp = new Problem_0148_SortList();
//        ListNode head = ListNode.generateListNode(4, 2, 1, 3);
        ListNode head = ListNode.generateListNode(-1, 5, 3, 4, 0);
        ListNode listNode = temp.sortList(head);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow, fast;
        slow = head;
        fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode rightHead = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return mergeList(left, right);
    }

    // 合并两个有序链表
    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode sortList = new ListNode();
        ListNode cur = sortList;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return sortList.next;
    }
}
