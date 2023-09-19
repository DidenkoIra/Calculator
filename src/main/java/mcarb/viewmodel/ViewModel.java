package mcarb.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mcarb.model.CalcModel;

public class ViewModel {

    private CalcModel calcModel;

    private StringProperty expression;
    private StringProperty x;
    private StringProperty calcResult;
    private StringProperty xMin;
    private StringProperty xMax;

    public ViewModel(CalcModel calcModel) {
        this.calcModel = calcModel;
        calcResult = new SimpleStringProperty("0");
        x = new SimpleStringProperty("");
        expression = new SimpleStringProperty("");
        xMin = new SimpleStringProperty("-5");
        xMax = new SimpleStringProperty("5");
    }

    public void updateResult() {
        calcResult.set(calcModel.calcResult(expression.get(), x.get()));
    }

    public double[][] updateGraph() {
        return calcModel.calcGraphData(expression.get(),
                Double.parseDouble(xMin.get()),
                Double.parseDouble(xMax.get()));
    }

    public StringProperty calcResult() {
        return calcResult;
    }

    public StringProperty x() {
        return x;
    }

    public StringProperty expression() {
        return expression;
    }

    public StringProperty xMin() {return xMin;}

    public StringProperty xMax() {return xMax;}

}
