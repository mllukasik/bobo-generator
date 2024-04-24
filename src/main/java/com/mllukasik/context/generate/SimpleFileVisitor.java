package com.mllukasik.context.generate;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Consumer;
import java.util.function.Predicate;

class SimpleFileVisitor implements FileVisitor<Path> {
    private final Consumer<Path> pathConsumer;
    private final Predicate<Path> pathPredicate;

    public SimpleFileVisitor(Consumer<Path> pathConsumer) {
        this(pathConsumer, (p) -> true);
    }

    SimpleFileVisitor(Consumer<Path> pathConsumer, Predicate<Path> pathPredicate) {
        this.pathConsumer = pathConsumer;
        this.pathPredicate = pathPredicate;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return pathPredicate.test(dir) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (pathPredicate.test(file)) {
            pathConsumer.accept(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        throw new IOException("File visit failed", exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
}
