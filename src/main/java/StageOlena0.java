import core.chain.Stage;

@Stage(ID = "0", chainName = "olena")
public class StageOlena0 {

    public Object invoke(Object o) {
        Integer i = (Integer) o;

        var res = String.valueOf(this.hashCode());

        return res;
    }

}
