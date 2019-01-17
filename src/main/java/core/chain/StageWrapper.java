package core.chain;

import java.lang.reflect.InvocationTargetException;

//immutable
public class StageWrapper implements Comparable<StageWrapper> {
    private Object machine;
    private String number;
    private String chainName;

    public StageWrapper(Object machine, String number, String chainName) {
        this.machine = machine;
        this.number = number;
        this.chainName = chainName;
    }

    public Object invoke(Object in) {
        var cl = machine.getClass();
        try {
            var out = cl
                    .getMethod("invoke", Object.class)
                    .invoke(machine,in);
            return out;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    public Object getMachine() {
        return machine;
    }

    public String getNumber() {
        return number;
    }

    public String getChainName() {
        return chainName;
    }

    @Override
    public int compareTo(StageWrapper o) {
        var num0 = this.number;
        var num1 = o.getNumber();
        float num0AsFloat = Float.parseFloat(num0);
        float num1AsFloat = Float.parseFloat(num1);
        return (int) (num0AsFloat - num1AsFloat);
    }
}
