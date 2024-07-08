package co.pipat.shai_hulud.feature.proxy.util;

import co.pipat.shai_hulud.feature.proxy.model.SessionData;
import com.google.common.net.HttpHeaders;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;

@Log4j2
public class ProxyUtils {
//  private static final String HOST = "HOST";

  public static void close(Closeable... closeables) {
    if(closeables==null){
      return;
    }

    for (Closeable closeable : closeables) {
      if(closeable==null){
        continue;
      }
      try {
        closeable.close();
      } catch (Exception e) {
        log.error(e);
      }
    }

  }

  public static void extractHeader(SessionData sessionData, String line) {
    if(StringUtils.isBlank(line)){
      return;
    }
    int colonIndex = line.indexOf(":");
    if(colonIndex>0){
      String headerKey = line.substring(0,colonIndex);
      String headerValue = line.substring(colonIndex+2);
      switch(headerKey){
        case HttpHeaders.HOST:
          sessionData.getHeaders().add(
              headerKey,
              headerValue
          );
          break;
        default:
//          sessionData.getHeaders().add(
//              headerKey,
//              headerValue
//          );

      }
    }
  }
}
