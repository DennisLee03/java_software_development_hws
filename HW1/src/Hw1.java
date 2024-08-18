import java.math.BigDecimal;
import java.util.Scanner;

public class Hw1{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        BigDecimal bd1 = new BigDecimal(sc.next());
        BigDecimal bd2 = new BigDecimal(sc.next());
        
        sc.close();
        
        System.out.println(bd1.add(bd2));
    }
}