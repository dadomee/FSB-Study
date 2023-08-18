import java.util.*;

public class Exam_04 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		/*2�� n���� �Է� �޾� ���� ��� �����ִ� ���α׷�
		n = 4
		2^4 = 2 * 2^3
		2^3 = 2 * 2^2
		2^2 = 2 * 2^1
		2^1 = 2 * 2^0
		*/
		System.out.print("n = " );
		int n = in.nextInt();

		long res = ex(n); //ū���� n���� �Է������� �������� int���� ū long �̾����

		System.out.printf("2�� %d���� %d�̴�.\n", n, res);
		}

		public static long ex(int su) {
		if(su==0) return 1;
		return 2 * ex(su-1);
		}
}