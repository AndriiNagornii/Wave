import core.chain.Stage;

@Stage(ID = "3", chainName = "Test")
public class StageTest2 {

    public Object invoke(Object in) {
        Integer i = (Integer) in;
        i++;
        return i;
    }

}
