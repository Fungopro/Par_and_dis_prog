package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main_lab5 {

    private static final Map<String, List<String>> indexMap = new HashMap<>();
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(PROCESSORS + 1);
    private static final List<Future> futureList = new ArrayList<>();

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(Main.pattern);

        try {
            List<String> paths = Files.find(Paths.get(args[0]),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .filter((path) -> path.toString().endsWith(".java"))
                    .map(Path::toString)
                    .collect(Collectors.toList());

            paths.stream()
                    .map(path -> {
                        Runnable task = () -> {
                            try {
                                CollectMap(pattern, path, indexMap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                        return (Future) executor.submit(task);
                    })
                    .forEach(futureList::add);

            futureList.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            executor.shutdownNow();

            indexMap.forEach((subClass, classes) ->
                    System.out.println(subClass + " => " + classes));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void CollectMap(Pattern pattern, String path, Map<String, List<String>> indexMap) throws IOException {
        Files.readAllLines(Paths.get(path))
                .stream()
                .filter(s -> pattern.matcher(s).matches())
                .forEach(s -> {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        String childClass = matcher.group("child").trim();
                        String parentClass = matcher.group("parent").trim();

                        synchronized (indexMap) {
                            if (indexMap.containsKey(parentClass)) {
                                indexMap.get(parentClass).add(childClass);
                            } else {
                                indexMap.putIfAbsent(parentClass,
                                        new ArrayList<>(Collections.singletonList(childClass)));
                            }
                        }
                    }
                });
    }
}