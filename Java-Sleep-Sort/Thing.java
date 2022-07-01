import java.util.concurrent.CountDownLatch;

class Thing {
  public static void sleepSortAndPrint(int[] nums) {
    final CountDownLatch doneSignal = new CountDownLatch(nums.length);

    for (final int num : nums) {
      new Thread(new Runnable() {
        public void run() {
          doneSignal.countDown();

          try {
            doneSignal.await();
            Thread.sleep(num * 1000);
            System.out.println(num);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
  }
  public static void main(String[] args) {
    int[] nums = new int[args.length];
    for (int i = 0; i < args.length; i++)
      nums[i] = Integer.parseInt(args[i]);
    sleepSortAndPrint(nums);
  }
}