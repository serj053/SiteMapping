package training2;

import java.util.concurrent.ForkJoinPool;

public class SumDemo {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        double[] nums = new double[5000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) (((i % 2) == 0) ? i : -i);
        }
        //выводим первые 10 значенией
        for(int i = 0; i < 10; i++){
            System.out.print(nums[i]+" ");
        }
        //создаем задачи
        Sum sum = new Sum(nums, 0, nums.length);
        //возвращаем результат
        double summation = pool.invoke(sum);
        System.out.println("\nsum - " + summation);
    }
}
