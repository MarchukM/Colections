package collections;

import java.util.NoSuchElementException;

public class
SimpleLinkedList<E> {

    private Node<E> last;
    private Node<E> first;
    private int size;

    public SimpleLinkedList() {
        last = null;
        first = null;
        size = 0;
    }

    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
        }

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        void print() {
            System.out.println(data.toString());
        }

        E getElement() {
            return data;
        }
    }


    public boolean isEmpty() {
        return first == null;
    }

    public void addFirst(E element) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        ++size;
    }

    public int size() {
        return size;
    }

    public void addLast(E element) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(last, element, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        ++size;
    }

    public boolean delete(E key) {
        Node<E> current = first;
        Node<E> previous = first;

        if (isEmpty()) {
            return false;
        }

        while (current.getElement() != key) {
            if (current.next == null) {
                return false;
            }
            previous = current;
            current = current.next;
        }
        if (current == first) {
            deleteFirst();
        } else if(current == last){
            deleteLast();
        }else
            previous.next = current.next;
        --size;
        return true;
    }


    public boolean deleteFirst() {
        if (isEmpty()) {
            return false;
        }

        Node<E> next = first.next;
        first.next = null;
        first.data = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        --size;
        return true;
    }

    public E getLast() {
        if(last == null)
            throw new NoSuchElementException();
        return last.data;
    }

    public E getFirst(){
        if(first == null)
            throw new NoSuchElementException();
        return first.data;
    }


    public boolean deleteLast() {
        if (isEmpty())
            return false;
        Node<E> prev = last.prev;
        last.prev = null;
        last.data = null;
        last = prev;
        if(prev == null)
            first = null;
        else
            last.next = null;
        --size;
        return true;
    }

    public void printList() {
        if (isEmpty()) {
            return;
        }

        Node<E> temp = first;

        while (temp != null) {
            temp.print();
            temp = temp.next;
        }
    }

}