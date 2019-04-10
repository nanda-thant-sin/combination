import java.math.BigInteger;
import java.util.Scanner;

/**
 * Generate all combinations sequentially
 * Time complexity is O(nCr * r)
 * @author Nanda
 *
 */
public class Generating_combinations_sequentially {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		long startTime = System.nanoTime();
		SEQUENTIAL_COMBINATIONS(n,r);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		sc.close();
	}
	/**
	 * Sequential algorithm for printing all combinations of n choose r
	 * Time complexity is O(nCr * r)
	 * @param n
	 * @param r
	 */
	public static void SEQUENTIAL_COMBINATIONS(int n,int r) {
		int[] curr_comb=new int[r+1];
		for(int i=1;i<=r;i++) {
			curr_comb[i]=i;
		}
		print_curr_comb(curr_comb);
		BigInteger nCr=Find_nCr.get_nCr(n, r);
		for(BigInteger i=new BigInteger(String.valueOf(2));i.compareTo(nCr)!=1 ;i=i.add(BigInteger.ONE)) {
			curr_comb=NEXT_COMBINATION(n,r,curr_comb);
		}
	}
	
	/**
	 * Produce next combination based on current combination
	 * and print the result
	 * Time complexity is O(r)
	 * @param n
	 * @param r
	 * @param curr_comb contains current combination
	 * @return updated combination
	 */
	public static int[] NEXT_COMBINATION(int n,int r,int[] curr_comb) {
		int j=r;
		while(j>0) {
			if(curr_comb[j]<n-r+j) {//check updatable or not
				curr_comb[j]=curr_comb[j]+1;
				for(int i=j+1;i<=r;i++) {
					curr_comb[i]=curr_comb[i-1]+1;
				}
				print_curr_comb(curr_comb);
				break;
			}
			else {
				j=j-1;
			}
		}
		return curr_comb;
	}
	
	/**
	 * Print current combination
	 * Time complexity is O(r)
	 * @param curr_comb contains current combination
	 */
	public static void print_curr_comb(int[] curr_comb) {
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<curr_comb.length;i++) {
			sb.append(curr_comb[i]+" ");
		}
		System.out.println(sb);
		//System.out.println(Numbering_combinations.ORDER(curr_comb.length-1, curr_comb));
	}
}
