import java.util.Scanner;

// idea:
// the head node refers to the player who is in possession of the pillow
// to move the pillow, we just move the head right or left

class Player {
    private int position;
    private int reflexTime;
    private int remainingTime; // time remaining on possession of the pillow

    Player(int position, int reflexTime) {
        this.position = position;
        this.reflexTime = reflexTime;
        this.remainingTime = reflexTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getPosition() {
        return position;
    }

    public int getReflexTime() {
        return reflexTime;
    }
}

class Node <T> {
    private T element;
    private Node<T> previous;
    private Node<T> next;

    Node(T element, Node<T> previous, Node<T> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    public T getElement() { return element; }
    public void setElement(T element) { this.element = element; }
    public Node<T> getPrevious() { return previous; }
    public void setPrevious(Node<T> previous) { this.previous = previous; }
    public Node<T> getNext() { return next; }
    public void setNext(Node<T> next) { this.next = next; }
}

class CircularDoublyLinkedList <T> {
    private Node<T> head;
    private int size;

    CircularDoublyLinkedList() {
        head = new Node<>(null, null, null);
        size = 0;
    }

    public int getSize()    { return size; }

    public boolean isEmpty() { return size==0; }

    public T getFirst() {
        if(isEmpty())   return null;
        return head.getElement();
    }

    public void addLeft(T newElement) {
        if(isEmpty())   {
            head.setElement(newElement);
            head.setPrevious(head);
            head.setNext(head); // linked to itself
        } else {
            Node<T> newNode = new Node<>(newElement, head.getPrevious(), head);
            head.getPrevious().setNext(newNode);
            head.setPrevious(newNode);
        }
        size++;
    }

    public void addRight(T newElement) {
        if(isEmpty())   addLeft(newElement);
        else {
            Node<T> newNode = new Node<>(newElement, head, head.getNext());
            head.getNext().setPrevious(newNode);
            head.setNext(newNode);
            size++;
        }
    }

    public void rotate(boolean isCounterClockwise) {
        if(!isEmpty()) {
            if(isCounterClockwise)  head = head.getNext();  // direction of rotation
            else head = head.getPrevious();
        }
    }

    public void remove(boolean isCounterClockwise) {
        head.getPrevious().setNext(head.getNext());
        head.getNext().setPrevious(head.getPrevious());
        if(isCounterClockwise)  head = head.getNext();
        else head = head.getPrevious();
        size--;
    }

    public void print(boolean isCounterClockwise) {
        // removing elements from the list because they are no longer needed in the program
        while (!isEmpty()) {
            Player p = (Player) head.getElement();
            System.out.print(p.getPosition());
            remove(isCounterClockwise);
            if(!isEmpty()) System.out.print(", ");
        }
    }
}

public class Main {

    public static void main(String[] args) {

        CircularDoublyLinkedList<Player> list = new CircularDoublyLinkedList<>();

        Scanner input = new Scanner(System.in);

        int n;
        System.out.println("Enter the number of initial players: ");
        n = input.nextInt();
        input.nextLine();
        System.out.println("Enter the reflex time of these players: ");
        for(int i=0; i<n; i++) {
            int refTime = input.nextInt();
            Player player = new Player(i+1,refTime);
            list.addLeft(player);
        }

        boolean isRunning = true;
        boolean isCounterClockwise = true;  // initially game rotating counter clockwise
        int time = 1;   // initial time
        boolean isMusicOn = false;

        while(isRunning) {
            System.out.println("Enter command: ");
            int inputTime = input.nextInt();
            String command = input.next();

            Player p;   // player in possession

            // moving the pillow
            // no need to move if only one player left i.e. game is already decided
            if(list.getSize()!=1) {
                for(int i=time; i<inputTime; i++) {
                    // pillow won't move if the music is on
                    if(!isMusicOn) {
                        p = list.getFirst();
                        p.setRemainingTime(p.getRemainingTime()-1);
                        if(p.getRemainingTime()==0) {
                            p.setRemainingTime(p.getReflexTime());
                            list.rotate(isCounterClockwise);
                        }
                    } else {
                        // music is on
                        // let this second pass
                        isMusicOn = false;
                    }
                }
            }

            // processing command
            if(command.equalsIgnoreCase("P")) {
                System.out.println("Player "+ list.getFirst().getPosition()+" is holding the pillow at t="+inputTime);
            }

            else if(command.equalsIgnoreCase("M")) {
                if(list.getSize()!=1) {
                    p = list.getFirst();
                    list.remove(isCounterClockwise);
                    System.out.println("Player "+p.getPosition()+" has been eliminated at t="+inputTime);
                    isMusicOn = true;
                }
            }

            else if(command.equalsIgnoreCase("R")) {
                isCounterClockwise = !isCounterClockwise; // reversing the direction of rotation
            }

            else if(command.equalsIgnoreCase("I")) {
                int inputReflexTime = input.nextInt();
                if(list.getSize()!=1) {
                    Player newPlayer = new Player(++n,inputReflexTime);
                    if(isCounterClockwise)  list.addLeft(newPlayer);
                    else    list.addRight(newPlayer);
                }
            }

            else if(command.equalsIgnoreCase("F")) {
                if(list.getSize()==1) {
                    System.out.println("Game over: Player "+list.getFirst().getPosition()+" wins!!");
                } else {
                    System.out.print("Game over: Player "+list.getFirst().getPosition()+" is holding the pillow ");
                    System.out.print("at t="+inputTime+", pillow passing sequence = Player ");
                    list.print(isCounterClockwise);
                }
                isRunning = false;
            }
            time = inputTime;
        }

        input.close();

    }
}
