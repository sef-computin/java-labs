import java.lang.Math;
public class Primes{
	public static void main(String[] args){
		System.out.print("Primes: ");
		for (int i = 1; i<101;i++){
			if (isPrime(i)) System.out.print(i + " ");
		}
		System.out.println();

	}
	private static boolean isPrime(int n){
		for (int i=2; i < (int)Math.sqrt(n)+1;i++){
			if (n%i==0) return false;
		}
		return true;
	}
}