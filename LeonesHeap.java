public class LeonesHeap {

    private int[] heap;
    private int heapSize;
    private boolean isMinHeap;

    public LeonesHeap(int capacity, boolean isMinHeap) {
        this.heap = new int[capacity + 1]; 
        this.heapSize = 0;
        this.isMinHeap = isMinHeap;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    public void insert(int key) {
        if (heapSize == heap.length - 1) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        heapSize++;
        heap[heapSize] = key;
        heapifyUp(heapSize);
    }

    private void heapifyUp(int i) {
        while (i > 1) {
            int parentIndex = parent(i);
            if (isMinHeap && heap[i] < heap[parentIndex] || !isMinHeap && heap[i] > heap[parentIndex]) {
                swap(i, parentIndex);
                i = parentIndex;
            } else {
                break;
            }
        }
    }

    public int extractMin() {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int minKey = heap[1];
        heap[1] = heap[heapSize];
        heapSize--;
        heapifyDown(1);
        return minKey;
    }

    public int extractMax() {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int maxKey = heap[1];
        heap[1] = heap[heapSize];
        heapSize--;
        heapifyDown(1);
        return maxKey;
    }

    private void heapifyDown(int i) {
        while (leftChild(i) <= heapSize) {
            int leftChildIndex = leftChild(i);
            int rightChildIndex = rightChild(i);
            int smallestChildIndex = leftChildIndex;
            if (rightChildIndex <= heapSize && (isMinHeap && heap[rightChildIndex] < heap[leftChildIndex] || !isMinHeap && heap[rightChildIndex] > heap[leftChildIndex])) {
                smallestChildIndex = rightChildIndex;
            }
            if (isMinHeap && heap[i] > heap[smallestChildIndex] || !isMinHeap && heap[i] < heap[smallestChildIndex]) {
                swap(i, smallestChildIndex);
                i = smallestChildIndex;
            } else {
                break;
            }
        }
    }

    public int peek() {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return heap[1];
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i <= heapSize; i++) {
            sb.append(heap[i]);
            if (i < heapSize) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LeonesHeap minHeap = new LeonesHeap(10, true);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        System.out.println("Min-Heap: " + minHeap);

        System.out.println("Extracted minimum: " + minHeap.extractMin());

        System.out.println("Updated Min-Heap: " + minHeap);
    }
  }
              
