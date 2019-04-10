import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;

/**
 * @author Nanda
 *
 */
public class CustomRecursiveAction extends RecursiveAction {
	int n;
	int r;
	BigInteger start;
	BigInteger each;
	BigInteger total;
	BigInteger processors;

	CustomRecursiveAction(int n, int r, BigInteger start, BigInteger each, BigInteger total, BigInteger processors) {
		this.n = n;
		this.r = r;
		this.start = start;
		this.each = each;
		this.total = total;
		this.processors = processors;
	}

	@Override
	protected void compute() {
		if (each.compareTo(BigInteger.ZERO) != 0) {
			if (start.compareTo(total) == -1) {
				int[] curr_comb = Numbering_combinations.RANKCINV(n, r, start);
				Numbering_combinations.print_curr_comb(curr_comb);
				for (BigInteger i = new BigInteger(String.valueOf(2)); i.compareTo(each) != 1; i = i
						.add(BigInteger.ONE)) {
					curr_comb = Generating_combinations_sequentially.NEXT_COMBINATION(n, r, curr_comb);
				}
			}
		} else {
			Set<CustomRecursiveAction> data = new HashSet<CustomRecursiveAction>();
			each = total.divide(processors);
			if ((each.multiply(processors)).compareTo(total) != 0) {
				each = each.add(BigInteger.ONE);
			}
			for (BigInteger i = BigInteger.ONE; i.compareTo(processors) != 1; i = i.add(BigInteger.ONE)) {
				BigInteger st = ((i.subtract(BigInteger.ONE)).multiply(each)).add(BigInteger.ONE);
				CustomRecursiveAction curr = new CustomRecursiveAction(n, r, st, each, total, processors);
				data.add(curr);
			}
			invokeAll(data);
		}
	}
}