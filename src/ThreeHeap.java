public class ThreeHeap implements PriorityQueue{
	
	double[] heap;
	int size;
	
	public ThreeHeap() {
		heap = new double[10];
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public double findMin() {
		if(isEmpty()){
			throw new EmptyPQException();
		} else {
			return heap[0];
		}
	}
	
	private void swap(int index1, int index2){
		double temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}
	
	// get the parent of i
	private int parent(int i){
		return (i-1)/3;
	}
	
	private void minHeapifyUp(int i){
		int currIndex = i;
		while(currIndex > 0 && heap[currIndex] < heap[parent(currIndex)]){
			swap(currIndex, parent(currIndex));
			currIndex = parent(currIndex);
		}
	}
	
	private void doubleHeap(){
		double[] newHeap = new double[size*2];
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}

	@Override
	public void insert(double x) {
		// add double to heap
		heap[size] = x;
		// fix heap
		minHeapifyUp(size);
		size++;
		// check to make sure heap is not full
		if(size == heap.length){
			// if it is full double the size
			doubleHeap();
		}
	}
	
	private void minHeapifyDown(int i){
		if(!isLeaf(i)){
			int smallest = smallestChild(i);
			if(heap[smallest] < heap[i]){
				swap(smallest, i);
				minHeapifyDown(smallest);
			}
		}
	}
	
	// loc of left child
	private int leftChild(int i){
		return i*3+1;
	}
	
	// loc of middle child
	private int middleChild(int i){
		return i*3+2;
	}
	
	// loc of right child
	private int rightChild(int i){
		return i*3+3;
	}
	
	// check if node is in tree
	private boolean exists(int i){
		return (i < size);
	}
	
	private boolean isLeaf(int i){
		if(exists(leftChild(i))){
			return false;
		} else {
			return true;
		}
	}
	
	// finds the location of the smallest child of i
	private int smallestChild(int i){
		int smallest = leftChild(i);
		if(exists(middleChild(i))){
			if(exists(rightChild(i))){
				if(heap[rightChild(i)] < heap[middleChild(i)]){
					if(heap[rightChild(i)] < heap[leftChild(i)]){
						smallest = rightChild(i);
					}
				} else {
					if(heap[middleChild(i)] < heap[leftChild(i)]){
						smallest = middleChild(i);
					}
				}
			} else {
				if(heap[middleChild(i)] < heap[leftChild(i)]){
					smallest = middleChild(i);
				}
			}
		}
		return smallest;
	}
	
	@Override
	public double deleteMin() {
		if(!isEmpty()){
			double min = heap[0];
			// make last node the root
			heap[0] = heap[size-1];
			// decrease size
			size--;
			if(size > 1){
				// fix heap 
				minHeapifyDown(0);
			}
			return min;
		} else {
			throw new EmptyPQException();
		}
	}

	@Override
	public void makeEmpty() {
		heap = new double[10];
		size = 0;
	}
	
}