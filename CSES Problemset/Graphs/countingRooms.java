import java.util.Scanner;

public class countingRooms{
	
	int N = 1000;
	int graph[][] = new int[N][N];
	boolean visited[][] = new boolean[N][N];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		double tuna = 5.28;
		int [] a = new int[7];
		for (int i = 0; i < 7; i++) {
			a[i] = in.nextInt();
		}
		for (int i = 0; i < 7; i++) {
			System.out.println(a[i]);
		}
		tuna += 7;
		System.out.println("Hello World " + a[3]);
	}
}