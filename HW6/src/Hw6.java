
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hw6 {
    public static boolean check(String target) {
        //there are 8 solutions that X wins
        String[] solutions = {"X{3}[#O]{6}", "[#O]{3}X{3}[#O]{3}", "[#O]{6}X{3}", 
                              "(X[#O]{2}){3}", "([#O]X[#O]){3}", "([#O]{2}X){3}",
                              "X[#O]{3}X[#O]{3}X", "[#O]{2}X[#O]X[#O]X[#O]{2}"};
        Pattern solPattern[] = new Pattern[8];
        Matcher solMatcher[] = new Matcher[8];

        for(int i = 0; i < 8; i++) {
            solPattern[i] = Pattern.compile(solutions[i]);
            solMatcher[i] = solPattern[i].matcher(target);
            if(solMatcher[i].find()) {
                return true;//X wins
            }
        }

        return false;
    }

    public static int diff(String target) {
        int countO = 0;
        int countX = 0;
        for (char c : target.toCharArray()) {
            if (c == 'O') {
                countO++;
            } else if (c == 'X') {
                countX++;
            }
        }

        int result = countX - countO;

        return result;
    }
    public static void validate(String target) {
        String incMovOrd = "^[O#]+$";
        Pattern pIncMovOrd = Pattern.compile(incMovOrd);
        Matcher mIncMovOrd = pIncMovOrd.matcher(target);
        
        //It is incorrect that # O is greater than # X
        int numX_minus_numO = diff(target);

        if(mIncMovOrd.find() || (numX_minus_numO < 0)) {
            System.out.println("invalid");
            return;
        }
        else if(numX_minus_numO > 1) {
            System.out.println("invalid");
            return;
        }
        else if(numX_minus_numO == 1) {
            System.out.println("valid");
            return;
        }
        else if(numX_minus_numO == 0 && check(target)) {
            System.out.println("invalid");
            return;
        } 
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String target = keyboard.next();//target = "#XO###XOX"
        keyboard.nextLine();
        validate(target);
        keyboard.close();
    }
}