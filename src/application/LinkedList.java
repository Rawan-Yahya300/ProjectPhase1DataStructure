package application;

public class LinkedList<T extends Comparable<T>> {

	private Node<T> head = new Node(null);  //give a node to head because it is dummy linkedList

	public void insert(T data) {   //insert data   //time O(n)
		Node<T> newNode = new Node<>(data);
		if (newNode.getData() != null) {
			Node<T> prev = head;
			Node<T> curr = head.getNext();

			for (; curr != null && newNode.getData().compareTo(curr.getData()) > 0; prev = curr, curr = curr.getNext())
				;

			newNode.setNext(curr);
			prev.setNext(newNode);
		}else {
			System.out.println("is null");
		}
	}

	public boolean delete(T data) {  //delete data  //time O(n)

		Node<T> newNode = new Node<>(data);
		Node<T> prev = head, curr = head.getNext();
		for (; curr != null && newNode.getData().compareTo(curr.getData()) > 0; prev = curr, curr = curr.getNext())
			;
		if (curr == null) { //the list empty or data not in list
			return false;
		} else if (curr.getData().compareTo(newNode.getData()) == 0) {
			prev.setNext(curr.getNext());
			return true;
		}
		return false;
	}

	 
	public String toString() {  //toString for list  //time O(n)
		String s = "";

		Node<T> curr = head.getNext();
		while (curr != null) {
			s += curr + " --> ";
			curr = curr.getNext();
		}
		s += " Null";
		return s;

	}

	public T find(T data) {  //find data   //time O(n)

		Node<T> newNode = new Node<>(data);   
		Node<T> prev = head, curr = head.getNext();
		for (; curr != null && newNode.getData().compareTo(curr.getData()) > 0; prev = curr, curr = curr.getNext())
			;
		if (curr == null) { //list empty or data not in list
			return null;
		}

		else if (curr.getData().compareTo(newNode.getData()) == 0) {
			return curr.getData();
		}

		return null;

	}

	public int length() {  //length of the list without dummy   //time O(n)
		Node<T> curr = head.getNext();
		int count = 0;
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;
	}
       //getter and setter for head
	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

}
