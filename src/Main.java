import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        String folderpath = "F:\\ОБРАЗЫ";
        long sizeLimit = 50 * 1024 * 1024;

        File file = new File(folderpath);
        Node root = new Node(file, sizeLimit);


        long start = System.currentTimeMillis();
//        System.out.println(getFolderSize(file));

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);

        System.out.println(root);

        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");
    }
}