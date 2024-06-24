package co.pipat.shai_hulud.feature.proxy.v4;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
* [] - Proxy request and response
* */
@Log4j2
@Service
public class V4Proxy {
  public void x() throws IOException {
    ServerSocket serverSocket = new ServerSocket(2777);
    Socket socket = serverSocket.accept();

    InputStream rqInputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(rqInputStream);

    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String line = null;
    StringBuilder rawRequestBuilder = new StringBuilder();
    for(;;){
      System.out.println("1");
      line = bufferedReader.readLine();
      System.out.println("2");
      if(rawRequestBuilder.length()!=0){
        rawRequestBuilder.append(System.lineSeparator());
      }
      if(ObjectUtils.isEmpty(line)) {
//				if(line==null) {
        break;
      }else{
        System.out.println("-------->"+line+"<");
      }
      rawRequestBuilder.append(line);
      System.out.println("3");
    }

    String rawRequest = rawRequestBuilder.toString();

    Socket socket2 = new Socket("",443);

    log.warn(rawRequestBuilder.toString());

    bufferedReader.close();
    inputStreamReader.close();
    rqInputStream.close();
    socket.close();
  }

}
