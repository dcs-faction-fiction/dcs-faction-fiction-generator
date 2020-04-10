
import java.io.IOException;
import static java.lang.String.format;
import java.util.List;

public class GenerateParkings {
  public static void main(String[] args) throws IOException {
    var maps = List.of("CAUCASUS");

    for (String map: maps) {
      var parser = FileParser.open(map+"/airfield_parkings");

      Integer id;
      while((id = parser.nextValueOfKey("airdromeId", Integer::valueOf)) != null) {
        String parking = parser.nextValueOfKey("parking", s -> s);
        String parkingId = parser.nextValueOfKey("parking_id", s -> s);
        double x = parser.nextValueOfKey("x", Double::valueOf);
        double y = parser.nextValueOfKey("y", Double::valueOf);

//        System.out.println(format(
//          "insert into parkings (map, airdrome_id, parking, parking_id, x, y) "
//          + "values('%s', %d, %s, %s, %f, %f);",
//          map,
//          id,
//          parking,
//          parkingId,
//          x,
//          y
//          ));

        System.out.println(format(
          "addParking(%s, %d, %s, %s, %f, %f);",
          map,
          id,
          parking,
          parkingId,
          x,
          y
        ));
      }
    }
  }
}
