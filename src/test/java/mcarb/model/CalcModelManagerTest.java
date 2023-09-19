package mcarb.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalcModelManagerTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/test_data.csv")
    void calcResultExpression(String expression, String result) {
        String x = "";
        CalcModelManager calcModelManager = new CalcModelManager();
        assertEquals(calcModelManager.calcResult(expression, x), result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data_withx.csv")
    void calcResultExpressionWithX(String expression, String x, String result) {
        CalcModelManager calcModelManager = new CalcModelManager();
        assertEquals(calcModelManager.calcResult(expression, x), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"...", "5//9", "sin(", "2x", "a"})
    void calcResultError(String expression) {
        String x = "";
        String result = "Error!";
        CalcModelManager calcModelManager = new CalcModelManager();
        assertEquals(calcModelManager.calcResult(expression, x), result);
        assertEquals(calcModelManager.calcResult(x, expression), result);
    }

    @Test
    void calcGraphData() {
        CalcModelManager calcModelManager = new CalcModelManager();
        double[][] result = calcModelManager.calcGraphData("x", -1, 1);
        assertArrayEquals(result[0], result[1]);
    }
}
