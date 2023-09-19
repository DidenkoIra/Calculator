package mcarb;

import javafx.application.Application;
import javafx.stage.Stage;
import mcarb.model.CalcModelFactory;
import mcarb.view.ViewHandler;
import mcarb.viewmodel.ViewModelFactory;

public class SmartCalcApp extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        CalcModelFactory modelFactory = new CalcModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
        viewHandler.start();
    }
}
