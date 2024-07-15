package co.pipat.shai_hulud.feature.playground;

public class PrettyLog {
  public static void main(String[] args) {
    System.out.println("\t:");
    System.out.println("----");
    System.out.println("---");
    System.out.println("--");
    System.out.println("-");
  }

  public static void right(String status, String left, String right) {
    System.out.println(String.format("---- %s ---- %s -> %s ----",status,left,right));
  }

  public static void left(String status, String left, String right) {
    System.out.println(String.format("---- %s ---- %s <- %s ----",status,left,right));
  }
}
