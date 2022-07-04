package demo.leetcode.other.link;

/**
 * 单向链表基本操作
 */
public class SLinkListTest {

    public static void main(String[] args) {
        SLinkedList<Integer> sLinkedList = new SLinkedList<>(1,
                new SLinkedList<>(2,
                        new SLinkedList<>(3,
                                new SLinkedList<>(4,
                                        new SLinkedList<>(5, null)))));
        printLink(sLinkedList);
        System.out.println("----------");
        printLink(inversion(sLinkedList));
    }

    // 链表反转
    public static <T> SLinkedList<T> inversion(SLinkedList<T> linkedList) {
        SLinkedList<T> prev = null;
        SLinkedList<T> next = linkedList;
        SLinkedList<T> current;

        while (next != null) {
            current = next; //当前node
            next = next.getNext(); // 剩余链
            current.setNext(prev); // 当前node成为链头
            prev = current; // 将prev刷新成为当前的node
        }

        return prev;
    }

    public static <T> void printLink(SLinkedList<T> sLinkedList) {
        if (sLinkedList != null) {
            while (sLinkedList != null) {
                System.out.println(sLinkedList.getData());
                sLinkedList = sLinkedList.getNext();
            }
        }
    }

}
