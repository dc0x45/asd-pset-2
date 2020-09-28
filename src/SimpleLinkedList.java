import java.util.List;
import java.util.NoSuchElementException;

public class SimpleLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public SimpleLinkedList(List<String> list){
        String[] s;
        if (list != null) {
            s = list.toArray(new String[list.size()]);
            for(int i = 0; i < s.length; i++){
                addLast(s[i]);
            }
        } else {
            throw new NullPointerException();
        }
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

    public int size(){
        return this.size;
    }

    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean contains(String s){
        boolean returnMe = false;
        Node track = head; 
        for (int i = 0; i < size; i++){
            if (track.data == s) {
                returnMe = true;
            } else {
                track = track.next;
            }
        }
        return returnMe;
    }

    public String get(int index){
        String s = "Index: " + index + ", Size: " + size();
        if (index >= 0 && index < size){
            return getNode(index).data;
        } else throw new IndexOutOfBoundsException(s);
    }

    public String getFirst(){
        if (head != null){
            return head.data;
        } else throw new NoSuchElementException();
    }

    public String getLast(){
        if (tail != null){
            return tail.data;
        } else throw new NoSuchElementException();
    }
    public int indexOf(String s){
        int returnMe = -1;
        Node track = head; 
        int count = 0;
        for (int i = 0; i < size; i++){
            if (track.data == s) {
                returnMe = count;
            } else {
                if (track.next != null){
                    track = track.next;
                    count++;
                }
                
            }
        }
        return returnMe;
    }

    public String remove(int index){
        String returnMe = "";
        if (index >= 0 && index < size){
            if (index == 0){
                returnMe = removeFirst();
            } else if (index == size){
                returnMe = removeLast();
            } else {
                Node track = getNode(index);
                returnMe = track.data;
                track.data = null;
                track.prev.next = track.next;
                track.next.prev = track.prev;
                size--;
            }
        } else {
            String s = "Index: " + index + ", Size: " + size();
            throw new IndexOutOfBoundsException(s);
        }
        return returnMe;
    }

    public boolean remove(String s){
        boolean returnMe = false;
        String rStr = "";
        if (s != null){
            int index = indexOf(s);
            if (index != -1){
                rStr = remove(index);
            }
            if (rStr != ""){
                returnMe = true;
            }
        }
        return returnMe;
    }

    public String removeFirst(){
        String returnMe = "";
        returnMe = head.data;
        if (head.next != null){
            head = head.next;
            head.prev = null;
            size--;
        } else if (tail == head) {
            head = null;
            tail = null;
            size = 0;
        }
        return returnMe;
    }

    public String removeLast(){
        String returnMe = "";
        returnMe = tail.data;
        if (tail.prev != null){
            tail = tail.prev;
            tail.next = null;
            size--;
        } else if(tail == head) {
            head = null;
            tail = null;
            size = 0;
        }
        return returnMe;
    }

    public String set(int index, String s){
        Node track = getNode(index);
        Node newNode = new Node(track.prev, s, track.next);
        if (index >= 0 && index < size){
            track.prev.next = newNode;
            track.prev = newNode;
        } else throw new IndexOutOfBoundsException();
        return track.data;
    }

    public String toString(){
        String finalString = "";
        Node track = head;
        for (int i = 0; i < size; i++){
            if (i == 0){
                finalString = track.data;
            } else{
                finalString = finalString + ", " + track.data;
            }
            track = track.next;

        }
        return "[" + finalString + "]";
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
