import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
/**
 * @author Nanda
 *
 */
public class Combination_parallel {
   public static void main(String[] args) {
	  Scanner sc=new Scanner(System.in);
	  int n=sc.nextInt();
	  int r=sc.nextInt();
	  BigInteger processors=new BigInteger(sc.next());
	  long startTime = System.nanoTime(); 
      ForkJoinPool pool = new ForkJoinPool();
      CustomRecursiveAction task = new
         CustomRecursiveAction(n,r,BigInteger.ONE,BigInteger.ZERO,Find_nCr.get_nCr(n, r),processors);
      pool.invoke(task);
      long endTime   = System.nanoTime();
      long totalTime = endTime - startTime;
      System.out.println(totalTime);
   }
}