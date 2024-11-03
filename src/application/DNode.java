package application;

public class DNode<T extends Comparable<T>> {
	// declare data and previous and next
	private T data;
	private DNode<T> prev;
	private DNode<T> next;

	public DNode(T data) { // constructor
		this.data = data;
	}

	// getters and setters
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}

	public DNode<T> getNext() {
		return next;
	}

	public void setNext(DNode<T> next) {
		this.next = next;
	}

	public String toString() {
		return data.toString();
	}

}
