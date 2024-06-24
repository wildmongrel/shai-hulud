package co.pipat.shai_hulud.feature.proxy.service;

import co.pipat.shai_hulud.feature.proxy.model.SessionData;
import co.pipat.shai_hulud.feature.proxy.util.ProxyUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
    SessionData sessionData = new SessionData();
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
        break;
      }else{
        ProxyUtils.extractHeader(sessionData,line);
        System.out.println("-------->"+line+"<");
      }
      rawRequestBuilder.append(line);
      System.out.println("3");
    }

    String rawRequest = rawRequestBuilder.toString();

    // forward request to destination
//    String host = sessionData.getHeaderData().getHeaders().get()
//    int port = sessionData.getPort();
    String host = "";
    int port = 0;
    log.info("------------------ Host:Port ----------------");
    log.info("{}:{}",host,port);
    Socket socket2 = new Socket("www.google.com",443);
    OutputStream outputStream = socket2.getOutputStream();
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

    InputStream inputStream = socket2.getInputStream();

    ProxyUtils.close(
            socket2,
            outputStream,
            outputStreamWriter,
            bufferedWriter
    );

    log.warn(rawRequestBuilder.toString());
    
    ProxyUtils.close(
            bufferedReader,
            inputStreamReader,
            rqInputStream,
            socket
    );
  }

}
