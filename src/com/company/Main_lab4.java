package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main_lab4 {

    private static final Map<String, List<String>> indexMap = new HashMap<>();
    private static final List<Thread> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(Main.pattern);

        try {
            List<String> paths  = Files.find(Paths.get(args[0]),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .filter((path) -> path.toString().endsWith(".java"))
                    .map(Path::toString)
                    .collect(Collectors.toList());

            paths.stream()
                    .map(path -> {
                        Runnable runnable = () -> {
                            try {
                                Main_lab5.CollectMap(pattern, path, indexMap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                        Thread thread = new Thread(runnable);
                        tasks.add(thread);
                        return thread;
                    } )
                    .forEach(Thread::run);

            for (Thread task: tasks) {
                task.join();
            }
            indexMap.forEach((subClass, classes) ->
                    System.out.println(subClass + " => " + classes));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}