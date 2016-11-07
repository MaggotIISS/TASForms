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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**

 @author Mark Ferguson
 */
public class TASFormsController implements Initializable {

  @FXML
  private ComboBox<String> cb;
  @FXML
  private CheckBox check;
  @FXML
  private ImageView iv;
  ObservableList<String> oal = FXCollections.observableArrayList();
  private Image img;
  private Label label;
  //<editor-fold defaultstate="collapsed" desc="private String[] forms">
  private String[] forms = {
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
  //<editor-fold defaultstate="collapsed" desc="private String[] masters">
  private String[] masters = {
    "39",
    "40",
    "41",
    "42",
    "46",
    "47",
    "48",
    "50",
    "53",
    "63",
    "66",
    "67",
    "68",
    "70",
    "77",
    "97",
    "116",
    "117",
    "119",
    "120",
    "169",
    "197",
    "198",
    "199",
    "206",
    "214",
    "215",
    "216",
    "217",
    "218",
    "219",
    "220",
    "221",
    "222",
    "223",
    "229",
    "241",
    "242",
    "243",
    "244",
    "245",
    "246"
  };
  //</editor-fold>
  private String string;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    String stringa = getClass().getResource("pics/forms/098.png").toString();
    img = new Image(stringa);
    iv.setImage(img);
    iv.setVisible(true);
    for (int i = 0; i < forms.length; i++) {
      oal.add(forms[i].toString());
      cb.getItems().add(forms[i].toString());
    }
    cb.getSelectionModel().select(0);
  }

  @FXML
  private void checkChange(ActionEvent event) {
    if (check.isSelected()) {
      setCombo("Master");
      string = getClass().getResource("pics/").toString() + "Master/" + cb.getValue() + ".png";
    } else {
      setCombo("Forms");
      string = getClass().getResource("pics/").toString() + "Forms/" + cb.getValue() + ".png";
    }
    System.out.println(string);
    img = new Image(string);
    iv.setImage(img);
  }

  private void setCombo(String foldername) {
    String[] strings = null;
    switch (foldername) {
      case "Master": {
        strings = masters;
        break;
      }
      case "Forms": {
        strings = forms;
        break;
      }
      default: {
        JOptionPane.showMessageDialog(null, this, "OOOOOOOOOOOOO", 0);
      }
    }
    cb.getItems().clear();
    for (int i = 0; i < strings.length; i++) {
      oal.add(strings[i].toString());
      cb.getItems().add(strings[i].toString());
    }
    cb.getSelectionModel().select(0);

  }

  @FXML
  private void showPage(ActionEvent event) {
    if (check.isSelected()) {
      string = getClass().getResource("pics/").toString() + "master/" + cb.getValue() + ".png";
    } else {
      string = getClass().getResource("pics/").toString() + "forms/" + cb.getValue() + ".png";
    }
    System.out.println(string);
    img = new Image(string);
    iv.setImage(img);
  }

}
