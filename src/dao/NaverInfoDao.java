package dao;

import java.util.ArrayList;
import dto.NaverApiDto;
 
public interface NaverInfoDao {

	public void insertNaverInfo();
	public void insertNaverInfo(String tit, String url, String date);
	public void insertNaverInfo(NaverApiDto dto);
	public void selectOne(int id);
	public ArrayList<NaverApiDto> selectAll();
	public void updateNaverInfo(int id);

}