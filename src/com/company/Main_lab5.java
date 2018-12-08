package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main_lab5 {

    private static final String REGEX_PATTERN_STRING = "(.*?)(class|interface)(?<child>.*?)(extends|implements)(\\s)(?<parent>\\w+)(.*?)";
    private static final Map<String, List<String>> extendedClassesInverseIndexMap = new HashMap<>();
    private static final int processorsCount = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(processorsCount + 1);
    private static final List<Future> futureList = new ArrayList<>();

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX_PATTERN_STRING);
        long start = System.nanoTime();

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
                                String timeStamp = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss:SSS")
                                        .format(Calendar.getInstance().getTime());
                                timeStamp = timeStamp + ":" + System.nanoTime();
                                System.out.println(timeStamp + " " + Thread.currentThread().getName());
                                CollectMap(pattern, path, extendedClassesInverseIndexMap);
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

            extendedClassesInverseIndexMap.forEach((subClass, classes) ->
                    System.out.println(subClass + " => " + classes));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        long finish = System.nanoTime();
        long timeConsumedNano = finish - start;
        System.out.println("\n\n\nExecution time:");
        System.out.println(timeConsumedNano + " * 10^-9 s.\n(digits: " + String.valueOf(timeConsumedNano).length() + ")");
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