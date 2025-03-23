package classworks.stack;

public class Stack {

    int[] stack = new int[10];
    int tos;

    Stack() {
        tos = -1;
    }

    void push(int item) {
        if (tos == stack.length - 1) {
            System.out.println("Stack is full");
        } else {
            stack[++tos] = item;
        }
    }

    int pop() {
        if (tos < 0) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return stack[tos--];
        }
    }
}
