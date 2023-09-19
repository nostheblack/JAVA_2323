public class DVDPlayer implements ExPlayers, Players{
	public void play() {
		System.out.println("dvd 재생 시작!");
		
	}
	public void stop() {
		System.out.println("dvd 재생 정지!");
		
	}
	public void slow() {
		System.out.println("dvd 느린 재생 시작!");
		
	}
	
}