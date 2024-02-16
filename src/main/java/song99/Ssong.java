package song99;

public class Ssong {
	public int sid;
	public String title;
	public String lyrics;
	
	public Ssong() { }

	
	
	public Ssong(String title, String lyrics) {
		this.title = title;
		this.lyrics = lyrics;
	}


	public Ssong(int sid, String title, String lyrics) {
		this.sid = sid;
		this.title = title;
		this.lyrics = lyrics;
	}

	@Override
	public String toString() {
		return "Ssong [sid=" + sid + ", title=" + title + ", lyrics=" + lyrics + "]";
	}



	public int getSid() {
		return sid;
	}



	public void setSid(int sid) {
		this.sid = sid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getLyrics() {
		return lyrics;
	}



	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	
	
}
