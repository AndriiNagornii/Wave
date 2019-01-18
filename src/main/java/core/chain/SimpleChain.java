package core.chain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * work like priority queue
 */
public class SimpleChain implements Chain {

    private String name;
    private List<List<StageWrapper>> stageWrappers = new LinkedList<>();
    private ExecutorService executor;
    private Object out;

    public SimpleChain(String name, ExecutorService executor) {
        this.name = name;
        this.executor = executor;
    }

    public String getName() {
        return name;
    }

    public SimpleChain addStage(StageWrapper sw) {
        validateStage(sw);

        if (!findAndAdd(sw)) {

            int i;
            for (i = 0; i < stageWrappers.size(); i++) {
                if (stageWrappers.get(i).get(0).compareTo(sw) > 0) {
                    break;
                }
            }

            var newList = new LinkedList<StageWrapper>();
            newList.add(sw);
            stageWrappers.add(i, newList);
        }
        return this;
    }

    private boolean findAndAdd(StageWrapper stageWrapper) {
        for (List<StageWrapper> list : stageWrappers) {
            if (list.get(0).getNumber().equals(stageWrapper.getNumber())) {
                list.add(stageWrapper);
                return true;
            }
        }
        return false;
    }

    private void validateStage(StageWrapper sw) {
        if (!sw.getChainName().equalsIgnoreCase(name))
            throw new IllegalStateException();
    }

    public String getChainAsList() {
        return stageWrappers
                .stream()
                .flatMap(Collection::stream)
                .map(StageWrapper::getNumber)
                .collect(Collectors.joining(", "));
    }

    @Override
    public void push(Object ...o) {
        stageWrappers
                .get(0)
                .get(0)
                .invoke(o);
    }

//        var current = o;
//        for (List<StageWrapper> list : stageWrappers) {
//            if (list.size() == 1) {
//                current = list.get(0).invoke(current[0]);
//            }
//            else {
//                var futures = new ArrayList<Future>();
//                for (StageWrapper sw : list) {
//                    Future f = executor.submit(sw::invoke);
//                    futures.add(f);
//                }
//                futures.forEach(f -> {
//                    try {
//                        f.get();
//                    } catch (InterruptedException | ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        }
//        this.out = current;

    @Override
    public Object getSynch() {
        return out;
    }


}
