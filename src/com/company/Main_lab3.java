package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main_lab3 {

    private static final String REGEX_PATTERN_STRING = "(.*?)(class)(?<child>.*?)(extends)(\\s)(?<parent>\\w+)(.*?)";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX_PATTERN_STRING);

        long start = System.nanoTime();

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
                                Files.readAllLines(Paths.get(path))
                                        .stream()
                                        .filter(s -> pattern.matcher(s).matches())
                                        .map(s -> {
                                            Matcher match = pattern.matcher(s);
                                            match.find();
                                            return match.group("parent") + " : " + match.group("child");
                                        })
                                        .forEach(System.out::println);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                        return new Thread(runnable);
                    } )
                    .forEach(Thread::run);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}