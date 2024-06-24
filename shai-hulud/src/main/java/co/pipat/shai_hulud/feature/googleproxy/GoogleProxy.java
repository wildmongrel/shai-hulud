package co.pipat.shai_hulud.feature.googleproxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class GoogleProxy {
  private int localPortNo;
  private PrintWriter out;
  private BufferedReader in;

  public static GoogleProxy start(int localPortNo) throws Exception {
    GoogleProxy proxy = new GoogleProxy();
    String host = "https://www.google.com";
//    host = "172.217.26.68";
    host = "google.com";
    int port = 443;
    Socket socket = new Socket(host,port);
    log.info("socket.isConnected(): {}",socket.isConnected());
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    out.println("GET / HTTP/1.1");
//    out.println("Host: google.com");
//    out.println("User-Agent: curl/8.7.1");
//    out.println("Accept: */*");
    out.println("");
    out.println("\r\n\r\n");
    out.println("\n\n");
    out.flush();
    log.info("socket.isClosed(): {}",socket.isClosed());

    String rs = in.readLine();
    Thread.sleep(1000);
    log.info(rs);
    log.info(in.readLine());
    log.info(in.readLine());

    socket.close();
    return proxy;
  }

  public static void main(String[] args) {
    String host = "www.google.com";
    int port = 443;

    try {
      // Create SSLSocket
      SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

      // Send HTTP GET request to google.com
      OutputStream outputStream = socket.getOutputStream();
//      outputStream.write("GET / HTTP/1.1\r\n".getBytes());
//      outputStream.write(("Host: " + host + "\r\n").getBytes());
//      outputStream.write("Connection: close\r\n".getBytes());
//      outputStream.write("\r\n".getBytes());
      String template = "GET / HTTP/1.1\r\nHost: " + host + "\r\nConnection: close\r\n\r\n";
      outputStream.write(template.getBytes());
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
