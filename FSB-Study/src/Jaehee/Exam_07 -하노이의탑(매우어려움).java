import java.util.*;

public class Exam_07 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//Day09. �ϳ�����ž ����(�ſ� �����)
		
		System.out.print("������ ���� �Է� : ");
		int n = in.nextInt();
		
		hanoi(n, 1, 2, 3);
		//������ ������ pol ����(��� ����)�� �ϳ��� �޼ҵ�� �����Ѵ�.

	}
	
	public static void hanoi(int n, int f_pol, int s_pol, int t_pol) {
		if (n == 1) { //���� 1����� 1����տ��� 3�� ������� ������
			System.out.println(n + " : " + f_pol + " -> " + t_pol);
			return;
		} 
		// N-1���� ������ 1�� ���뿡�� 2�� ����� �̵�
		// N-1, 1(����), 3(�߰�����), 2(�ű� ��ǥ)
		hanoi(n-1, f_pol, t_pol, s_pol);
		// ���� ū ������ 1�� ���뿡�� 3�� ����� �̵�
		System.out.println(n + " : " + f_pol + " -> " + t_pol);
		// N-1���� ������ 2�� ���뿡�� 3�� ����� �̵�
		// N-1, 2(����), 1(�߰�����), 3(�ű� ��ǥ)
		hanoi(n-1, s_pol, f_pol, t_pol);
	}
}

//���� : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=441&sca=2080
//�ؼ� : https://st-lab.tistory.com/96

//�߰����� ���ļ� ���� ������ �ݺ�

/*

3, 1, 2, 3
2, 1, 3, 2
1, 

*/