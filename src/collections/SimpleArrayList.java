package collections;

import java.util.Arrays;

public class SimpleArrayList<E> {

    private static final Object EMPTY_ELEMENTDATA[] = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;


    public SimpleArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public SimpleArrayList(int capacity){
        this();
        if(capacity < 0){
            throw new IllegalArgumentException();
        }
        ensureCapacity(capacity);
    }

    public boolean add(E value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
        return true;
    }

    public boolean add(E value, int index){
        rangeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index+1, size - index);
        elementData[index] = value;
        ++size;
        return true;
    }


    public E remove(int index) {
        rangeCheck(index);

        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;

        return oldValue;
    }


    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    private void ensureCapacity(int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity > MAX_ARRAY_SIZE) {
            newCapacity = hugeCapacity(minCapacity);
        }

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return MAX_ARRAY_SIZE;
    }

    public void print(){
        for(int i = 0; i < size; i++){
            System.out.println(elementData(i));
        }
    }

    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    public int size() {
        return size;
    }
}
