package mcarb.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import mcarb.viewmodel.ViewModel;

import java.net.URL;
import java.util.StringTokenizer;
import java.util.prefs.Preferences;

public class Controller {

    @FXML
    TextField expressionField;
    @FXML
    TextField xField;
    @FXML
    Label labelResult;
    @FXML
    TextField xMinField;
    @FXML
    TextField xMaxField;
    @FXML
    TextField yMinField;
    @FXML
    TextField yMaxField;
    @FXML
    LineChart<Number, Number> chart;
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    ComboBox<String> historyBox;
    private ViewModel viewModel;

    private TextField currentField;
    private static final int MAX_LENGTH = 256;

    public Controller() { }

    private void addFocusListener(TextField textField) {
        textField.focusedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    currentField = textField;
                }
            }
        });
    }

    private void addLengthListener(TextField textField) {
        textField.lengthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (textField.getText().length() >= MAX_LENGTH) {
                        textField.setText(textField.getText().substring(0, MAX_LENGTH));
                    }
                }
            }
        });
    }

    private void addIntListener(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (textField.getText().length() >= 7) {
                    textField.setText(textField.getText().substring(0, 7));
                }
            }
        });
    }

    public void init(ViewModel viewModel) {
        this.viewModel = viewModel;
        currentField = null;
        addFocusListener(expressionField);
        addFocusListener(xField);
        addLengthListener(expressionField);
        addLengthListener(xField);
        addIntListener(xMinField);
        addIntListener(xMaxField);
        addIntListener(yMinField);
        addIntListener(yMaxField);
        viewModel.expression().bind(expressionField.textProperty());
        viewModel.x().bind(xField.textProperty());
        labelResult.textProperty().bind(viewModel.calcResult());
        viewModel.xMin().bind(xMinField.textProperty());
        viewModel.xMax().bind(xMaxField.textProperty());

        chart.setCreateSymbols(false);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        setAxisRange();
        loadHistory();
    }

    private void setAxisRange() {
        xAxis.setLowerBound(Double.parseDouble(xMinField.getText()));
        xAxis.setUpperBound(Double.parseDouble(xMaxField.getText()));
        yAxis.setLowerBound(Double.parseDouble(yMinField.getText()));
        yAxis.setUpperBound(Double.parseDouble(yMaxField.getText()));
    }

    public void onButtonClick(ActionEvent actionEvent) {
        String buttonName = ((Button) actionEvent.getSource()).getText();
        if (currentField != null) {
            currentField.setText(currentField.getText() + buttonName);
        }
    }

    public void onButtonACClick() {
        expressionField.setText("");
        xField.setText("");
    }

    public void onButtonFunctionClick(ActionEvent actionEvent) {
        String buttonName = ((Button) actionEvent.getSource()).getText();
        if (currentField != null) {
            currentField.setText(currentField.getText() + buttonName + "(");
        }
    }

    public void onButtonEqClick() {
        viewModel.updateResult();
        historyBox.getItems().add(expressionField.getText());
    }

    public void onButtonGraphClick() {
        double graphData[][] = viewModel.updateGraph();
        chart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<Number,Number>();
        if (graphData[0].length > 0) {
            series.setName("f(x) = " + expressionField.getText());
            for(int i = 0; i < graphData[0].length; i++) {
                series.getData().add(new XYChart.Data<>(graphData[0][i], graphData[1][i]));
            }
        } else {
            series.setName("f(x) = error!");
        }

        chart.getData().add(series);
        setAxisRange();
    }

    public void selectExpressionFromHistory() {
        String selectedValue = historyBox.getValue();
        expressionField.setText(selectedValue);
    }

    public void saveHistory() {
        Preferences preferences = Preferences.userRoot().node("SmartCalc3");

        ObservableList<String> items = historyBox.getItems();
        StringBuilder builder = new StringBuilder();
        for (String item : items) {
            builder.append(item);
            builder.append("\n");
        }
        preferences.put("HISTORY", builder.toString());
    }

    public void loadHistory() {
        Preferences preferences = Preferences.userRoot().node("SmartCalc3");
        String history = preferences.get("HISTORY", "");
        StringTokenizer stringTokenizer = new StringTokenizer(history, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            historyBox.getItems().add(stringTokenizer.nextToken());
        }
    }

    public void showHelpAbout() {
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        URL url = this.getClass().getResource("/help.html");
        String path = url.toExternalForm();
        if (path != null) {
            webEngine.load(path);
        }

        Stage stage = new Stage();
        VBox root = new VBox();
        root.setPadding(new Insets(1));
        root.setSpacing(1);
        root.getChildren().addAll(browser);

        Scene scene = new Scene(root);

        stage.setTitle("HELP");
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(300);

        stage.show();
    }

    public void clearHistory() {
        historyBox.getItems().clear();
    }
}
