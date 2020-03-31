package Project2.ver05;

import java.sql.SQLException;
import java.util.Scanner;

public class Account extends ConnectDB{
	
	Game3by3 game = new Game3by3();
	public Account() {
		super();
	}
	
	// 메뉴출력
	public void showMenu() {
		
		while(true) {
			
			System.out.println("-----Menu------");
			System.out.println("1.계좌개설");
			System.out.println("2.입	금");
			System.out.println("3.출	금");
			System.out.println("4.계좌정보출력");
			System.out.println("5.3by3게임시작");
			System.out.println("6.프로그램종료");
			System.out.print("선택: "); 
			
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			
			case MenuChoice.MAKE:
				makeAccount();
				break;
			case MenuChoice.DEPOSIT:
				depositMoney();
				break;
			case MenuChoice.WITHDRAW:
				withdrawMoney();
				break;
			case MenuChoice.INQUIRE:
				showAccInfo();
				break;
			case MenuChoice.GAME:
				game.gameMenu();
				break;
			case MenuChoice.EXIT:
				System.out.println("프로그램을 종료합니다.");
				close();
				return;
			}
		}
	}       
	// 계좌개설을 위한 함수(prepared)
	
	
	public void makeAccount() {
		
		try {
			//1.쿼리문준비
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?)";
			//2.prepared객체 생성
			psmt = con.prepareStatement(query);
			
			//3.DB입력값 입력받음.
			Scanner scan = new Scanner(System.in);
			System.out.println("계좌번호: ");
			String acNumber = scan.nextLine();
			System.out.println("이름: ");
			String name = scan.nextLine();
			System.out.println("잔고: ");
			int balance = scan.nextInt();
			scan.nextLine();
			
			//4.인파라미터 설정하기 : ?의 순서대로 설정하고 DB이므로 인덱스는 1부터
			psmt.setString(1, acNumber);
			psmt.setString(2, name);
			psmt.setInt(3, balance);
			
			//5.쿼리실행을 위해 prepared객체 실행.
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}    
	// 입    금
	public void depositMoney() {
		
		String sql = "UPDATE banking_tb  "
	            + "SET balance = balance + ? "
	            + "WHERE ac_number = ? ";
		try {
			psmt = con.prepareStatement(sql);
	         
	        Scanner scan = new Scanner(System.in);
	        System.out.println("입금할 계좌번호: ");
	        String acNumber = scan.nextLine();
	         
	        System.out.println("입금할 금액: ");
	        int balance = scan.nextInt();
	        scan.nextLine();
	         
	        psmt.setInt(1, balance);
	        psmt.setString(2, acNumber);
	         
	        int affected = psmt.executeUpdate();
	        System.out.println(affected + "행이 업데이트 되었습니다.");
		}
		catch (SQLException e) {
	         e.printStackTrace();
	    }
	     
	}  
	// 출    금
	public void withdrawMoney() {
		
		String sql = "UPDATE banking_tb  "
	            + "SET balance = balance - ? "
	            + "WHERE ac_number = ? ";
		try {
			psmt = con.prepareStatement(sql);
	         
	        Scanner scan = new Scanner(System.in);
	        System.out.println("출금할 계좌번호: ");
	        String acNumber = scan.nextLine();
	         
	        System.out.println("출금할 금액: ");
	        int balance = scan.nextInt();
	        scan.nextLine();
	         
	        psmt.setInt(1, balance);
	        psmt.setString(2, acNumber);
	         
	        int affected = psmt.executeUpdate();
	        System.out.println(affected + "행이 업데이트 되었습니다.");
		}
		catch (SQLException e) {
	         e.printStackTrace();
	    }
		
	} 
	// 전체계좌정보출력
	public void showAccInfo() {
		try {
			stmt = con.createStatement();
	         
	        String query = "SELECT * from banking_tb ";
	        
	        rs = stmt.executeQuery(query);
	        
	        while(rs.next()) {
	        	String acNumber = rs.getString(1);//employee_id컬럼
				String name = rs.getString(2);//컬럼명을 그대로 이용함
				int balance = rs.getInt(3);
				
				System.out.printf("%s %s %d%n",
						acNumber, name, balance);
	         }
	      }
	      catch (SQLException e) {
	         System.out.println("쿼리오류발생");
	         e.printStackTrace();
	      }

		System.out.println("===전체정보가 출력되었습니다.==");
		
	}  

}
