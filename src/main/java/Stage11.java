import core.chain.Stage;

@Stage(ID = "1")
public class Stage11 {

    public Object invoke(Object in) {
        System.out.println("Stage11");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
