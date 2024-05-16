package network;

import exeptions.UnknowCommandException;
import managers.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Server {
    ServerSocketChannel ss;
    private int port;
    private RunManager runManager;
    private Request request;
    private Response response;
    private DatabaseManager databaseManager;
    private CollectionManager collectionManager;
    public static final Logger serverLogger = Logger.getLogger("logger");

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private ForkJoinPool forkJoinPool = new ForkJoinPool(3);
    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader reader = new BufferedReader(new InputStreamReader(bf));


    public Server(int port, RunManager runManager, DatabaseManager databaseManager, CollectionManager collectionManager){
        this.port = port;
        this.runManager = runManager;
        this.collectionManager = collectionManager;
        this.databaseManager = databaseManager;
    }


    public void run(){
        try{
            openServerSocket();
            serverLogger.info("Connection with client has been created");
            while (true){
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
        try(ObjectInputStream clReader = new ObjectInputStream(clientSocket.socket().getInputStream());
        ObjectOutputStream clWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())){
            cachedThreadPool.submit(()->readRequest(clReader)).get();
            serverLogger.info("Recieved request with command " + request.getArgs()[0]);
            forkJoinPool.submit(() -> comExecute(request)).get();
            cachedThreadPool.submit(() -> sendResponse(response, clWriter)).get();
            serverLogger.info("Answer sended " + response.getResult());
            clWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            serverLogger.warning("I/O error");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try{
                clientSocket.close();
            } catch (IOException e) {
                serverLogger.warning("Error closing the client socket");
            }
        }

    }
    private  Response  comExecute(Request request) {
        response = CommandManager.startExecuting(request);
        return response;
    }

    private synchronized void readRequest(ObjectInputStream objectInputStream) {
        try {
            request = (Request) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void sendResponse(Response s,  ObjectOutputStream writer) {
        try {
            writer.writeObject(s);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
