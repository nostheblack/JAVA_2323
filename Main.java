class Sports{
    String getName(){ return "아직 결정되지 않음";}
    int getPlayers(){ return 0;}
}

class Soccer extends Sports {
    @Override
    String getName() {
        return "축구";
    }

    @Override
    int getPlayers() {
        return 11;
    }
}

public class Main {
    public static void main(String[] args) {
        Sports sports = new Soccer();
        System.out.println("스포츠 이름: " + sports.getName());
        System.out.println("선수 수: " + sports.getPlayers());
    }
}





