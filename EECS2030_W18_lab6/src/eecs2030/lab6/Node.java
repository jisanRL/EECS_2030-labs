package eecs2030.lab6;

/**
 * A simple node class for implementing singly linked sequences. Each node is an
 * aggregation of a data element and another node (the next node in the
 * sequence).
 *
 * @param <E>
 *            the type of the data element stored in this node
 */
public class Node<E> {
	private E element;
	private Node<E> next;

	/**
	 * Create a node holding the given data element and having the given next node
	 * in the sequence.
	 * 
	 * @param element
	 *            the data element to store in the node
	 * @param next
	 *            the next node in the sequence
	 */
	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}

	/**
	 * Get the next node in the sequence. Returns null if there are no more nodes in
	 * the sequence.
	 * 
	 * @return the next node in the sequence
	 */
	public Node<E> getNext() {
		return this.next;
	}

	/**
	 * Set the next node in the sequence. Setting the next node to null indicates
	 * the end of the sequence.
	 * 
	 * @param next
	 *            the next node in the sequence
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/**
	 * Get the data element stored in this node.
	 * 
	 * @return the data element stored in this node
	 */
	public E getElement() {
		return this.element;
	}

	/**
	 * Set the data element stored in this node.
	 * 
	 * @param element
	 *            the data element to store in this node
	 */
	public void setElement(E element) {
		this.element = element;
	}

}
