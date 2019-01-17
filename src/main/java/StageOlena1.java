import core.chain.Stage;

@Stage(ID = "1", chainName = "olena")
public class StageOlena1 {

    public Object invoke(Object o) {
        String i = (String) o;
        return i.replaceAll("3", "*");
    }


}
