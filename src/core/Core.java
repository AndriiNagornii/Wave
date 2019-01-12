package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Core {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    List<List<StageWrapper>> stages = new LinkedList<>();

    private static Core instance = new Core();

    private Core() {

    }

    public static Core getInstance() {
        return instance;
    }

    public void process() {
        for (List<StageWrapper> list : stages) {
            if (list.size() == 1) {
                list.get(0).invoke();
            } else {
                var futures = new ArrayList<Future>();
                for (StageWrapper sw : list) {
                    Future f = executor.submit(sw::invoke);
                    futures.add(f);
                }
                futures.forEach(f -> {
                    try {
                        f.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }



}
