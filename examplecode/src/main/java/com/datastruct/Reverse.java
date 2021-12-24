package com.datastruct;

class Reverse {
    public static ListNode reverse(ListNode head) {
        // no need to reverse if head is null
        // or there is only 1 node.
        if (head == null || head.next == null) {
            return head;
        }

        ListNode listToDo = head.next;
        ListNode reversedList = head;

        reversedList.next = null;

        while (listToDo != null) {
            ListNode temp = listToDo;
            listToDo = listToDo.next;

            temp.next = reversedList;
            reversedList = temp;
        }

        return reversedList;
    }

    static ListNode reverseListRecursive(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return head;
        }
        ListNode node = reverseListRecursive(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return node;
    }

    static ListNode constructLinkedList(int[] arr) {
        ListNode head = null;
        ListNode tail = null;
        for (int i = 0; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            if (head == null) {
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
        }
        return head;
    }

    static class ListNode {
        int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode listHead = null;
        int[] arr = {7, 14, 21, 28};
        listHead = Reverse.constructLinkedList(arr);
        System.out.println("ITERATIVE:");
        System.out.print("Original: ");
        System.out.println(listHead);

        listHead = reverse(listHead);
        System.out.print("After Reverse: ");
        System.out.println(listHead);

        System.out.println("RECURSIVE:");
        int[] arrRec = {5, 10, 15, 20, 25};
        listHead = Reverse.constructLinkedList(arrRec);
        System.out.print("Original: ");
        System.out.println(listHead);

        listHead = reverseListRecursive(listHead);
        System.out.print("After Reverse: ");
        System.out.println(listHead);
    }
}
