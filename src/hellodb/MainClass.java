package hellodb;

import java.util.ArrayList;
import java.util.Scanner;

import dao.KeywordDao;
import dao.KeywordDaoImpl;
import dao.NaverInfoDao;
import dao.NaverInfoDaoImpl;
import dto.NaverApiDto;
import naverapi.NaverAPI;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello db");
		// 데이터베이스 연결
		NaverInfoDao dao = new NaverInfoDaoImpl();
		// 데이터 넣기
//		dao.insertNaverInfo();
		
//		String tit = "이 제목을 전달 Bean";
//		String url = "이 URL 을 전달 Bean";
//		String date = "이 날짜를 전달 Bean";
//		NaverApiDto dto = new NaverApiDto();
//		dto.setTitle(tit);
//		dto.setLink(url);
//		dto.setPubDate(date);
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력(뉴스): ");
		String search = sc.next();
		// 실제 네이버 정보를 받아와서 데이터 베이스에 넣기
		NaverAPI nApi = new NaverAPI();
		String jsonData = nApi.searchNews(search);  // 이 검색어를 DB에 저장
		KeywordDao kdao = new KeywordDaoImpl();
		kdao.insert(search);   /// 키워드 디비에 넣기
		int kidx = kdao.selectOne(search);
		ArrayList<NaverApiDto> nList = nApi.getListJson(kidx, jsonData);
		for (NaverApiDto d : nList) {
			dao.insertNaverInfo(d);
		}
		

		// 위 변수를 넘겨도 되고 우리가 만든 DTO로 넘겨도 됩니다
		// 메인 위치에서 데이터를 전달 해서 데이터 넣기
//		dao.insertNaverInfo(tit, url, date);
//		dao.insertNaverInfo(dto);
		
		// 데이터 전체 보기(여기서 출력해보자)
		ArrayList<NaverApiDto> list = dao.selectAll();
		for (NaverApiDto d : list) {
			System.out.println(d.getTitle());
			System.out.println(d.getLink());
			System.out.println(d.getPubDate());
		}
		
		// 데이터 보기(하나)
//		dao.selectOne(2);
		
		
		// 데이터 수정
//		dao.updateNaverInfo(2);
		// 데이터 보기
//		dao.selectOne(2);

	}

}