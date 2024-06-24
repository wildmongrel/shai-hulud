package co.pipat.shai_hulud.feature.proxy;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Log4j2
@NoArgsConstructor
public class V1Proxy {

  public static void openGoogle() {
    String host = "www.google.com";
    int port = 443;

    try {
      // Create SSLSocket
      SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

      // Send HTTP GET request to google.com
      OutputStream outputStream = socket.getOutputStream();
      outputStream.write("GET / HTTP/1.1\r\n".getBytes());
      outputStream.write(("Host: " + host + "\r\n").getBytes());
      outputStream.write("Connection: close\r\n".getBytes());
      outputStream.write("\r\n".getBytes());
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
  }

}
