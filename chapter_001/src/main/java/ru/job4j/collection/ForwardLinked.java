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
        size++;
        modCount++;
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int pointer;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(pointer++);
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
