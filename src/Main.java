import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//
//        thread.start();
//        thread2.start();





        String folderpath = "F:\\ОБРАЗЫ";
        File file = new File(folderpath);

        long start = System.currentTimeMillis();
//        System.out.println(getFolderSize(file));

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);

        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");

    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}