import core.chain.Stage;

@Stage(ID = "0")
public class Stage0 {

    public Object invoke(Object in) {
        System.out.println("Stage0");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}


