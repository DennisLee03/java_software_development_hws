import java.util.Scanner;

class Point {
    private int horizontal;//x coordinate
    private int vertical;//y coordinate

    public Point() {
        this.horizontal = 0;
        this.vertical = 0;
    }

    public void set(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public void move(int x, int y) {
        this.horizontal += x;
        this.vertical += y;
    }

    public void rotate() {
        int x = this.horizontal;
        int y = this.vertical;
        /*
         * x' = xconA - ysinA
         * y' = xsinA + yconA 
         * con(-90degree) = 0, sin(-90degree) = -1
         */
        this.horizontal = y;
        this.vertical = -x;
    }

    public int retrieveHorizontal() {
        return this.horizontal;
    }

    public int retrieveVertical() {
        return this.vertical;
    }

    public int calculateManhattanDistance(Point other) {
        int distanceX = Math.abs(this.horizontal - other.retrieveHorizontal());
        int distanceY = Math.abs(this.vertical - other.retrieveVertical());
        return(distanceX + distanceY);
    }
    public double ChebyshevDistance(Point other) {
        int distanceX = Math.abs(this.horizontal - other.retrieveHorizontal());
        int distanceY = Math.abs(this.vertical - other.retrieveVertical());
        return(distanceX > distanceY ? (double)distanceX : (double)distanceY);
    }

    public void outputPoint() {
        System.out.println(this.horizontal + " " + this.vertical);
    }
}
public class Hw3 {
    public static void main(String[] args) {
        Point mainPoint = new Point();
        Point otherPoint = new Point();
        Scanner keyboard = new Scanner(System.in);

        mainPoint.set(keyboard.nextInt(), keyboard.nextInt());
        otherPoint.set(keyboard.nextInt(), keyboard.nextInt());
        mainPoint.outputPoint();
        mainPoint.move(keyboard.nextInt(), keyboard.nextInt());
        mainPoint.outputPoint();
        mainPoint.rotate();
        mainPoint.outputPoint();
        mainPoint.rotate();
        mainPoint.outputPoint();
        mainPoint.rotate();
        mainPoint.outputPoint();
        mainPoint.rotate();
        mainPoint.outputPoint();

        System.out.println(mainPoint.calculateManhattanDistance(otherPoint));
        System.out.println(mainPoint.ChebyshevDistance(otherPoint));
        
        keyboard.close();
    }
}
