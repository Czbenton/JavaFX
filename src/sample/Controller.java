package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, ChangeListener {

    @FXML
    TextField addressBar;
    @FXML
    WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Worker worker = webView.getEngine().getLoadWorker();    //boiler plate
        worker.stateProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        addressBar.setText(webView.getEngine().getLocation());
    }

    public void goBack(){
        try {
            webView.getEngine().getHistory().go(-1);    //boiler plate
        } catch (Exception e) {
            //Eat it
        }
    }

    public void goForward(){
        try {
            webView.getEngine().getHistory().go(1);
        } catch (Exception e) {

        }
    }

    public void goToURL(){
        String url = addressBar.getText();
        if(! url.startsWith("http")){
            url = "http://" + url;
        }
        webView.getEngine().load(url);
    }

    public void onKeyPressed(KeyEvent event) {      //boiler plate
        if (event.getCode() == KeyCode.ENTER) {
            goToURL();
        }
    }


}

