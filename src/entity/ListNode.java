package entity;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode generateListNode(Integer... nums) {
        ListNode head = new ListNode();
        ListNode cur = head;

        for (Integer num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }

        return head.next;
    }
}
