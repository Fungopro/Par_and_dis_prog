package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {

    static final String pattern = "(.*?)(class)(?<child>.*?)(extends)(\\s)(?<parent>\\w+)(.*?)";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(Main.pattern);

        try {
            Files.find(Paths.get(args[0]),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .filter((path) -> path.toString().endsWith(".java"))
                    .map(Path::toString)
                    .collect(Collectors.toList())
                    .stream()
                    .map(s -> {
                        try {
                            return Files.readAllLines(Paths.get(s));
                        } catch (IOException e) {
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .map(strings ->  strings.stream()
                            .filter(s -> pattern.matcher(s).matches())
                            .map(s -> {
                                Matcher match = pattern.matcher(s);
                                match.find();
                                return match.group("parent") + " : " + match.group("child");
                            })
                    )
                    .forEach(stringStream -> stringStream.forEach(System.out::println));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}