import java.util.Scanner;
public class Exam_05 {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		//���丮�� ���� ���ϴ� ���α׷�
		System.out.print("���丮�� ���� �Է� : ");
		int su = in.nextInt();
		
		int res = fac(su);
		
		System.out.printf("���丮�� �� %d�� ����� %d�Դϴ�.", su, res);
	}
	public static int fac(int n) {
		if(n==1) return 1;
		return n * fac(n-1);
	}
}

/* su = 5�϶�
fac(5) = 5 * fac(4)
fac(4) = 4 * fac(3)
.
.
.
fac(1) = 1 //�Ųٷ� ���� �ۼ�
*/
