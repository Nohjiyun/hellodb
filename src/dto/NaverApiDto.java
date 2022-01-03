package dto;

public class NaverApiDto {
	
	// 번호
	private int num;
	// 키워드 연결 번호
	private int kidx;
	// 제목
	private String title;
	// 링크
	private String link;
	// 날짜
	private String pubDate;

	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getKidx() {
		return kidx;
	}

	public void setKidx(int kidx) {
		this.kidx = kidx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

}