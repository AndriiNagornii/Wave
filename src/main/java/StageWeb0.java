import core.chain.Stage;

@Stage(ID = "1", chainName = "web")
public class StageWeb0 {

    public Object invoke(Integer i, String name) {

        System.out.println(i);
        System.out.println(name);

        return null;
    }


}
