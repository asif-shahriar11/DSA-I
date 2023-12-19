class Node <T> {
    private T element;
    private Node<T> next;

    Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() { return element; }
    public void setElement(T element) { this.element = element; }
    public Node<T> getNext() { return next; }
    public void setNext(Node<T> next) { this.next = next; }
}

public class Stack<T> {
    private Node<T> first;

    Stack() {
        first = null;
    }

    public boolean isEmpty() {return first==null;}

    public void push(T item) {
        Node<T> second = first;
        first = new Node<>(item,second);
    }

    public T pop() {
        if(isEmpty()) { return null; }
        T item = first.getElement();
        first = first.getNext();
        return item;
    }

    public T peek() {
        return first.getElement();
    }
}
