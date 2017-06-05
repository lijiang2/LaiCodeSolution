package com.example.laicode;

/**
 * Created by lijiang on 6/4/17.
 */

//import java.util.HashMap;
//import java.util.Map;

import java.util.HashMap;
import java.util.Map;

/**
 * Each of the nodes in the linked list has another pointer
 * pointing to a random node in the list or null.
 * Make a deep copy of the original list.
 */
public class DeepCopyLinkedList {

    public class RandomListNode {
        public int value;
        public RandomListNode next;
        public RandomListNode random;
        // Constructor
        public RandomListNode(int value) {
            this.value = value;
        }
    }

    public RandomListNode copy(RandomListNode head) {
        // Corner Case:
        if (head == null) {
            return null;
        }
        // Allocate memory for lookup map
        Map<RandomListNode,RandomListNode> lookup = new HashMap<>();
        // Copy head node
        RandomListNode newHead = new RandomListNode(head.value);
        // put head and newHead pair in lookup map
        lookup.put(head, newHead);
        // current node in output list
        RandomListNode cur = newHead;
        // walk through the list
        while (head != null) {
            // copy next
            if (head.next != null) {
                if (!lookup.containsKey(head.next)) {
                    // copy then add to map
                    RandomListNode newNext = new RandomListNode(head.next.value);
                    lookup.put(head.next, newNext);
                }
                // use the existing node in map
                cur.next = lookup.get(head.next);
            }
            // copy random
            if (head.random != null) {
                if (!lookup.containsKey(head.random)) {
                    RandomListNode newRandom = new RandomListNode(head.random.value);
                    lookup.put(head.random, newRandom);
                }
                cur.random = lookup.get(head.random);
            }
            // update cur
            head = head.next;
            cur = cur.next;
        }

        return newHead;
    }

    public static void main (String[] args) {
        DeepCopyLinkedList list = new DeepCopyLinkedList();
        DeepCopyLinkedList.RandomListNode n1 = list.new RandomListNode(10);
        DeepCopyLinkedList.RandomListNode n2 = list.new RandomListNode(20);
        DeepCopyLinkedList.RandomListNode n3 = list.new RandomListNode(30);
        DeepCopyLinkedList.RandomListNode n4 = list.new RandomListNode(40);
        DeepCopyLinkedList.RandomListNode n5 = list.new RandomListNode(50);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        n1.random = n3;
        n2.random = null;
        n3.random = n5;
        n4.random = n3;
        n5.random = n2;

        // Copy
        DeepCopyLinkedList test = new DeepCopyLinkedList();
        RandomListNode outHead = test.copy(n1);

        // Print
        System.out.println("Print by Next");
        RandomListNode printN = outHead;
        while (printN != null) {
            if (printN.next == null) {
                System.out.printf("Current: %d, Next: Null \n", printN.value);
            }
            else {
                System.out.printf("Current: %d, Next: %d \n", printN.value, printN.next.value);
            }
            printN = printN.next;
        }
        System.out.println("===============");
        System.out.println("Print by Random");
        RandomListNode printR = outHead;
        while (printR != null) {
            if (printR.random == null) {
                System.out.printf("Current: %d, Next: Null \n", printR.value);
            }
            else {
                System.out.printf("Current: %d, Next: %d \n", printR.value, printR.random.value);
            }
            printR = printR.random;
        }
    }
}
