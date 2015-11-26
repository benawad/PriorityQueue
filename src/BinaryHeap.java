public class BinaryHeap implements PriorityQueue{

	double[] heap;
	int size = 0;
	
	public BinaryHeap() {
		// set default size
		heap = new double[10];
		heap[0] = Double.MIN_VALUE;
	}
	
	// get parent of node at position i
	private int parent(int i){
		return i / 2;
	}
	
	// get left child of node at position i
	private int leftChild(int i){
		return i * 2;
	}
	
	// get right child of node at position i
	private int rightChild(int i){
		return (i*2) + 1;
	}
	
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public double findMin() {
		if(!isEmpty()){
			return heap[1];
		} else {
			throw new EmptyPQException();
		}
	}
	
	private void doubleHeap(){
		double[] newHeap = new double[(size+1)*2];
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}
	
	private void swap(int index1, int index2){
		double temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}
	
	@Override
	public void insert(double x) {
		// increase size
		size++;
		// add value to heap
		heap[size] = x;
		// index where the value is
		int currIndex = size;
		// change position of value to meet min-heap property
		while(heap[currIndex] < heap[parent(currIndex)]){
			// if the value is smaller than its parent then swap the values
			swap(currIndex, parent(currIndex));
			// change current index to the position of the parent
			currIndex = parent(currIndex);
		}
		
		// check to see if heap is full
		if(size == heap.length-1){
			doubleHeap();
		}
	}
	
	private boolean hasLeftChild(int i){
		return (leftChild(i) <= size);
	}
	
	private boolean hasRightChild(int i) {
		return (rightChild(i) <=  size);
	}
	
	private boolean isLeaf(int i){
		if (hasLeftChild(i)){
			return false;
		} else {
			return true;
		}
	}
	
	private void minHeapify(int i){
		if (!isLeaf(i)){
			int minIndex;
			if(hasRightChild(i)){
				// check to see whether left or right child is smaller
				if(heap[rightChild(i)] < heap[leftChild(i)]){
					minIndex = rightChild(i);
				} else {
					minIndex = leftChild(i);
				}
			} else {
				// if the node is not a leaf you can assume it has at least a left node
				minIndex = leftChild(i);
			}
			// swap with smallest child
			if(heap[minIndex] < heap[i]){
				swap(minIndex, i);
				minHeapify(minIndex);
			}
		}
	}

	@Override
	public double deleteMin() {
		// make sure the heap is not empty
		if(!isEmpty()){
			double min = heap[1];
			// set the root equal to the last element in the heap
			heap[1] = heap[size];
			// decrease size
			size--;
			// make sure heap follows min-heap property
			if(!isEmpty()){
				minHeapify(1);
			}
			return min;
		} else {
			throw new EmptyPQException();
		}
	}

	@Override
	public void makeEmpty() {
		// reset size and array
		size = 0;
		heap = new double[10];
		heap[0] = Double.MIN_VALUE;
	}

}
