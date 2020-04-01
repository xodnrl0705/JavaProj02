package Project2.ver05;

import java.util.Arrays;
import java.util.Scanner;

public class Game3by3 {
	
	String left,right,up,down;
	int xi,xj;
	Scanner scan = new Scanner(System.in);
	boolean exit ;
	
	String[][] puzzle = {

			{"1","2","3"},
			{"4","5","6"},
			{"7","8","X"}

	};
	String[][] correct = {

			{"1","2","3"},
			{"4","5","6"},
			{"7","8","X"}

	};
	public Game3by3() {}
	
	//게임진행메소드
	public void gameMenu() { 

		shufflePuzzle();
		exit = false;
		while(true) {
			movePuzzle();
			if(exit==true) {
				break;
			}
			switch(correctPuzzle()) {
			case 1://정답이고,재시작일때
				shufflePuzzle();
				break;
			case 2://정답이고, 종료할때
				return;
			case 3://안맞을때
				showPuzzle();
				break;
			}
		}
	}
	
	//퍼즐이 처음과 같은지 알아보는 메소드
	public int correctPuzzle() {
		boolean check = Arrays.deepEquals(puzzle, correct);
		String restart;
		if(check==true) {
			System.out.println("==^^정답입니다^^==");
			showPuzzle();
			
			System.out.println("재시작하시겠습니까?(y 누르면 재시작. 나머지는 종료)");
			restart = scan.nextLine();

			if(restart.equals("y")) {
				System.out.println("재시작하겠습니다.");
				System.out.println("===============");
				return 1;
			}
			else {
				System.out.println("게임을 종료합니다.");
				return 2;
			}
		}
		return 3;
	}
	
	//퍼즐을 섞는 메소드
	public void shufflePuzzle() {
		int shuffleNum = 1;
		String sub = "";
		
		while(shuffleNum<3) {
			try {
				int random = (int) (Math.random()*4);
				switch (random){
				case 0:
					findX();
					sub = puzzle[xi][xj];
					puzzle[xi][xj] = puzzle[xi][xj+1];
					puzzle[xi][xj+1] = sub;
					shuffleNum++;
					break;
					
				case 1:
					findX();
					sub=puzzle[xi][xj];
					puzzle[xi][xj]=puzzle[xi][xj-1];
					puzzle[xi][xj-1] = sub;
					shuffleNum++;
					break;
					
				case 2:
					findX();
					sub=puzzle[xi][xj];
					puzzle[xi][xj]=puzzle[xi+1][xj];
					puzzle[xi+1][xj] = sub;
					shuffleNum++;
					break;
					
				case 3:
					findX();
					sub=puzzle[xi][xj];
					puzzle[xi][xj]=puzzle[xi-1][xj];
					puzzle[xi-1][xj] = sub;
					shuffleNum++;
					break;		
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {
				
			}
		}
		//만약 셔플했는데도 정답과 같으면 다시 셔플
		boolean check = Arrays.deepEquals(puzzle, correct);
		if(check==true) {
			shufflePuzzle();
		}
		else{
			showPuzzle();
		}
	}
	
	//현재의 퍼즐의 형태를 보여주는 메소드
	public void showPuzzle() {
		System.out.println("===============");
		for(int i=0; i<puzzle.length;i++) {
			for(int j=0; j<puzzle[i].length;j++) {
					System.out.print(puzzle[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("===============");
	}
	
	//퍼즐안에있는 X의 인덱스 번호를 찾는메소드
	public void findX() {
		for(int i=0; i<puzzle.length;i++) {
			for(int j=0; j<puzzle[i].length;j++) {
				if(puzzle[i][j].equals("X")) {
					xi=i;
					xj=j;
				}
			}
		}
	}
	
	//퍼즐을 움직이는 메소드
	public void movePuzzle() {
		try {
			System.out.println("[ 이동 ] a:Left d:Right w:Up s:Down");
			System.out.println("[ 종료 ] x:Exit");
			System.out.println("키를 입력해주세요 : ");
			String choice = scan.nextLine();
			String sub= "";
			
			switch (choice){
			case "a":
				findX();
				sub=puzzle[xi][xj];
				puzzle[xi][xj]=puzzle[xi][xj+1];
				puzzle[xi][xj+1] = sub;
				break;
				
			case "d":
				findX();
				sub=puzzle[xi][xj];
				puzzle[xi][xj]=puzzle[xi][xj-1];
				puzzle[xi][xj-1] = sub;
				break;
				
			case "w":
				findX();
				sub=puzzle[xi][xj];
				puzzle[xi][xj]=puzzle[xi+1][xj];
				puzzle[xi+1][xj] = sub;
				break;
				
			case "s":
				findX();
				sub=puzzle[xi][xj];
				puzzle[xi][xj]=puzzle[xi-1][xj];
				puzzle[xi-1][xj] = sub;
				break;		
			case "x":
				System.out.println("게임을 종료합니다.");
				exit = true;
				break;
			}
			
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("xxxxxxxxxxxxxxxxx");
			System.out.println("xxxxxx이동불가xxxxx");
			System.out.println("xxxxxxxxxxxxxxxxx");
			movePuzzle();
		}
			
	}
	
}