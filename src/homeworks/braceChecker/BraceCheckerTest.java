package homeworks.braceChecker;

public class BraceCheckerTest {
    public static void main(String[] args) {
        String text = "Hello ({(from] (J}ava";
        BraceChecker braceChecker = new BraceChecker(text);
        braceChecker.check();
    }
}
