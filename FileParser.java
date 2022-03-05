import java.io.InputStream;
import java.util.Scanner;

public class FileParser {
  private InputStream stream;

  public FileParser (InputStream stream) {
    this.stream = stream;
  }

  public void readAll() {
    Scanner scanner = new Scanner(stream);
    Cache c1 = new DirectCache(2, 1);
    Cache c2 = new DirectCache(2, 2);
    Cache c3 = new DirectCache(2, 4);
    Cache c4 = new AssociativeCache(2, 2, 1);
    Cache c5 = new AssociativeCache(2, 4, 1);
    Cache c6 = new AssociativeCache(2, 4, 4);
    Cache c7 = new DirectCache(4, 1);
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
    c1.printCache(1);
    c2.printCache(2);
    c3.printCache(3);
    c4.printCache(4);
    c5.printCache(5);
    c6.printCache(6);
    c7.printCache(7);
  }
}