import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Hw2 {

    private static BigDecimal removeRedundant(BigDecimal num) {
        
        String numStr = num.toString();

        int indexOfDot = numStr.indexOf(".");

        if(indexOfDot != -1) {
            String decimalPart = numStr.substring(indexOfDot + 1);
            if(decimalPart.matches("9+")) {
                num = num.setScale(0, RoundingMode.UP);
            } 
            else if (decimalPart.matches("(\\d)\\1+")) {
                num = num.setScale(1, RoundingMode.DOWN);
            } 
            else if (decimalPart.matches("(\\d{2})\\1+")) {
                num = num.setScale(2, RoundingMode.DOWN);
            }
        }

        return num;
    }

    private static BigDecimal lastAlsoCarry(BigDecimal sum, int length) {
        BigDecimal adjustment = BigDecimal.ONE.scaleByPowerOfTen(-length);
        return(sum.add(adjustment));
    }

    private static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private static int findLCM(int a, int b) {
        return(a * b / findGCD(a, b));
    }

    private static void calculate(String num1Str, String num2Str) {
        //split a number into 2 parts
        String[] num1Part = num1Str.split("\\.");
        String[] num2Part = num2Str.split("\\.");

        //find the proper decimal digits for the operands
        int extendLength = findLCM(num1Part[1].length(), num2Part[1].length());

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < (extendLength/num1Part[1].length()); i++) {
            builder.append(num1Part[1]);
        }
        num1Part[1] = builder.toString();

        builder.setLength(0);

        for(int i = 0; i < (extendLength/num2Part[1].length()); i++) {
            builder.append(num2Part[1]);
        }
        num2Part[1] = builder.toString();
        
        num1Part[1] = String.format("0.%s", num1Part[1]);
        num2Part[1] = String.format("0.%s", num2Part[1]);

        BigDecimal d1 = new BigDecimal(num1Part[1]);
        BigDecimal d2 = new BigDecimal(num2Part[1]);
        BigDecimal sumOfD1D2 = d1.add(d2);
        BigDecimal i1 = new BigDecimal(num1Part[0]);
        BigDecimal i2 = new BigDecimal(num2Part[0]);
        BigDecimal sumOfI1I2 = i1.add(i2);

        //check carry
        if(sumOfD1D2.compareTo(BigDecimal.ONE) > 0) {
            sumOfD1D2 = lastAlsoCarry(sumOfD1D2, extendLength);
            sumOfD1D2 = sumOfD1D2.subtract(BigDecimal.ONE);
            sumOfI1I2 = sumOfI1I2.add(BigDecimal.ONE);
        }

        //check redundant
        sumOfD1D2 = removeRedundant(sumOfD1D2);

        //output result
        System.out.println(sumOfD1D2.add(sumOfI1I2));
    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int count = keyboard.nextInt();
        keyboard.nextLine();

        String[] num1Str = new String[count];
        String[] num2Str = new String[count];

        for(int i = 0; i < count; i++) {
            num1Str[i] = new String(keyboard.next());
            num2Str[i] = new String(keyboard.next());
            keyboard.nextLine();
        }

        for(int i = 0; i < count; i++) {
            calculate(num1Str[i], num2Str[i]);
        }

        keyboard.close();
    }
}

