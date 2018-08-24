package server;

import org.oasisopen.sca.annotation.Reference;

public class Calculator implements ICalculator {
    private IAdd add;

    public IAdd getAdd() {
        return add;
    }

    @Reference
    public void setAdd(IAdd add) {
        this.add = add;
    }


    @Override
    public double add(double n1, double n2) {
        return this.add.add(n1, n2);
    }
}
