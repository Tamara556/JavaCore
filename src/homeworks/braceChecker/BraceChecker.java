package homeworks.braceChecker;

public class BraceChecker {

    private String text;

    public BraceChecker(String text) {
        this.text = text;
    }

    public void check() {
        Stack myStack = new Stack();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char last;
            int pop;

            switch (c) {
                case '{':
                case '[':
                case '(':
                    myStack.push(c);
                    break;

                case '}':
                case ']':
                case ')':
                    pop = myStack.pop();
                    if (pop == 0) {
                        System.out.println("Error: Closed '" + c + "' but not opened at index " + i);
                    } else {
                        last = (char) pop;
                        if (!matches(last, c)) {
                            System.out.println("Error: Closed '" + c + "' but opened '" + last + "' at index " + i);
                        }
                    }
                    break;
            }
        }

        while (!myStack.isEmpty()) {
            System.out.println("Error: Opened '" + (char) myStack.pop() + "' but not closed.");
        }
    }

    private boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}
