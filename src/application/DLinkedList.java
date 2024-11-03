package application;

public class DLinkedList<T extends Comparable<T>> {

	private DNode<T> head = new DNode(null); // give a DNode to head because it is Dummy DoubleLinkedList

	public void insert(T data) { // insert data   //time O(n)
		DNode<T> newNode = new DNode(data);
		if (newNode.getData() != null) {
			DNode<T> curr = head.getNext();
			DNode<T> prev = head;

			for (; curr != null && newNode.getData().compareTo(curr.getData()) > 0; prev = curr, curr = curr.getNext())
				;

			if (curr == null) { // last
				newNode.setNext(null);
				newNode.setPrev(prev);
				prev.setNext(newNode);
			}
			// first and between
			else {
				newNode.setNext(curr);
				newNode.setPrev(prev);
				curr.getPrev().setNext(newNode);
				curr.setPrev(newNode);
			}
		} else {
			System.out.println("is null");
		}
	}

	public boolean delete(T data) { // delete data    //time O(n)
		DNode<T> newNode = new DNode<>(data);
		DNode<T> curr = head.getNext();
		DNode<T> prev = head;
		for (; curr != null && newNode.getData().compareTo(curr.getData()) > 0; prev = curr, curr = curr.getNext())
			;
		;
		if (curr == null) { // not in list
			return false;
		} else if (newNode.getData().compareTo(curr.getData()) == 0) {
			curr.getPrev().setNext(curr.getNext());
			if (curr.getNext() != null)
				curr.getNext().setPrev(curr.getPrev());
			
			return true;
		}
		return false;
	}
 
	public T find(T data) { // find data   //time O(n)
		DNode<T> newNode = new DNode<>(data);
		DNode<T> curr = head.getNext();

		for (; curr != null && newNode.getData().compareTo(curr.getData()) > 0; curr = curr.getNext())
			;
		if (curr == null) { // not in list
			return null;
		} else if (newNode.getData().compareTo(curr.getData()) == 0) {
			return curr.getData();
		} else {
			return null;
		}

	}

	public int length() { // number of data in list without the dummy    //time O(n)
		int count = 0;
		DNode<T> curr = head.getNext();
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}

	public String toString() { // toSring for list    //time O(n)
		String s = "";
		s += "head --> ";
		DNode<T> curr = head.getNext();
		while (curr != null) {
			s += curr.toString() + " --> ";
			curr = curr.getNext();
		}
		s += "Null";
		return s;
	}

	// getters
	public DNode<T> getHead() {
		return head;
	}

	public void setHead(DNode<T> head) {
		this.head = head;
	}

}
