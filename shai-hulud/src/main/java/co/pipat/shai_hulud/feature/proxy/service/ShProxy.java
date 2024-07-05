package co.pipat.shai_hulud.feature.proxy.service;

import co.pipat.shai_hulud.feature.proxy.model.SessionData;
import co.pipat.shai_hulud.feature.proxy.util.ProxyUtils;
import com.sun.net.httpserver.Headers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpHeaders;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/*
* [] - Proxy request and response
* */
@Log4j2
@Service
public class ShProxy {
  public void x() throws IOException {
    ServerSocket serverSocket = new ServerSocket(2777);
    SessionData sessionData = new SessionData();

    // wait for incoming request
    Socket socket = serverSocket.accept();

    InputStream rqInputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(rqInputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String line = null;
    StringBuilder rawRequestBuilder = new StringBuilder();
    for(;;){
//      System.out.println("1");
      line = bufferedReader.readLine();
//      System.out.println("2");
      if(rawRequestBuilder.length()!=0){
        rawRequestBuilder.append(System.lineSeparator());
      }
      if(ObjectUtils.isEmpty(line)) {
        break;
      }else{
        ProxyUtils.extractHeader(sessionData,line);
//        System.out.println("-------->"+line+"<");
      }
      rawRequestBuilder.append(line);
//      System.out.println("3");
    }

    String rawRequest = rawRequestBuilder
        .append(System.lineSeparator())
        .append(System.lineSeparator())
        .toString();

    // forward request to destination
//    String host = sessionData.getHeaderData().getHeaders().get()
//    int port = sessionData.getPort();
    String host = "";
    int port = 0;
    log.info("------------------ Host:Port ----------------");
    log.info("{}:{}",host,port);

    // Create SSLSocket
    host = "www.google.com";
    port = 443;
    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket socket2 = (SSLSocket) factory.createSocket(host, port);

//    Socket socket2 = new Socket("www.google.com",443);

    OutputStream outputStream2 = socket2.getOutputStream();

    outputStream2.write(rawRequest.getBytes());
    outputStream2.flush();
//    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(outputStream2);
//    BufferedWriter bufferedWriter2 = new BufferedWriter(outputStreamWriter2);
//
//    bufferedWriter2.write(rawRequest);
//    bufferedWriter2.flush();

    InputStream inputStream2 = socket2.getInputStream();
    InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream2);
    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);

    log.warn("-------------------------");

    String inputLine;
    while ((inputLine = bufferedReader2.readLine()) != null) {
      log.warn(inputLine);
    }
//
//    StringBuilder rawRequestBuilder2 = new StringBuilder();
//    for(;;){
////      System.out.println("1");
//      line = bufferedReader2.readLine();
////      System.out.println("2");
//      if(rawRequestBuilder2.length()!=0){
//        rawRequestBuilder2.append(System.lineSeparator());
//      }
//      if(ObjectUtils.isEmpty(line)) {
//        break;
//      }else{
//        ProxyUtils.extractHeader(sessionData,line);
////        System.out.println("-------->"+line+"<");
//      }
//      rawRequestBuilder2.append(line);
////      System.out.println("3");
//    }
    log.warn("-------------------------");
//    log.warn(rawRequestBuilder2.toString());



    ProxyUtils.close(
        outputStream2,

        bufferedReader2,
        inputStreamReader2,
        inputStream2,

        socket2
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
