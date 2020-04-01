package Project2.ver04;

public class HighCreditAccount extends Account{
	
	int rate;
	String grade;
	
	public HighCreditAccount(String acn, String n, int b, int r, String g) {
		super(acn,n,b);
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
		balance = balance + (balance * rate) / 100 
				+ (balance * grate) / 100 + money;
	}
	
	/*
	 신용계좌 : 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
		Ex) 5000 + (5000 * 0.02) + (5000 * 0.04) + 2000 = 7,300원

	 */ 
}



