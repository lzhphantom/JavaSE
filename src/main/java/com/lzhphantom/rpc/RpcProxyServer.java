package sg.com.ncs.luozhihui.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luozhihui
 * @date 2/9/2023
 */
public class RpcProxyServer {
    public final ExecutorService executorService =
            Executors.newCachedThreadPool();
    public void publisher(Object server, int port){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket,server));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
