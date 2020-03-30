package Project2;

import Project2.ver02.AccountManager;

public class BankingSystemVer02 {

	public static void main(String[] args) {
		
		AccountManager bank = new AccountManager(50);
		bank.showMenu();
	}

}
