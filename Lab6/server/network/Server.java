package network;

import exeptions.UnknowCommandException;
import managers.CollectionManager;
import managers.RunManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Server {
    ServerSocketChannel ss;
    private int port;
    public RunManager runManager;
    public static final Logger serverLogger = Logger.getLogger("logger");

    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(bf));

    public Server(int port, RunManager runManager){
        this.port = port;
        this.runManager = runManager;
    }

    public void run(String path){
        try{
            openServerSocket();
            serverLogger.info("Connection with client has been created");

            while (true){
                if (reader.ready()){
                    String line = reader.readLine();
                    if (line.split(" ")[0].equals("save")){
                        String[] args = {path};
                        CollectionManager.save(args);
                        serverLogger.info("Objects saved successfully");
                    }
                    if (line.split(" ")[0].equals("exit")){
                        String[] args = {path};
                        CollectionManager.save(args);
                        serverLogger.info("Objects saved successfully");
                        System.exit(0);
                    }
                }

                SocketChannel clientSocket = ss.accept();
                if (clientSocket!= null){
                    processClientRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openServerSocket(){
        try{
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(port));
            ss.configureBlocking(false);
        } catch (IOException e) {
            serverLogger.warning("Error when trying to use port");
        }
    }

    private void processClientRequest(SocketChannel clientSocket) {
        Request userRequest;
        Response userResponse;
        try(ObjectInputStream clReader = new ObjectInputStream(clientSocket.socket().getInputStream());
        ObjectOutputStream clWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())){
            userRequest = (Request) clReader.readObject();
            serverLogger.info("Recieved request with command " + userRequest.getArgs()[0]);
            userResponse = runManager.run(userRequest);
            clWriter.writeObject(userResponse);
            serverLogger.info("Answer sended " + userResponse.getResult());
            clWriter.flush();
        }catch (ClassNotFoundException | UnknowCommandException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            serverLogger.warning("I/O error");
        } finally {
            try{
                clientSocket.close();
            } catch (IOException e) {
                serverLogger.warning("Error closing the client socket");
            }
        }
    }
}
