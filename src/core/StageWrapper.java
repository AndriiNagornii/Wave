package core;

import java.lang.reflect.InvocationTargetException;

//immutable
class StageWrapper implements Comparable<StageWrapper> {
    private Object machine;
    private String number;

    public StageWrapper(Object machine, String number) {
        this.machine = machine;
        this.number = number;
    }

    public void invoke() {
        Class cl = machine.getClass();
        try {
            cl
                    .getMethod("invoke", Object.class)
                    .invoke(machine,"test");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Object getMachine() {
        return machine;
    }

    public String getNumber() {
        return number;
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
