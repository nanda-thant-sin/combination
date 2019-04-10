import java.math.BigInteger;
import java.util.Scanner;

/**
 * Support functions for parallel combination generator
 * 
 * @author Nanda
 *
 */
public class Numbering_combinations {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// COMPLEMENT(10,5,new int[]{0,3,4,6,7,8});
		// ORDER(5,new int[] {0,6,7,8,9,10});
		// RANKC(10,5,new int[] {0,3,4,6,7,8});
		// for(int i=1;i<=252;i++) {
		// int[] res=RANKCINV(10,5,i);
		// print_curr_comb(res);
		// }
		// RANKCINV(100, 5, 28232821);
		sc.close();
	}

	/**
	 * It calculate complement of current combination Time complexity O(r)
	 * 
	 * @param n
	 * @param r
	 * @param curr_comb
	 * @return complement of the curr_comb
	 */
	public static int[] COMPLEMENT(int n, int r, int[] curr_comb) {
		int[] complement = new int[r + 1];
		for (int i = 1; i <= r; i++) {
			complement[i] = (n + 1) - curr_comb[r - i + 1];
		}
		return complement;
	}

	/**
	 * Still don't understand but used for RANKC Time complexity is O(r^2)
	 * 
	 * @param r
	 * @param curr_comb
	 * @return order
	 */
	public static BigInteger ORDER(int r, int[] curr_comb) {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 1; i <= r; i++) {
			sum.add(get_nCr(curr_comb[i] - 1, i));
		}
		return sum;
	}

	/**
	 * Rank of current combination Time complexity is O(r^2)
	 * 
	 * @param n
	 * @param r
	 * @param curr_comb
	 * @return
	 */
	public static BigInteger RANKC(int n, int r, int[] curr_comb) {
		BigInteger rank = get_nCr(n, r);
		int[] complement = COMPLEMENT(n, r, curr_comb);
		rank.subtract(ORDER(r, complement));
		return rank;
	}

	/**
	 * Get combination from order Time complexity is O(m*n)
	 * 
	 * @param n
	 * @param r
	 * @param g
	 * @return
	 */
	public static int[] ORDERINV(int n, int r, BigInteger g) {
		int[] curr_comb = new int[r + 1];
		int j = n;
		for (int i = r; i >= 1; i--) {
			curr_comb[i] = 0;
			BigInteger t = get_nCr(j - 1, i);
			while (curr_comb[i] == 0) {
				if (g.compareTo(t) != -1) {
					curr_comb[i] = j;
					break;
				}
				t = (t.multiply(new BigInteger(String.valueOf(j - i - 1))))
						.divide(new BigInteger(String.valueOf(j - 1)));
				j = j - 1;
			}
			g = g.subtract(get_nCr(curr_comb[i] - 1, i));
		}
		return curr_comb;
	}

	/**
	 * Convert rank to combination
	 * 
	 * @param n
	 * @param r
	 * @param rank
	 * @return
	 */
	public static int[] RANKCINV(int n, int r, BigInteger rank) {
		int[] curr_comb = new int[r + 1];
		int[] complement = ORDERINV(n, r, get_nCr(n, r).subtract(rank));
		curr_comb = COMPLEMENT(n, r, complement);
		return curr_comb;
	}

	/**
	 * function to return total number of combinations out of n choose r
	 * 
	 * @param n
	 * @param r
	 * @return result
	 */
	public static BigInteger get_nCr(int n, int r) {
		if (n < r) {// doesn't make sense
			return BigInteger.ZERO;
		}
		if (n - r < r) { // choose smaller one
			r = n - r;
			// System.out.printf("Change %d to %d in calculating nCr\n", n-r, r);
		}
		/*
		 * nCr = n! / ((n-r)! * (r)! ) = (n * n-1 * n-2 * .... * 1) / ( (n-r * n-r-1 *
		 * .. * 1) * (r * r-1 * ... * 1) ) = (n * n-1 * n-2 * n-r+1) / (r * r-1 * ... *
		 * 1)
		 */

		BigInteger result = BigInteger.ONE;

		for (int i = 0; i < r; i++) {
			result = result.multiply(new BigInteger(String.valueOf(n - i))); // n * n-1 * n-2 * .... * n-(r-1)
			result = result.divide(new BigInteger(String.valueOf(i + 1))); // r * r-1 * ... * 1
		}

		/*
		 * The loop is going to run only r times or (n-r) times for any n Time to
		 * calculate nCr : Min ( O(r) , O(n-r) )
		 */
		return result;
	}

	/**
	 * Print current combination Time complexity is O(r)
	 * 
	 * @param curr_comb
	 *            contains current combination
	 */
	public static void print_curr_comb(int[] curr_comb) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < curr_comb.length; i++) {
			sb.append(curr_comb[i] + " ");
		}
		System.out.println(sb);
	}
}
