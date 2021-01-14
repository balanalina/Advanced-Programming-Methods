package ADT;

import java.util.Stack;

public interface ImyStack<A> {
    //Pushes an element onto the top of this stack.
    public A push(A element);

    //Removes the object at the top of this stack and returns that object as the value of this function.
    public A pop();

    //Returns the 1-based position where an object is on this stack.
    public int search(A element);

    //Tests if this stack is empty.
    public boolean empty();

    public Stack<A> getStack();

    public String toString();
}
