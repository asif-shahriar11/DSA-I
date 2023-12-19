public class Queue <T> {
    private Node<T> first;
    private Node<T> last;

    Queue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {return first == null;}

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item, null);
        if(isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
    }

    public T dequeue() {
        T item = first.getElement();
        first = first.getNext();
        return item;
    }

    public T front() { return first.getElement(); }

    public T rear() { return last.getElement(); }
}
