import core.Stage;

@Stage(ID = "1")
public class Stage1 {

    public Object invoke(Object in) {
        System.out.println("Stage1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
