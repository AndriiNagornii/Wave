import core.chain.Stage;

@Stage(ID = "0", chainName = "Test")
public class StageTest0 {

    public Object invoke(Object in) {
        Integer i = (Integer) in;
        i++;
        return i;
    }

}
