package training;

import java.util.concurrent.ForkJoinPool;

public class Test {
    public static void main(String[] args) {
        ForkJoinPool p = new ForkJoinPool();
        double[] values = new double[100000];
        for (int i = 0; i < values.length; i++) {
            values[i] = (double) i;
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(values[i]+" ");
        }
        System.out.println();
        TryForkJoin t = new TryForkJoin(values, 0, 100000);
        p.invoke(t);
        for (int i = 0; i < 1000; i++){
            System.out.printf("%.2f ",values[i]);
        }

    }
}
