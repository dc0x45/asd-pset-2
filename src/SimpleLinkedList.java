public class SimpleLinkedList {
    	
	private Node head;
	private Node tail;
	private int size;

	public SimpleLinkedList() {
	    this.head = null;
	    this.tail = null;
	    this.size = 0;
	}
	
	public void add(int index, String s) {
	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	    } else if (index == 0) {
	        addFirst(s);
	    } else if (index == size) {
	        addLast(s);
	    } else {
	        Node current = getNode(index);
	        Node newNode = new Node(current.prev, s, current);
	        
	        size++;
	        current.prev.next = newNode;
	        current.prev = newNode;
	    }
	}
	
	public void addFirst(String s) {
	    Node current = head;
	    Node newNode = new Node(null, s, current);
	    
	    head = newNode;
	    if (current == null) {
	        tail = newNode;
	    } else {
	        current.prev = newNode;
	    }	    
	    
	    size++;
	}
	
	public void addLast(String s) {
	    Node current = tail;
	    Node newNode = new Node(current, s, null);
	    
	    tail = newNode;
	    if (current == null) {
	        head = newNode;
	    } else {
	        current.next = newNode;
	    }
	    
	    size++;
	}
	
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        int count = 0;
        Node current = head;
        
        while (current != null) {
            if (count++ == index) {
                return current;
            }
            
            current = current.next;
        }
        
        return null;
    }
	
	private static class Node {
	    
	    Node prev;
	    String data;
	    Node next;
		
		public Node(Node prev, String data, Node next) {
			this.prev = prev;
	        this.data = data;
			this.next = next;
		}
	}
}
