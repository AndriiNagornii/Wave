package core;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class SequenceBuilder {

    private Core core = Core.getInstance();

    public void build() {
        try {
            var stagePathes = findStages();

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            List<StageWrapper> stageWrappers = new LinkedList<>();
            for (String name : stagePathes) {
                Class cl = classLoader.loadClass(name);
                Object machine = cl.getDeclaredConstructors()[0].newInstance();

                var m = (Stage) cl.getAnnotation(Stage.class);

                var stageWrapper = new StageWrapper(machine, m.ID());
                stageWrappers.add(stageWrapper);
            }

            sortMachineWrappers(stageWrappers);

            mergeMachineWrappers(stageWrappers);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException();
        }
    }



    private void sortMachineWrappers(List<StageWrapper> stageWrappers) {
        Collections.sort(stageWrappers);
    }

    private void mergeMachineWrappers(List<StageWrapper> stageWrappers) {
        for (StageWrapper stageWrapper : stageWrappers) {
            if (!findAndAdd(stageWrapper)) {
                var newList = new LinkedList<StageWrapper>();
                newList.add(stageWrapper);
                core.stages.add(newList);
            }
        }
    }

    private boolean findAndAdd(StageWrapper stageWrapper) {
        for (List<StageWrapper> list : core.stages) {
            if (list.get(0).getNumber().equals(stageWrapper.getNumber())) {
                list.add(stageWrapper);
                return true;
            }
        }
        return false;
    }

    /**
     * TODO use priority queue
     */
    private Collection<String> findStages() {
        String path = "/home/andrey/IdeaProjects/Test2/src/sequence_machine";

        File[] files = new File(path)
                .listFiles((dir, name) -> name.contains("Stage"));

        assert files != null;

        return Arrays
                .stream(files)
                .map(File::getName)
                .map(name -> name.split("\\.")[0])
                .map(name -> "sequence_machine." + name)
                .collect(Collectors.toList());
    }

    public Core getCore() {
        return core;
    }

}

