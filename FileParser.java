import java.io.InputStream;
import java.util.Scanner;

public class FileParser {
  private InputStream stream;

  public FileParser (InputStream stream) {
    this.stream = stream;
  }

  public void readAll() {
    Scanner scanner = new Scanner(stream);
    Cache c1 = new Cache(2, 1, 1);
    Cache c2 = new Cache(2, 1, 2);
    Cache c3 = new Cache(2, 1, 4);
    Cache c4 = new Cache(2, 2, 1);
    Cache c5 = new Cache(2, 4, 1);
    Cache c6 = new Cache(2, 4, 4);
    Cache c7 = new Cache(4, 1, 1);
    while (scanner.hasNext()) {
      scanner.next();
      int addr = Integer.parseInt(scanner.next(), 16);
      c1.store(addr);
      c2.store(addr);
      c3.store(addr);
      c4.store(addr);
      c5.store(addr);
      c6.store(addr);
      c7.store(addr);
    }
    scanner.close();
  }
}