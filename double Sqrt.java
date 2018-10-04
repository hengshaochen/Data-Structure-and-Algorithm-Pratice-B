// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {

        System.out.println(Math.sqrt(5));
        System.out.println(mySqrt(5));
    }

    public double mySqrt(double target) {
        // 把這個問題轉成binary search, 要在1~x中找到一個數字, 該數字^2 會> x的
        double eps = 0.00001;
        double start = 1, end = target;
        // 如果是介於0到1
        if (target > 0 && target < 1) {
            start = target;
            end = 1;
        }

        while (Math.abs(start - end) > eps) {
            double mid = start + (end - start) / 2;
            double cur = mid * mid ;
            if (cur > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return start;
    }
}