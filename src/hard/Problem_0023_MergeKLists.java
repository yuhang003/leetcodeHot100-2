package hard;

import entity.ListNode;

/**
 * 23. 合并 K 个升序链表
 */
public class Problem_0023_MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        return process(lists, 0, lists.length - 1);
    }

    // 分治合并
    public ListNode process(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start > end) return null;

        int mid = start + ((end - start) >> 1);
        return mergeTwoList(process(lists, start, mid), process(lists, mid + 1, end));
    }

    // 合并两个链表
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode root = new ListNode();
        ListNode cur = root;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return root.next;
    }
}
