package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 == ");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();
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
				int id = lastArticleId + 1;
				lastArticleId = id; // 회원이 늘때마다 1씩증가하게 만들어주는 변수

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				Article article = new Article(id, title, body);
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

				System.out.println("번호		|		제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d		|		%s\n", article.id, article.title);
				//articles에 있는 모든 아이디 제목을 출력할 수 있게만든 반복문
				}

			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				//입력받은 문장을 " "을 기준으로 쪼개어 문자열에 저장할 수 있는 명령어
				

				int id = Integer.parseInt(cmdBits[2]);
				// mdBits [0] == "article" ,cmdBits [1] == "detail" ,cmdBits [2] == " "

				Article foundArticle = null;
				//Article를 조종할 수 있는 리모콘 

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						//만약 사용자가 원하는 아이디와 article에 있는 아이디가 동일시 해당 아이디를 가진 방을 조종할 수 있는 리모콘을 foundArticle에 넘겨 조종할 수 있게만든다 
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				//만약 모든 아이디를 다 찾았지만 없는 경우에는 해당 게시글이 없는 것이므로 게시물은 존재하지 않습니다을 출력
				}

				System.out.printf("%d번 게시물은 존재합니다\n", id);
				//foundArticle(사용자가 원하는 article)에 있는 id,title,body를 조종해서 출력할 수 있다
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : 2023-12-09 12:12:12\n");
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else {
				System.out.println("없는 명령어입니다");
			}
		}
		sc.close();
		System.out.println("== 프로그램 종료 == ");
	}
}

class Article {
	int id; // 고유번호
	String title;// 게시글 제목
	String body;// 게시글 내용

	public Article(int id, String title, String body) {
		this.id = id; // 외부에서 입력받은 정보를 객체안에 넣는다
		this.title = title;
		this.body = body;
	}
}