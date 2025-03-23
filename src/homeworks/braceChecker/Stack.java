package homeworks.braceChecker;

public class Stack {

    private char[] stack;
    private int tos;

    public Stack() {
        stack = new char[10]; // Initial size
        tos = -1;
    }

    public void push(char item) {
        if (tos == stack.length - 1) {
            resize();
        }
        stack[++tos] = item;
    }

    public char pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return '\0';
        }
        return stack[tos--];
    }

    public boolean isEmpty() {
        return tos < 0;
    }

    private void resize() {
        int newSize = stack.length * 2;
        char[] newStack = new char[newSize];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }
}
