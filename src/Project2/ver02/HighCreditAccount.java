package Project2.ver02;

/*
신용신뢰계좌 > 신용도가 높은 고객에게 개설이 허용되며 높은 이율의 계좌이다.
NormalAccount 와 마찬가지로 생성자를 통해서 이율정보(이자비율의정보)를 
초기화 할수있도록 정의한다.
고객의 신용등급을 A, B, C로 나누고 계좌개설시 이 정보를 등록한다.
A,B,C 등급별로 각각 기본이율에 7%, 4%, 2%의 이율을 추가로 제공한다.
 */
public class HighCreditAccount extends Account{

	int rate;
	String grade;
	
	public HighCreditAccount(String acn, String n, int b, int r, String g) {
		super(acn, n, b);
		rate = r;
		grade = g;
		
	}

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("기본이자: "+ rate + "%");
		System.out.println("신용등급: "+ grade);
	}
	@Override
	public void deposit(int money) {

		int grate = 0;
		
		if(grade.equals("A")) {
			grate = CustomSpecialRate.A;
		}
		else if (grade.equals("B")) {
			grate = CustomSpecialRate.B;
		}
		else if (grade.equals("C")) {
			grate = CustomSpecialRate.C;
		}
		System.out.println(grate);
		balance = balance + (balance * rate) / 100 
				+ (balance * grate) / 100 + money;

	}
}

/*
 신용계좌 : 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
	Ex) 5000 + (5000 * 0.02) + (5000 * 0.04) + 2000 = 7,300원

 */ 
