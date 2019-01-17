import core.chain.Stage;

@Stage(ID = "2")
public class Stage2 {

    public Object invoke(Object in) {
        System.out.println("Stage2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
