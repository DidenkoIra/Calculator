package mcarb.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mcarb.viewmodel.ViewModelFactory;

public class ViewHandler {

    private Stage stage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    public void start() throws  Exception {
        openView();
    }

    public void openView() throws Exception {
        Scene scene = null;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/form.fxml"));
        root = loader.load();

        Controller view = loader.getController();
        view.init(viewModelFactory.getViewModel());

        stage.setTitle("SmartCalc");
        scene = new Scene(root);
        String css = this.getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);

        stage.setOnCloseRequest(windowEvent -> saveHistory(view));

        stage.show();
    }

    private void saveHistory(Controller controller) {
        controller.saveHistory();
    }
}
