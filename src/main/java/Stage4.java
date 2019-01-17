import core.chain.Stage;

@Stage(ID = "4")
public class Stage4 {

    public Object invoke(Object in) {
        System.out.println("Stage4");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
