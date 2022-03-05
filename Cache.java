public abstract class Cache {
  private static final int WORD_PER_KB = 256;
  private static final int BYTE_PER_WORD = 4;
  private static final int BYTES_PER_KB = WORD_PER_KB * BYTE_PER_WORD;
  private int totalIndex;
  private int blockSizeInWord;
  private int sizeInKb;
  private int tagShift;
  private int associativity = 1;
  private int hit = 0;
  private int miss = 0;
  private int[] mem;

  public abstract void store(int address);

  /**
   * initialize the cache with all zeros
   * 
   * @param size the size of cache(KB)
   * @param blockSize number of words stored in each block
   * @param associativity parallel number in the cache
   */
  public Cache(int sizeInKb, int blockSizeInWord) {
    this.sizeInKb = sizeInKb;
    this.blockSizeInWord = blockSizeInWord;
    this.totalIndex = this.sizeInKb * WORD_PER_KB / (this.blockSizeInWord * associativity);
    this.mem = new int[totalIndex];
    this.tagShift  = 2 + getExponent(this.blockSizeInWord) + getExponent(totalIndex);
  }

  public boolean withinBlock(int index, int tag) {
    return mem[index] == tag;
  }

  public int getTag(int address) {
    address = address >> tagShift;
    return address;
  }

  private static int getExponent(int num) {
    int result = 0;
    while (num != 0) {
      num  = num >> 1;
      result++;
    }
    return result - 1;
  }

  public void setAssociativity(int associativity) {
    this.associativity = associativity;
  }

  public void addHit() {
    hit++;
  }

  public void addMiss() {
    miss++;
  }

  public int getIndex() {
    return this.totalIndex;
  }

  public int getAssociativity() {
    return this.associativity;
  }

  public void printCache(int num) {
    int sizeInByte = BYTES_PER_KB * sizeInKb;
    System.out.printf("Cache #%d\nCache size: %dB\tAssociativity: %d\tBlock size: %d\nHits: %d \t Hit Rate: %.2f", 
      num, sizeInByte, this.associativity, this.blockSizeInWord, this.hit, 
      (double)hit / (hit + miss) * 100);
    System.out.println("%\n---------------------------");
  }
}
