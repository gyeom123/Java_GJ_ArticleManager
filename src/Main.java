import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("프로그램 시작");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();

			int id = lastArticleId + 1;
			lastArticleId = id;

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력하세요.");
				continue;
			}

			if (cmd.equals("article exit")) {
				break;
			}

			if (cmd.equals("article werir")) {

				System.out.printf("제목 : \n");
				String title = sc.nextLine();
				System.out.printf("내용 : \n");
				String body = sc.nextLine();
				System.out.printf("%d번글이 생성되었습니다.\n", id);

				Article article = new Article(id, title, body);
				articles.add(article);
			}

			else if (cmd.equals("article list")) {
			
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.err.println("번호    ㅣ     제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.err.printf("%d      ㅣ     %s\n", article.id, article.title);
				}

			} else {
				System.out.println("없는 명령어입니다.");
			}
		}

		sc.close();
		System.out.println("프로그램 종류");
	}
}

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;

	}

}
