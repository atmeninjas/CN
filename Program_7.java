import java.util.*;

public class RSA {

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the message to be encrypted:");
        String message = sc.nextLine();

        System.out.println("Enter values of p and q:");
        int p = sc.nextInt();
        int q = sc.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int e = 0, d = 0;

        for (int i = 2; i < phi; i++) {
            if (gcd(i, phi) == 1) {
                e = i;
                break;
            }
        }

        for (int i = 2; i < phi; i++) {
            if ((e * i) % phi == 1) {
                d = i;
                break;
            }
        }

        int[] nummes = new int[message.length()];
        int[] encrypted = new int[message.length()];
        int[] decrypted = new int[message.length()];

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            nummes[i] = c - 'a' + 1;
        }

        // Encryption
        for (int i = 0; i < message.length(); i++) {
            encrypted[i] = 1;
            for (int j = 0; j < e; j++) {
                encrypted[i] = (encrypted[i] * nummes[i]) % n;
            }
        }

        System.out.println("\nEncrypted message:");
        for (int i = 0; i < message.length(); i++) {
            System.out.print(encrypted[i] + " ");
        }

        // Decryption
        for (int i = 0; i < message.length(); i++) {
            decrypted[i] = 1;
            for (int j = 0; j < d; j++) {
                decrypted[i] = (decrypted[i] * encrypted[i]) % n;
            }
        }

        System.out.println("\nDecrypted message:");
        for (int i = 0; i < message.length(); i++) {
            System.out.print((char) (decrypted[i] + 'a' - 1));
        }
        sc.close();
    }
}
