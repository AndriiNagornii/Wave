import core.Stage;

@Stage(ID = "3")
public class Stage30 {

    public Object invoke(Object in) {
        System.out.println("Stage30");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
