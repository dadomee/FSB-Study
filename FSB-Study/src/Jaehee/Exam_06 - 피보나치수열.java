import java.util.*;
public class Exam_06 {
 public static void main(String[] args) {
	 
	 //�Ǻ���ġ ����(�䳢 ���ķ�)
	 //ù���� �ڱ��ڽŸ�, 1 1 2 3 5 8 13 21 34 55 89 144
	 //n = 2 �̸� 1��
	 Scanner in = new Scanner(System.in);
	 
	 System.out.print("�Ǻ���ġ ������ ��ġ�� �Է� : ");
	 int po = in.nextInt();
	 
	 for(int i = 1; i<po; ++i) { //10�� �Է��Ѵٸ� 9�� �ݺ��Ͽ� ����ϰ�
	 System.out.print(fibo(i) + ",");
	 }
	 System.out.println(fibo(po)); //������ �� 10��°�� ���

 }
 	public static int fibo(int n) {
 		if(n<=2) return 1; //���� 1���� "1, 1"
 		return fibo(n-2) + fibo(n-1);
 	} //1 1 2 3=fibo(1)+fibo(2) 5=fibo(2)+fibo(3).......
}

