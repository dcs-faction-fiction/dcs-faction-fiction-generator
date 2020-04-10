
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Function;

public class FileParser {

  BufferedInputStream bin;

  private FileParser(BufferedInputStream bin) {
    this.bin = bin;
  }

  public static FileParser open(String path) {
    InputStream in = FileParser.class.getClassLoader().getResourceAsStream(path);
    BufferedInputStream bin = new BufferedInputStream(in);

    return new FileParser(bin);
  }

  private boolean advanceUntil(String s, Consumer<Character> charEvent) throws IOException {
    char[] chars = s.toCharArray();
    int size = chars.length;
    char c;
    int indexCheck = 0;
    while (bin.available() > 0) {
      c = (char) bin.read();
      indexCheck = c == chars[indexCheck] ? indexCheck + 1 : 0;
      if (indexCheck == size) {
        return true;
      } else if (charEvent != null) {
        charEvent.accept(c);
      }
    }
    return false;
  }

  public <T> T nextValueOfKey(String key, Function<String, T> transformer) throws IOException {
    if (!advanceUntil("[\""+key+"\"] = ", null)) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    advanceUntil(",", sb::append);
    return transformer.apply(sb.toString().trim().replaceAll("\"", ""));
  }
}