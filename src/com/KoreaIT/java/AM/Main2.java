package com.KoreaIT.java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;
//자동 인푸트 : 컨트로 + 쉬프트 + o

public class Main2 {
	public static void main(String[] args) {
		//구글링해서 얻은 코드가 불안전할 수 있기 때문에 main2클래스는 오직 코딩을 실험을 목적으로 만든 클래스
		
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date time = new Date();

		String time1 = format1.format(time);

		System.out.println(time1);
	}
}
