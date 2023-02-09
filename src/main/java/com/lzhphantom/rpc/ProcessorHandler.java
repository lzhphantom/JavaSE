package sg.com.ncs.luozhihui.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author luozhihui
 * @date 2/9/2023
 */
@AllArgsConstructor
@Data
public class ProcessorHandler implements Runnable{
    private Socket socket;
    private Object server;
    @Override
    public void run() {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Object rs = invoke(request);
            System.out.println("服务端处理的结果："+rs);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                outputStream.writeObject(rs);
                outputStream.flush();
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName(request.getClassName());
        Method method = clazz.getMethod(request.getMethodName(), request.getTypes());
        return method.invoke(server,request.getArgs());
    }
}
