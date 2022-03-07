public class AssociativeCache extends Cache {
  private int[][] mem;
  private int[][] LRUTable;
  private int associativity;
  private int LRUHist = 0;

  public AssociativeCache(int sizeInKb, int associativity, int blockSizeInWord) {
    super (sizeInKb, blockSizeInWord, associativity);
    super.setAssociativity(associativity);
    this.LRUTable = new int[super.getTotalIndex()][super.getAssociativity()];
    this.mem = new int[super.getTotalIndex()][super.getTotalIndex()];
    this.associativity = super.getAssociativity();
  }

  public void store(int address) {
    int tag = getTag(address);
    int index = getIndex(address);
    for (int i = 0; i < associativity; i++) {
      if (mem[index][i] == tag) {
        LRUTable[index][i] = LRUHist;
        LRUHist++;
        addHit();
        return;
      }
    }
    for (int i = 0; i < associativity; i++) {
      if (mem[index][i] == 0) {
        mem[index][i] = tag;
        LRUTable[index][i] = LRUHist;
        LRUHist++;
        addMiss();
        return;
      }
    }
    int minLRUIndex = 0;
    for (int i = 1; i < associativity; i++) {
      if (LRUTable[index][i] < LRUTable[index][minLRUIndex]) {
        minLRUIndex = i;
      }
    }
    mem[index][minLRUIndex] = tag;
    LRUTable[index][minLRUIndex] = LRUHist;
    LRUHist++;
    addMiss();
  }

}
