package mcarb.model;

public interface CalcModel {
    String calcResult(String expression, String expressionX);
    double[][] calcGraphData(String expression, double xBegin, double xEnd);
}
