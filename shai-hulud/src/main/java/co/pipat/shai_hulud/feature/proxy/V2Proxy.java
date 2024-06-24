package co.pipat.shai_hulud.feature.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Log4j2
@Service
public class V2Proxy {
  public void x() {
    try {
      String host = "www.google.com";
      int port = 443;

      try {
        // Create SSLSocket
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        StringBuilder rawRequestBuilder = new StringBuilder();

        // Send HTTP GET request to google.com
        rawRequestBuilder.append("GET / HTTP/1.1").append(System.lineSeparator());
        rawRequestBuilder.append("Host: " + host).append(System.lineSeparator());
        rawRequestBuilder.append("Connection: close").append(System.lineSeparator());
        rawRequestBuilder.append(System.lineSeparator());

        writeFile("C:/tmp/com/sent.txt",rawRequestBuilder.toString());
        String rawRequest = readEntireFile("C:/tmp/com/sent.txt");
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(rawRequest.getBytes());
        outputStream.flush();

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          System.out.println(inputLine);
        }

        // Close the socket
        socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }


//      String content = "xtexst\nasdasdas\n\n\nasdasd";
//      writeFile("C:/tmp/com/sent.txt",content);
//      readEntireFile("C:/tmp/com/sent.txt");
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  private String readEntireFile(String absPath) throws IOException {
    File file = new File(absPath);
    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    StringBuilder content = new StringBuilder();
    String line;
    for(;;){
      line = bufferedReader.readLine();
      if(content.length()!=0){
        content.append(System.lineSeparator());
      }
      if(line==null) {
        break;
      }
      content.append(line);
    }
    return content.toString();
  }

  private void writeFile(String absPath, String content) throws IOException {
    File file = new File(absPath);
    FileWriter fileWriter = new FileWriter(file);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    bufferedWriter.write(content);
    bufferedWriter.flush();
    bufferedWriter.close();
    fileWriter.close();
  }
}
