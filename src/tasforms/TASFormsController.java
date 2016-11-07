/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package tasforms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**

 @author Mark Ferguson
 */
public class TASFormsController implements Initializable {

  @FXML
  private ComboBox<String> cb;
  @FXML
  private ImageView iv;
  ObservableList<String> oal = FXCollections.observableArrayList();
  private Image img;
  private Label label;
  //<editor-fold defaultstate="collapsed" desc="private String[] listing">
  private String[] pages = {
    "098",
    "106",
    "117",
    "142",
    "250",
    "251",
    "270",
    "302",
    "303",
    "350",
    "351",
    "354",
    "355",
    "356",
    "357",
    "425",
    "438",
    "439",
    "440",
    "441",
    "442",
    "443",
    "444",
    "449",
    "450",
    "451",
    "452",
    "453",
    "465",
    "466",
    "467",
    "468",
    "469",
    "470",
    "471",
    "472",
    "473",
    "474",
    "475",
    "476",
    "546",
    "547",
    "564",
    "585"
  };

  //</editor-fold>
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    String string = getClass().getResource("pics/098.png").toString();
    img = new Image(string);
    iv.setImage(img);
    iv.setVisible(true);
    for (int i = 0; i < pages.length; i++) {
      oal.add(pages[i].toString());
      cb.getItems().add(pages[i].toString());
    }
  }

  @FXML
  private void showPage(ActionEvent event) {
    String string = getClass().getResource("pics/").toString() + cb.getValue() + ".png";
    System.out.println(string);
    img = new Image(string);
    iv.setImage(img);
  }

}
