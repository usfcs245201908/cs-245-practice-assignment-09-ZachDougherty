import java.util.Arrays;
/************************
 * This is a MinHeap array 
 * implementation.
 ************************/

public class BinaryHeap {
    private int size;
    private int[] data;

    public BinaryHeap() {
        size = 0;
        data = new int[10];
    }

    public void add(int number) {
        /**********************************
         * The add function adds a new element
         * to the end of the array, then
         * compares the value of the newly 
         * added element to its parent, 
         * making appropriate swaps is the
         * child is of less value than its
         * parent.
         **********************************/
        if (size + 1 == data.length) {
            grow();
        }
        data[size++] = number;
        int child_indx = size - 1;
        int parent_indx = (child_indx - 1) / 2;
        while (parent_indx >= 0 && data[parent_indx] > data[child_indx]) {
            swap(parent_indx, child_indx);
            child_indx = parent_indx;
            parent_indx = (child_indx - 1) / 2;
        }
    }

    public void add(int[] arr) {
        /**********************************
         * I thought it would be useful to add
         * this function in the case where we
         * needed to add an array of elements
         * to the heap and so thought I 
         * would include it in case I needed
         * to use this implementation in the
         * future.
         */
        for (int i=0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    public int remove() {
        /*****************************************
         * The remove function returns the smallest
         * element in the Heap which will always
         * be the 'root' or data[0] element. It
         * then reorders the array in order to 
         * maintain the structure of the binary
         * MinHeap.
         ******************************************/
        int removed = data[0];
        data[0] = data[--size];
        siftdown(0); // down from the root
        return removed;
    }

    private void siftdown(int index) {
        /********************************************
         * This function recursively swaps the 'root'
         * element with one of its children if the
         * value of tyhe child element is less than
         * the parent, this is according to the 
         * structure of a MinHeap.
         ********************************************/
        int child = (2 * index) + 1;
        if (child >= size) {
            return;
        }
        if (child + 1 < size && data[child] > data[child + 1]) {
            ++child;
        }
        if (data[child] < data[index]) {
            swap(index, child);
            siftdown(child);
        }
    }
    
    // double size of the array
    private void grow() {
        data = Arrays.copyOf(data, size * 2);
    }

    // swap two elements in the array
    private void swap(int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    // used for testing purposes
    public String toString() {
        String result = "Size: " + getSize() + "\t[ ";
        for (int i=0; i < size; i++) {
            result+= data[i] + ", ";
        }
        return result+= " ]\n";
    }

    // used for testing purposes
    public int getSize() {
        return this.size;
    }
}