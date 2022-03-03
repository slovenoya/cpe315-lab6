public class Cache {
  private int blockSize;
  private int sizeInWord; // count in word
  private int associativity;
  private int[][] mem;
  private int maxIndex;
  private static final int WORD_PER_KB = 256;
  private final int LDR;
  private static final int DEFAULT_MEM = -1;

  /**
   * initialize the cache with all zeros
   * 
   * @param size the size of cache(KB)
   * @param blockSize number of words stored in each block
   * @param associativity parallel number in the cache
   */
  public Cache(int sizeInKb, int blockSizeInWord, int associativity) {
    this.sizeInWord = sizeInKb * WORD_PER_KB;
    this.blockSize = blockSizeInWord * 32;
    this.associativity = associativity;
    this.LDR = associativity - 1;
    this.maxIndex = this.sizeInWord / (blockSizeInWord * associativity);
    mem = new int[this.maxIndex][associativity + 1];
    //initialize the memory
    for (int i = 0; i < mem.length; i++) {
      for (int j = 0; j < mem[0].length; j++) {
        mem[i][j] = DEFAULT_MEM;
      }
    }
  }

  /**
   * return true if hit, false if miss
   * @param address address to be stored
   * @return
   */
  public boolean store(int address) {

    return false;
  }

  private boolean isWithinBlock(int index, int addr) {
    for (int i = 0; i < associativity; i++) {
      if (addr >= mem[index][i] && addr < mem[index][i] + blockSize) {
        return true;
      }
    }
    return false;
  }

}
