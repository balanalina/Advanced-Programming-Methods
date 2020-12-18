package ADT;

import java.util.Stack;

public class myStack<A> implements ImyStack<A> {
    private Stack<A> stack;

    public myStack(){
        this.stack = new Stack<>();
    }

    @Override
    public A push(A element) {
        return this.stack.push(element);
    }

    @Override
    public A pop() {
        return stack.pop();
    }

    @Override
    public int search(A element) {
        return stack.search(element);
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
