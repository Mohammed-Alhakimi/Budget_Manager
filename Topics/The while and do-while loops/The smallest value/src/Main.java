import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        long n = 0;
        while (factorial(n) <= number) {
            n++;
        }
        System.out.println(n);
    }

    public static long factorial(long number) {
        if (number < 2) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}