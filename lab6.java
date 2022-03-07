import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

public class lab6 {
  public static void main(String args[]) {
    File file = new File(args[0]);
    try (InputStream stream = new FileInputStream(file)) {
      FileParser parser = new FileParser(stream);
      parser.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
