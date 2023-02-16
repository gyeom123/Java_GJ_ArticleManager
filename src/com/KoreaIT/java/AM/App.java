package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class App {

	private static List<Article> articles; // 생명주기가 긴 전역변수로 articles를 옮김
	// private(접근 지정자)은 외부(Main제외한 모든 지역)에서 접근이 불가능하다

	static {
		articles = new ArrayList<>();
		// 스태틱 생성자를 통해 한번 분리를 한후 프로그램이 시작할때 articles를 만든다
	}

	public void start() {
		// static은 static끼리 만날수 있다

		System.out.println("== 프로그램 시작 == ");

		makeTesData();

//		List<Article> articles = new ArrayList<>(); 
//		다른 지역에는 도달을 하지못하는 지역변수이므로 생명주기가 긴 전역변수로 옮겨준다

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어 ) ");

			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요");
				continue;
			}
			if (cmd.equals("system exit")) {
				break;
			}
			if (cmd.equals("article write")) {
				int id = articles.size() + 1; // 회원이 늘때마다 1씩증가하게 만들어주는 변수

				String regDate = Util.getNowDatestr();
				// Util클래스의 있는 getNowDatestr함수를 불러드려 리턴값을 regDate에 ㅈ준다

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				Article article = new Article(id, regDate, title, body, 0);
				// 입력받은 제목 내용 아이디를 종이로 만들어들어서 Article에 저장
				articles.add(article);
				// 정보를 모두 저장한 종이(Article)를 폴더에(articles) 저장
				System.out.printf("%d번글이 생성되었습니다\n", id);

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					// articles의 사이즈가 0이면 게시글이 없는 것이므로 게시글없습니다가 출력 continue를 만나 다시 명령어를 받을 준비를 한다
					System.out.println("게시글이 없습니다");
					continue;
				}

				System.out.println("번호		|        제목	     | 	  조회수");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d		|	 %s     |   %4d\n", article.id, article.title, article.viewCnt);
					// articles에 있는 모든 아이디 제목을 출력할 수 있게만든 반복문
				}

			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				// 입력받은 문장을 " "을 기준으로 쪼개어 문자열에 저장할 수 있는 명령어

				int id = Integer.parseInt(cmdBits[2]);
				// mdBits [0] == "article" ,cmdBits [1] == "detail" ,cmdBits [2] == " "

				Article foundArticle = null;
				// Article를 조종할 수 있는 리모콘

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						// 만약 사용자가 원하는 아이디와 article에 있는 아이디가 동일시 해당 아이디를 가진 방을 조종할 수 있는 리모콘을
						// foundArticle에 넘겨 조종할 수 있게만든다
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
					// 만약 모든 아이디를 다 찾았지만 없는 경우에는 해당 게시글이 없는 것이므로 게시물은 존재하지 않습니다을 출력
				}

				foundArticle.increaseViewCnt(); // 조회수 1씩증가 함수

				// foundArticle(사용자가 원하는 article)에 있는 id,title,body등(을)를 조종해서 출력할 수 있다
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회수 : %d\n", foundArticle.viewCnt);

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				// 입력받은 문장을 " "을 기준으로 쪼개어 문자열에 저장할 수 있는 명령어

				int id = Integer.parseInt(cmdBits[2]); // 문자열에 있는 문자를 정수로 바꿔주는 명령어
				// mdBits [0] == "article" ,cmdBits [1] == "detail" ,cmdBits [2] == " "

				int foundIndex = -1;
				// 원하는 게시글이 없는 경우를 생각해 생성한 변수

				// size() => 3(게시글 갯수)
				// index : 0 1 2(방의 번호)
				// id : 1 2 3 (사용자의 고유번호)

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {

						foundIndex = i;
						// foundIndex의 변수 i(articles의 방번호)를 대입하여 foundIndex를 활용하여 해당 배열의 방으로 찾아갈 수 있다
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);

					continue;
					// 만약 모든 방(을)를 다 찾았지만 없는 경우에는 해당 게시글이 없는 것이므로 게시물은 존재하지 않습니다을 출력
				}

				articles.remove(foundIndex); // 원하는 방을 찾았고 해당방을 추출하여 삭제한다.
				System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);

			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				// 입력받은 문장을 " "을 기준으로 쪼개어 문자열에 저장할 수 있는 명령어

				int id = Integer.parseInt(cmdBits[2]); // 문자열에 있는 문자를 정수로 바꿔주는 명령어
				// mdBits [0] == "article" ,cmdBits [1] == "detail" ,cmdBits [2] == " "

				Article foundArticle = null;
				// 원하는 게시글이 없는 경우를 생각해 생성한 변수

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {

						foundArticle = article;
						// foundIndex의 변수 i(articles의 방번호)를 대입하여 foundIndex를 활용하여 해당 배열의 방으로 찾아갈 수 있다
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);

					continue;
					// 만약 모든 방(을)를 다 찾았지만 없는 경우에는 해당 게시글이 없는 것이므로 게시물은 존재하지 않습니다을 출력
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물이 수정 되었습니다.\n", id);

			}

			else {
				System.out.println("없는 명령어입니다");
			}
		}
		sc.close();
		System.out.println("== 프로그램 종료 == ");
	}


	private  void makeTesData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getNowDatestr(), "title 1", "body 1", 11));
		articles.add(new Article(2, Util.getNowDatestr(), "title 2", "body 2", 22));
		articles.add(new Article(3, Util.getNowDatestr(), "title 3", "body 3", 33));
	}
}
