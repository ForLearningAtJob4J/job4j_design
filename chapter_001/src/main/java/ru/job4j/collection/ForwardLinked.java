package ru.job4j.collection;

import java.util.*;


public class ForwardLinked<E> implements Iterable<E> {
    private final float multiplier = 1.5f;
    private Node<E> head;
    private Node<E> tail;
    private int modCount;
    private int size;

    public int size() {
        return size;
    }

    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            modCount++;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        size++;
        modCount++;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            modCount++;
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        int i = 0;
        Node<E> node = head;
        while (i < index) {
            node = node.next;
            i++;
        }
        return node.value;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        modCount++;
        size--;
    }

    public E deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E value = tail.value;
        if (head != tail) {
            Node<E> node = head;
            while (node.next != tail) {
                node = node.next;
            }
            node.next = null;
            tail = node;
        } else {
            head = null;
            tail = null;
        }
        modCount++;
        size--;

        return value;
    }

    public void revert() {
        if (size > 0) {
            Node<E> pointer = head;
            size = 0;
            while (pointer != null) {
                addFirst(pointer.value);
                pointer = pointer.next;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> node = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
