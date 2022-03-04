public class Cache {
  private int blockSizeInWord;
  private int blockSizeInBits;
  private int sizeInWord; // count in word
  private int associativity;
  private int[][] mem;
  private int maxIndex;
  private int miss = 0;
  private int hit = 0;
  private final int LRU;
  private static final int WORD_PER_KB = 256;
  private static final int BITS_PER_WORD = 32;
  private static final int BYTE_PER_WORD = 4;

  /**
   * initialize the cache with all zeros
   * 
   * @param size the size of cache(KB)
   * @param blockSize number of words stored in each block
   * @param associativity parallel number in the cache
   */
  public Cache(int sizeInKb, int blockSizeInWord, int associativity) {
    this.sizeInWord = sizeInKb * WORD_PER_KB;
    this.blockSizeInWord = blockSizeInWord;
    this.blockSizeInBits = blockSizeInWord * BITS_PER_WORD;
    this.associativity = associativity;
    this.LRU = associativity;
    this.maxIndex = this.sizeInWord / (this.blockSizeInWord * associativity);
    mem = new int[this.maxIndex][associativity + 1];
  }

  /**
   * return true if hit, false if miss
   * @param address address to be stored
   */
  public void store(int address) {
    int index = getIndex(address);
    //first fill in the empty places
    for (int i = 0; i < this.associativity; i++) {
      if (mem[index][i] == 0) {
        mem[index][i] = getStorageAddress(address);
        mem[index][LRU] = i;
        miss++;
        return;
      }
    }
    for (int i = 0; i < this.associativity; i++) {
      if (isWithinBlock(index, address, i)) {
        hit++;
        mem[index] [LRU] = i;
        return;
      } 
    }
    int LRU = mem[index][this.LRU];
    mem[index] [LRU] = getStorageAddress(address);
    miss++;
  }

  public void storeDirect(int address) {
    int index = getIndex(address);
    if (isWithinBlock(index, address)) {
      hit++;
    } else {
      miss++;
      mem[index][0] = getStorageAddress(address);
    }
  }

  /**
   * return the index for the address to be stored
   * address % index. Since index is a power of 2, use bit-wise calculation to reduce runtime
   * @param address 32 bit address
   * @return
   */
  private int getIndex(int address) {
    return address & (maxIndex - 1);
  }

  /**
   * return the address to be stored in the first row of cache. 
   * e.g if cache is 4-word, and 15 will be stored as 12, if 1-word, 15 will be stored as 15
   * 
   * @param address
   * @return address % blockSizeInWord * blockSizeInWord
   */
  private int getStorageAddress(int address) {
    return address - (address & (this.blockSizeInWord - 1));
  }

  private boolean isWithinBlock(int index, int address, int associative) {
    return address >= mem[index][associative] && address < mem[index][associative] + blockSizeInBits;
  }

  private boolean isWithinBlock (int index, int address) {
    return address >= mem[index][0] && address < mem[index][0] + blockSizeInBits;
  }

  public void printCache(int num) {
    System.out.printf("Cache #%d\nCache size: %dB\tAssociativity: %d\tBlock size: %d\nHits: %d \t Hit Rate: %.2f", 
      num, this.sizeInWord * BYTE_PER_WORD, this.associativity, this.blockSizeInWord, this.hit, 
      (double)hit / (hit + miss) * 100);
    System.out.println("%\n---------------------------");
  }
}
