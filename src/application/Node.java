package application;

public class Node<T extends Comparable<T>> {
       //declare the data and the next node
	private T data;
	private Node<T> next;
	
	
	
	public Node(T data) {  //constructor
		
		this.data = data;
	}
	
	public String toString() {  //toString 
		return data.toString();
	}
    //getters and setters
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}

