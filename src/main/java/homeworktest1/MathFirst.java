package homeworktest1;

public class MathFirst {

    public int mathPlus(int a, int b){
        return a + b;
    }
    public int mathMinus(int a, int b){
        return a - b;
    }
    public int mathMultiply(int a, int b){
        return a * b;
    }
    public double mathSplit(double a, double b){

        return a / b;
    }


    public boolean compare(int[] a, int[] b) {
        if (a.length != b.length)
            return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }

        return true;
    }

    public String strN(){
        return null;
    }
}
