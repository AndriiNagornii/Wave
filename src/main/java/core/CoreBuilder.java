package core;

import core.chain.Stage;
import core.chain.StageWrapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CoreBuilder {

    private Core core = new Core();

    public CoreBuilder build() {
        try {
            var stagePathes = findStages();

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            List<StageWrapper> stageWrappers = new LinkedList<>();
            for (String name : stagePathes) {

                var cl = classLoader.loadClass(name);

                var stage = cl.getDeclaredConstructors()[0].newInstance();

                var m = (Stage) cl.getAnnotation(Stage.class);

                var stageWrapper = new StageWrapper(stage, m.ID(), m.chainName());

                core.addStage(stageWrapper);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | IOException e) {
            throw new IllegalStateException(e);
        }
        return this;
    }


    private Collection<String> findStages() throws IOException {
//        Path path = Paths.get("").toAbsolutePath();
//        Path path = Path

//        System.out.println("Current relative path is: " + path);
//
//        Files.find(path, 100,
//                (p, basicFileAttributes) -> {
//                    File file = p.toFile();
//                    return !file.isDirectory() &&
//                            file.getName().contains("Stage");
//                });



        File[] files = new File("/home/andrey/Projects/Wave/src/main/java")
                .listFiles((dir, name) -> name.contains("Stage"));

        assert files != null;

        return Arrays
                .stream(files)
                .map(File::getName)
                .map(name -> name.split("\\.")[0])
                .collect(Collectors.toList());
    }



    public Core getCore() {
        return core;
    }

}

