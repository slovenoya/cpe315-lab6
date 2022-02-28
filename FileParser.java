import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class FileParser {
  private InputStream stream;

  public FileParser (InputStream stream) {
    this.stream = stream;
  }

  public void readAll() {
    Scanner scanner = new Scanner(stream);
    while (scanner.hasNext()) {
      scanner.next();
      Integer.parseInt(scanner.next(), 16);
    }
    scanner.close();
  }

  public static void main(String args[]) {
    File file = new File("tests/mem_stream.1");
    try (InputStream stream = new FileInputStream(file)) {
      FileParser parser = new FileParser(stream);
      parser.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}