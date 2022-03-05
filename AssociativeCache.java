public class AssociativeCache extends Cache {
  private int[][] LRUTable;
  
  public AssociativeCache(int sizeInKb, int blockSizeInWord, int associativity) {
    super (sizeInKb, blockSizeInWord);
    super.setAssociativity(associativity);
    this.LRUTable = new int[super.getIndex()][super.getAssociativity()];
  }

  public void store(int address) {

  }

}
