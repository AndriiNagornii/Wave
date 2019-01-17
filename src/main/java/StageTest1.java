import core.chain.Stage;

@Stage(ID = "1", chainName = "Test")
public class StageTest1 {

    public Object invoke(Object in) {
        Integer i = (Integer) in;
        i++;
        return i;
    }

}
