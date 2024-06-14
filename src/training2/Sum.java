package training2;

import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask<Double> {
    final int threshold = 500;
    double[] data;
    int start, end;
    public Sum(double[] d, int s, int e){
        data = d;
        start = s;
        end = e;
    }
    @Override
    protected Double compute() {
        System.out.println("current thread - " + Thread.currentThread().getName()
        + " start " + start + ",  end - " + end);
        double sum = 0;
        if((end - start) < threshold){
            for(int i = start; i < end; i++){
                sum += data[i];
            }
        }else{
            int middle =(start + end)/2;
            Sum s1 = new Sum(data, start, middle);
            Sum s2 = new Sum(data, middle, end);
            //запускаем каждую подзадачу путем разветвления
            s1.fork();
            s2.fork();
            //возвратить конечную сумму
            sum = s1.join() + s2.join();
          //  sum = s1.invoke() + s2.invoke();


        }

        return sum;
    }
}
