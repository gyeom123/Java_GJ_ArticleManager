package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;
//자동 인푸트 : 컨트로 + 쉬프트 + o

//안전하고 자주 사용하는 코드를 함수로 만들어서 편하게 쓸려는 목적으로 만든 클래스
public class Util {
	//해당 날짜 시간 출력
	public static String getNowDatestr() {
    //객체를 따로 만들지않게 static으로 만듬
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date time = new Date();

		return format.format(time);

	}
}