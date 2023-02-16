package com.KoreaIT.java.AM.dto;

public class Article {
	public int id; // 고유번호
	public String regDate; // 현재 날짜 시간
	public String title;// 게시글 제목
	public String body;// 게시글 내용
	public int viewCnt; // 게시글의 조회수

	public Article(int id, String regDate, String title, String bodym) {
		this(id,  regDate, title, bodym, 0);
		//다른 생성자에 일을 넘기는 매서드
	}
	public Article(int id, String regDate, String title, String bodym, int viewCnt) {
		
		this.id = id; // 외부에서 입력받은 정보를 객체안에 넣는다
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.viewCnt = viewCnt; 
	}

	public void increaseViewCnt() {
		viewCnt++; // 게시글을 보때마다 1씩 증가하는 목적으로 만드는 함수
	}
}
