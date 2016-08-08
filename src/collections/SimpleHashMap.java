package collections;

public class SimpleHashMap<K, V> {

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final float TABLE_MAX_SIZE = Integer.MAX_VALUE/2+1;

    private Node<K, V> table[];

    private float loadFactor;
    private int threshold;
    private int size;
    private int capacity;


    public SimpleHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = 8;
        evaluateThreshold();
        table = new Node[capacity];
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        ensureCapacity();
        int h = indexFor(key.hashCode(), capacity);
        putValue(h, key, value);
        ++size;
    }


    public V get(K key){
        int position = indexFor(key.hashCode(), capacity);
        Node<K,V> e = table[position];
        while(e != null){
            if(e.getKey().hashCode() == key.hashCode()){
                return e.getValue();
            }else{
                e = e.next;
            }
        }
        return null;
    }



    public V remove(K key){
        int position = indexFor(key.hashCode(), capacity);
        Node<K,V> e = table[position];
        Node<K,V> value;
        while(e.next != null){
            if(e.next.getKey().hashCode() == key.hashCode()){
                value = e.next;
                e.next = value.next;
                --size;
                return value.getValue();
            }else{
                e = e.next;
            }
        }
        return null;
    }


    private void putValue(int position, K key, V value) {
        Node<K, V> current = table[position];
        table[position] = new Node<>(key, value, current);
    }

    public void print() {
        for (int i = 0; i < capacity; i++) {
            Node<K,V>temp = table[i];
            if (temp == null) {
                System.out.println("null");
            } else {
                while (temp != null) {
                    System.out.print(temp.getValue() + " ");
                    temp = temp.next;
                }
                System.out.println();
            }
        }
    }

    private int evaluateThreshold(){
        threshold = (int)(capacity*loadFactor);
        return threshold;
    }

    private void ensureCapacity(){
        if(capacity == TABLE_MAX_SIZE){
            return;
        }
        if(size > threshold){
            resize();
        }
    }

    private void resize() {
        int oldCapacity = capacity;
        capacity = capacity << 1;
        Node<K, V> newTable[] = new Node[capacity];

        for (int i = 0; i < oldCapacity; i++) {
            while (table[i] != null){
                int pos = indexFor(table[i].getKey().hashCode(), capacity);
                Node<K, V> current = newTable[pos];
                newTable[pos] = new Node<>(table[i].getKey(), table[i].getValue(), current);
                table[i] = table[i].next;
            }
        }
        evaluateThreshold();
        table = newTable;
    }


    private static int indexFor(int hash, int capacity) {
        int h = hash^(hash>>>16);
        return h & (capacity - 1);
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }
}
