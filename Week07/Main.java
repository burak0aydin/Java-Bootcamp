import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>(10);
        stack.push("John");
        System.out.println(stack);
        stack.push("Jack");
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.push("Edison");
        System.out.println(stack);
        //stack.pop(); stack.pop();

        Queue<Integer> queue = new Queue<Integer>(10);
        queue.queue(1);
        System.out.println(queue);
        queue.queue(5);
        System.out.println(queue);
        queue.queue(7);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.queue(10);
        System.out.println(queue);
        queue.dequeue(); queue.dequeue();
        System.out.println(queue);

        clear(stack);
        System.out.println(stack);
        clear(queue);
        System.out.println(queue);

        ArrayList<String> strList = new ArrayList<String>();
        strList.add("abc"); strList.add("def"); strList.add("def");

        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(1); intList.add(2); intList.add(3); intList.add(4); intList.add(5); intList.add(1);

        printList(removeDuplicates(strList));
        printList(removeDuplicates(intList));

    }

    public static <E extends Structure<?>> void clear(E structure) {

        int c = 0;
        while (!structure.isEmpty()) {
        
            structure.array[c++] = null;
        }
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();
        for (E e : list) {
            if (!result.contains(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public static <E> void printList(ArrayList<E> list) {
        for (E e : list) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}

interface IStructure {
    boolean isEmpty();
    int count();
    int capacity();
    String toString();
}

abstract class Structure<T> implements IStructure{
    protected T[] array;

    Structure(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public int count() {
        int c = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                c += 1;
            }
        }

        return c;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < capacity(); i++) {
            if (array[i] != null) {
                return false;
            }
        }

        return true;
    }
}

class Stack<T> extends Structure<T> {
    
    Stack(int capacity) {
        super(capacity);
    }

    public void push(T element) {
        if (count() == capacity()) {
            throw new RuntimeException("Stack is full");
        }

        array[count()] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        T elem = array[count() - 1];
        array[count() - 1] = null;

        return elem;
    }

    public String toString() {
        String m = "";
        for (T t : array) {
            if (t != null) 
                m += t.toString() + " ";
        }

        return m;
    }
}

class Queue<T> extends Structure<T> {
    
    Queue(int capacity) {
        super(capacity);
    }

    public void queue(T element) {
        if (count() == capacity()) {
            throw new RuntimeException("Queue is full");
        }

        array[count()] = element;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        T elem = array[0];
        
        int c = 1;
        while (c < count() && count() > 0) {
            array[c - 1] = array[c];

            c++;
        }
        array[count() - 1] = null;

        return elem;
    }

    public String toString() {
        String m = "";
        for (T t : array) {
            if (t != null) 
                m += t.toString() + " ";
        }

        return m;
    }
}