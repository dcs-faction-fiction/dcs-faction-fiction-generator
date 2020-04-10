
import java.io.IOException;

public class GenerateParkings {
  public static void main(String[] args) throws IOException {
    var parser = FileParser.open("CAUCASUS/airfield_parkings");

    Integer id;
    while((id = parser.nextValueOfKey("airdromeId", Integer::valueOf)) != null) {
      String parking = parser.nextValueOfKey("parking", s -> s);
      String parkingId = parser.nextValueOfKey("parking_id", s -> s);
      double x = parser.nextValueOfKey("x", Double::valueOf);
      double y = parser.nextValueOfKey("y", Double::valueOf);

      System.out.println(id + "=" + parking + "/" + parkingId + ": " + x + ",0" + y);
    }
  }
}
