import java.util.Scanner;

public class ReverseKGroup {

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // Function to reverse nodes in k-group
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kthNode = getKthNode(prevGroupEnd, k);
            if (kthNode == null) break;

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kthNode.next;

            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            prevGroupEnd.next = kthNode;
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    // Helper function to get kth node
    private static ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }

    // Function to append node at the end
    private static void append(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Function to print linked list
    private static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ✅ Create linked list directly in code
        ListNode head = new ListNode(1);
        append(head, 2);
        append(head, 3);
        append(head, 4);
        append(head, 5);
        append(head, 6);

        // ✅ Take k as input
        System.out.print("Enter value of k: ");
        int k = sc.nextInt();

        System.out.println("\nOriginal List:");
        printList(head);

        // ✅ Reverse in k-group
        head = reverseKGroup(head, k);

        System.out.println("\nReversed in groups of " + k + ":");
        printList(head);

        sc.close();
    }
}
