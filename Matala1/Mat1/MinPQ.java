package M_1;

//AA
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key>
implements Iterable<Key> {
    private Key[] pq;
    private int N;
    private Comparator<Key> comparator;

    public MinPQ(int initCapacity) {
        this.pq = (Key[]) new Object[initCapacity + 1];
        this.N = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        this.pq = (Key[]) new Object[initCapacity + 1];
        this.N = 0;
    }

    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MinPQ(Key[] keys) {
        this.N = keys.length;
        this.pq = (Key[]) new Object[keys.length + 1];
        int i = 0;
        while (i < this.N) {
            this.pq[i + 1] = keys[i];
            ++i;
        }
        int k = this.N / 2;
        while (k >= 1) {
            this.sink(k);
            --k;
        }
        assert (this.isMinHeap());
    }

    public boolean isEmpty() {
        if (this.N == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.N;
    }

    public Key min() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return this.pq[1];
    }

    private void resize(int capacity) {
        assert (capacity > this.N);
        Object[] temp = new Object[capacity];
        int i = 1;
        while (i <= this.N) {
            temp[i] = this.pq[i];
            ++i;
        }
        this.pq = (Key[]) temp;
    }

    public void insert(Key x) {
        if (this.N == this.pq.length - 1) {
            this.resize(2 * this.pq.length);
        }
        this.pq[++this.N] = x;
        this.swim(this.N);
        assert (this.isMinHeap());
    }

    public Key delMin() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        this.exch(1, this.N);
        Key min = this.pq[this.N--];
        this.sink(1);
        this.pq[this.N + 1] = null;
        if (this.N > 0 && this.N == (this.pq.length - 1) / 4) {
            this.resize(this.pq.length / 2);
        }
        assert (this.isMinHeap());
        return min;
    }

    private void swim(int k) {
        while (k > 1 && this.greater(k / 2, k)) {
            this.exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= this.N) {
            int j = 2 * k;
            if (j < this.N && this.greater(j, j + 1)) {
                ++j;
            }
            if (!this.greater(k, j)) break;
            this.exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (this.comparator == null) {
            if (((Comparable)this.pq[i]).compareTo(this.pq[j]) > 0) {
                return true;
            }
            return false;
        }
        if (this.comparator.compare(this.pq[i], this.pq[j]) > 0) {
            return true;
        }
        return false;
    }

    private void exch(int i, int j) {
        Key swap = this.pq[i];
        this.pq[i] = this.pq[j];
        this.pq[j] = swap;
    }

    private boolean isMinHeap() {
        return this.isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > this.N) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= this.N && this.greater(k, left)) {
            return false;
        }
        if (right <= this.N && this.greater(k, right)) {
            return false;
        }
        if (this.isMinHeap(left) && this.isMinHeap(right)) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                pq.insert(item);
                continue;
            }
            if (pq.isEmpty()) continue;
            StdOut.print(String.valueOf((String)pq.delMin()) + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }

    private class HeapIterator
    implements Iterator<Key> {
        private MinPQ<Key> copy;

        public HeapIterator() {
            this.copy = MinPQ.this.comparator == null ? new MinPQ(MinPQ.this.size()) : new MinPQ(MinPQ.this.size(), MinPQ.this.comparator);
            int i = 1;
            while (i <= MinPQ.this.N) {
                this.copy.insert(MinPQ.this.pq[i]);
                ++i;
            }
        }

        @Override
        public boolean hasNext() {
            return !this.copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Key next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return this.copy.delMin();
        }
    }

}

