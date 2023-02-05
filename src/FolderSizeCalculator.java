import org.w3c.dom.Node;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {


    private Node node;

    public FolderSizeCalculator(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        File folder;
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        List<FolderSizeCalculator> subTask = new LinkedList<>();
        File[] files = folder.listFiles();
        for (File file : files) {
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork();
            subTask.add(task);
        }
        for (FolderSizeCalculator task : subTask) {
            sum += task.join();
        }
//        node.setSize(sum);
        return sum;
    }
}
