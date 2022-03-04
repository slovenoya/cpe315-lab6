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
      c1.storeDirect(addr);
      c2.storeDirect(addr);
      c3.storeDirect(addr);
      c4.store(addr);
      c5.store(addr);
      c6.store(addr);
      c7.storeDirect(addr);
    }
    scanner.close();
    c1.printCache(1);
    c2.printCache(2);
    c3.printCache(3);
    c4.printCache(4);
    c5.printCache(5);
    c6.printCache(6);
    c7.printCache(7);
  }
}