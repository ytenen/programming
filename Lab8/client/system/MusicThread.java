package system;

import javazoom.jl.player.Player;

import java.io.FileInputStream;

public class MusicThread extends Thread{
    String dvor = "/Users/main/Documents/ITMO/programming/Lab8/client/music/dvor.mp3";
    String taksi = "/Users/main/Documents/ITMO/programming/Lab8/client/music/taksi.mp3";
    String sineva = "/Users/main/Documents/ITMO/programming/Lab8/client/music/sineva.mp3";
    String peterburg = "/Users/main/Documents/ITMO/programming/Lab8/client/music/peterburg.mp3";
    @Override
    public void run(){
        while (true){
            try {
                Player player = new Player(new FileInputStream(taksi));
                player.play();
                Player player1 = new Player(new FileInputStream(peterburg));
                player1.play();
                Player player2 = new Player(new FileInputStream(sineva));
                player2.play();
                Player player3 = new Player(new FileInputStream(dvor));
                player3.play();
            }catch (Exception e){
                System.out.println("No more music");
            }

        }
    }
}
