public class DirectCache extends Cache {
  int [] mem;
  public DirectCache (int sizeInKb, int blockSizeInWord)  {
    super (sizeInKb, blockSizeInWord, 1);
    this.mem = new int[super.getTotalIndex()];
  }

  public void store(int address) {
    int tag = getTag(address);
    int index = getIndex(address);
    if (mem[index] == tag) {
      super.addHit();
      return;
    }
    mem[index] = tag;
    super.addMiss();
  }

}