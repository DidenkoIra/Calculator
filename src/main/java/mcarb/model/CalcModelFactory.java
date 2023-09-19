package mcarb.model;

public class CalcModelFactory {

    private CalcModel calcModel;

    public CalcModel getCalcModel() {
        if (calcModel == null) {
            calcModel = new CalcModelManager();
        }
        return calcModel;
    }
}
