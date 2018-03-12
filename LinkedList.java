/**
 * css 342
 * andrewbudihardja
 *
 * @param <T>
 */
class LinkedNode<T> implements Comparable<T>{

    LinkedNode<T> next;
    T data;

    public LinkedNode(T data) {
        this(data, null);
    }

    public LinkedNode(T data, LinkedNode<T> next) {
        this.next = next;
        this.data = data;
    }

    public T getElement() {
        return this.data;
    }

    public void setElement(T data) {
        this.data = data;
    }

    public LinkedNode<T> getNext() {
        return this.next;
    }

    public void setNext(LinkedNode<T> nextLinkedNode) {
        this.next = nextLinkedNode;
    }
    
    @SuppressWarnings("unchecked")
	public int compareTo(T arg0) {
		return ((Comparable<T>) data).compareTo(arg0);
	}
}

public class LinkedList<T> {

private LinkedNode<T> head;
private LinkedNode<T> tail;
private int size;

public LinkedList() {
	
    head = null;
    tail = null;
    size = 0;
}

public void insert(T data, int index) {

    if (index > size) {
        throw new IllegalArgumentException("The index [" + index
                + "] is greater than the currentent size [" + size + "].");
    } else {

        LinkedNode<T> temp = new LinkedNode<T>(data);
        LinkedNode<T> current = getLinkedNode(index);

        if (index == 0) {
            temp.setNext(head);
            head = temp;
            tail = head;
        } else {
            temp.setNext(current.getNext());
            current.setNext(temp);
        }

        if ( index == size - 1 ) {
            tail.setNext(temp);
            tail = temp;
        }

    }

    size++;
}

public void append(T data) {
    insert(data, size);
}

public void replace(T data, int index) {
    getLinkedNode(index).setElement(data);
}

public void remove(int index) {

    if (index == 0) {
        head = head.getNext();
    } else {
        getLinkedNode(index).setNext(getLinkedNode(index).getNext().getNext());
    }

    this.size--;
}

public LinkedNode<T> getLinkedNode(int index) {

    if ( index > size ) {
        throw new IllegalArgumentException("The index [" + index + "] is greater than the current size [" + size + "].");
    }
    LinkedNode<T> current = head;
    for (int i = 1; i < index; i++) {
        current = current.getNext();
    }

    return current;
}

public T get(int index) {
    return getLinkedNode(index).getElement();
}

public int size() {
    return this.size;
}


@Override
public String toString() {
    StringBuilder builder = new StringBuilder();

    LinkedNode<T> current = head;
    while( current != null ) {
        builder.append("[" + current.getElement() + "]");
        current = current.getNext();
    }

    return builder.toString();
}

public LinkedNode<T> getHead() {
	return head;
}

public LinkedNode<T> getTail() {
	return tail;
}

public void setHead(LinkedNode<T> next) {
	
	head = next;
	
}


}