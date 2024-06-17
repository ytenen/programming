package system;

import network.Program;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Program program = new Program();
        MusicThread musicThread = new MusicThread();
        musicThread.setDaemon(true);
        //musicThread.start();
        program.execute();
    }
}
