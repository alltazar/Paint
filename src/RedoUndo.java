import java.util.ArrayList;

public class RedoUndo {

    private static ArrayList<Items> list = new ArrayList<>();
    private static int count;


    public static void unDo(SwingTest.DataModel data) {

        if (data.getAllShapes().size() != count) {
            list.clear();
        }

        if (data.getAllShapes().size() != 0) {

            ArrayList<Items> s = data.getAllShapes();

            list.add(s.get(s.size() - 1));
            data.unDo();

            count = s.size();

        }
    }

    public static void reDo(SwingTest.DataModel data) {

        ArrayList<Items> s = data.getAllShapes();

        if (count == (s.size()) && list.size() != 0) {
            data.reDo(list.get(list.size() - 1));
            list.remove(list.size() - 1);
            count = s.size();
        } else {
            list.clear();
        }
    }

}
