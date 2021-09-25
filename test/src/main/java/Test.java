public class Test {
    public static void print(Object o) {
        System.out.println("Print object");
    }

    public static void print(String s) {
        System.out.println("Print string");
    }
    public static void main(String[] args) {
        print(null);
    }
}
