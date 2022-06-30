package demo.leetcode.other.link;

/**
 * 单向链表
 */
public class SLinkedList<T> {

    private T data;

    private SLinkedList<T> next;

    public SLinkedList() {
    }

    public SLinkedList(T data, SLinkedList<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SLinkedList<T> getNext() {
        return next;
    }

    public void setNext(SLinkedList<T> next) {
        this.next = next;
    }
}
