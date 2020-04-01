package Project2.ver04;

public class NormalAccount extends Account{
	
	int rate;//이율
	
	public NormalAccount(String acn, String n, int b, int r) {
		super(acn, n, b);
		rate = r;
	}

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("기본이자: "+ rate + "%");
	}
	
	@Override
	public void deposit(int money) {
		
		balance = balance + (balance * rate) / 100 + money; 
	}
	
	/*
	이자계산방식 : 잔고:5000원, 기본이자:2%, 신용등급이자:4%, 입금액:2000원이라 가정하면….
	일반계좌 : 잔고 + (잔고 * 기본이자) + 입금액 
	Ex) 5000 + (5000 * 0.02) + 2000 = 7,100원
	 */
	
}
