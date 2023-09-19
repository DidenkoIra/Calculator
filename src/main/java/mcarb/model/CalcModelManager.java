package mcarb.model;

public class CalcModelManager implements CalcModel {

    static {
        System.loadLibrary("smartcalc");
    }

    public CalcModelManager() {}
    native public String calcResult(String expression, String expressionX);
    native public double[][] calcGraphData(String expression, double xBegin, double xEnd);

}
