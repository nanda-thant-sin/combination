import java.math.BigInteger;
import java.util.Scanner;

/**
 * Algorithm to return total number of combinations out of n choose r
 * Time complexity is O(r)
 * @author Nanda
 *
 */
public class Find_nCr {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		BigInteger result = get_nCr(n, r);
		System.out.printf("Result of C(%d, %d) = %d", n, r, result);
		sc.close();
	}
	/**
	 * function to return total number of combinations out of n choose r
	 * @param n
	 * @param r
	 * @return result 
	 */
	public static BigInteger get_nCr(int n,int r) {
		if (n < r) {// doesn't make sense
			return BigInteger.ZERO;
		}
		if (n - r < r) { // choose smaller one
			r = n - r;
			//System.out.printf("Change %d to %d in calculating nCr\n", n-r, r);
		}
		/*
		 * nCr 	= n! / ((n-r)! * (r)! )
		 * 		= (n * n-1 * n-2 * .... * 1) / ( (n-r * n-r-1 * .. * 1) * (r * r-1 * ... * 1) )
		 *		= (n * n-1 * n-2 * n-r+1) / (r * r-1 * ... * 1)
		 */

		BigInteger result = BigInteger.ONE;

		for (int i = 0; i < r; i++) {
			result=result.multiply(new BigInteger(String.valueOf(n - i))); // n * n-1 * n-2 * .... * n-(r-1)
			result=result.divide( new BigInteger(String.valueOf(i + 1))); // r * r-1 * ... * 1
		}

		/*
		 * The loop is going to run only r times or (n-r) times for any n Time to calculate nCr : Min ( O(r) , O(n-r) )
		 */
		return result;
	}
}
