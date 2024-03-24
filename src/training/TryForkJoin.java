package training;

import java.util.concurrent.RecursiveAction;

public class TryForkJoin extends RecursiveAction {
    double[] values;
    int start, end;
    int threshold = 1000;

    public TryForkJoin(double[] d, int s, int e) {
        this.values = d;
        this.start = s;
        this.end = e;
    }

    @Override
    protected void compute() {
        if ((start - end)< threshold) {
            for (int i = 0; i < values.length; i++) {
                values[i] = Math.sqrt(i);
            }
        } else {
            int middle = (start-end)/2;
            TryForkJoin t1 = new TryForkJoin(values, start, middle);
            TryForkJoin t2 = new TryForkJoin(values, middle, end);
            invokeAll(t1, t2);
        }
    }
}
