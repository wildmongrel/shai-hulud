package co.pipat.shai_hulud.feature.proxy.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
public class SessionData {
  private HeaderData headerData;

  @Data
  @Accessors(chain = true)
  public static class HeaderData {
    private List<Header> headers = new ArrayList<>();
    private List<String> rawHeaders = new ArrayList<>();
  }

  @Data
  @Accessors(chain = true)
  public static class Header {
    private String host;
    private int port;
  }
}
