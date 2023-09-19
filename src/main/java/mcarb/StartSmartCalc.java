package mcarb;

import javafx.application.Application;

public class StartSmartCalc {

    public static void main(String[] args) {
      try {
            Application.launch(SmartCalcApp.class);
      } catch (Exception exception) {
          System.err.println("error opening app");
      }
    }

}