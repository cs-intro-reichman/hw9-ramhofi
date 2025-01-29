/**
 * Represents a list of Nodes.
 */
public class LinkedList {

	private Node first; // pointer to the first element of this list
	private Node last; // pointer to the last element of this list
	private int size; // number of elements in this list

	/**
	 * Constructs a new list.
	 */
	public LinkedList() {
		first = null;
		last = first;
		size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public Node getFirst() {
		if (first == null) {
			return null;
		}
		return this.first;
	}

	public Node getLast() {
		if (last == null) {
			return null;
		}
		return this.last;
	}

	/**
	 * Gets the node located at the given index in this list.
	 * 
	 * @param index
	 *              the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *                                  if index is negative or greater than the
	 *                                  list's size
	 * @return the node at the given index
	 */
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = this.first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current;
	}

	/**
	 * Creates a new Node object that points to the given memory block,
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last
	 * node in this list.
	 * <p>
	 * The method implementation is optimized, as follows: if the given
	 * index is either 0 or the list's size, the addition time is O(1).
	 * 
	 * @param block
	 *              the memory block to be inserted into the list
	 * @param index
	 *              the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *                                  if index is negative or greater than the
	 *                                  list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node newnNode = new Node(block);
		if (index == 0) {
			addFirst(block);

		} else if (index == size) {
			addLast(block);

		} else {
			Node perv = getNode(index - 1);
			newnNode.next = perv.next;
			perv.next = newnNode;
			size++;
		}

	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *              the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newN = new Node(block);
		if (first == null) {
			this.first = newN;
			this.last = newN;
		} else {
			last.next = newN;
			last = newN;
		}
		size++;

	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the beginning of this list (the node will become the list's first
	 * element).
	 * 
	 * @param block
	 *              the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		if (block == null) {
			throw new IllegalArgumentException("MemoryBlock cannot be null");
		}

		Node newnNode = new Node(block);
		if (first == null) {
			newnNode.next = this.first;
			first = newnNode;
			last = newnNode;
		} else {
			newnNode.next = first;
			first = newnNode;
		}

		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *              the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *                                  if index is negative or greater than or
	 *                                  equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		return getNode(index).getMemoryBlock();
	}

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *              the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		/// 1. count 2. run over the list 
		int index = 0;
		Node temp = first;
		while (temp != null) {
			if (temp.getMemoryBlock() == block) {
				return index;
			}
			index++;
			temp = temp.next;
		}

		return -1;
	}

	/**
	 * Removes the given node from this list.
	 * 
	 * @param node
	 *             the node that will be removed from this list
	 */
	public void remove(Node node) {
		//// Write your code here
		int index = indexOf(node.getMemoryBlock());
		remove(index);
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *                                  if index is negative or greater than or
	 *                                  equal to size
	 */
	public void remove(int index) {
		//// Write your code here
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (index == 0) {
			first = first.next;
			if (size == 1) {
				last = null;
			}
		} else {
			Node prev = getNode(index - 1);
			Node toRemove = prev.next;
			prev.next = toRemove.next;
			if (toRemove == last) {
				last = prev;
			}
		}
		size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *                                  if the given memory block is not in this
	 *                                  list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
		if (block == null) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		int index = indexOf(block);
		if (index == -1) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");

		}
		remove(index);
	}

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator() {
		return new ListIterator(first);
	}

	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {

		String result = "";
		Node temp = first;
		while (temp != null) {
			result += temp.block + " ";
			temp = temp.next;
		}
		return result;
	}
}