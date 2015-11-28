public class MyPQ implements PriorityQueue {

	Node head;
	int size = 0;

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
		if (!isEmpty()) {
			return head.e;
		} else {
			throw new EmptyPQException();
		}
	}
	
	@Override
	public void insert(double x) {
		size++;
		// make new node to be inserted
		Node newNode = new Node(x);
		// make sure the list is not empty
		if(head == null){
			head = newNode;
			return;
		}

		// check to see if the new value is less than the head
		if(x <= head.e){
			newNode.next = head;
			head = newNode;
			return;
		}
		
		Node currentNode = head;
		// find the node you should insert x before
		while(currentNode.next != null && x > currentNode.next.e){
			currentNode = currentNode.next;
		}
		if(currentNode.next == null){
			currentNode.next = newNode;
		} else {
			newNode.next = currentNode.next;
			currentNode.next = newNode;
		}
	}

	@Override
	public double deleteMin() {
		if (!isEmpty()) {
			double min = head.e;
			head = head.next;
			size--;
			return min;
		} else {
			throw new EmptyPQException();
		}
	}

	@Override
	public void makeEmpty() {
		head = null;
		size = 0;
	}

	private static class Node {
		double e;
		Node next;

		public Node(double e) {
			this.e = e;
		}
	}

}
