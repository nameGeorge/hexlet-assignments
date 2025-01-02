package exercise;

import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN

        // END
    }

    public static CompletableFuture<String> unionFiles(String path1, String path2, String targetPath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String content1 = Files.readString(Paths.get(path1));
                String content2 = Files.readString(Paths.get(path2));
                String content = content1 + content2;
                Files.writeString(Paths.get(targetPath), content, StandardOpenOption.CREATE);
                return content;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }
    // END
}


