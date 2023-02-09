package sg.com.ncs.luozhihui.rpc;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author luozhihui
 * @date 2/9/2023
 */
@AllArgsConstructor
public class RpcNetTransport {
    private String host;
    private int port;
    public Socket createSocket() throws IOException {
        return new Socket(host, port);
    }

    public Object send(RpcRequest request) throws IOException, ClassNotFoundException {
        Socket socket = createSocket();
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(request);
        outputStream.flush();
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        return inputStream.readObject();
    }
}
