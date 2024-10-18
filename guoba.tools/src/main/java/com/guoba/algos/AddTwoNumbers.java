package com.guoba.algos;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes
 * contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = new AddTwoNumbers().addTwoNumbers(l1, l2);
        System.out.println(result);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tl1 = l1;
        ListNode tl2 = l2;
        ListNode res = new ListNode();
        ListNode resCur = res;
        int t = 0;
        while (tl1 != null || tl2 != null || t > 0) {
            int curRes = (tl1 == null ? 0 : tl1.val) + (tl2 == null ? 0 : tl2.val) + t;
            resCur.val = curRes % 10;
            t = curRes / 10;
            if ((tl1 != null && tl1.next != null) || (tl2 != null && tl2.next != null) || t > 0) {
                resCur.next = new ListNode();
                resCur = resCur.next;
            }
            tl1 = tl1 == null ? null : tl1.next;
            tl2 = tl2 == null ? null : tl2.next;
        }
        return res;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        if (next == null) {
            return "" + val;
        }
        return val + ", " + next;
    }
}