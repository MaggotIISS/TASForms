/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package tasforms;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
  private ActionEvent ae;

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
    load("forms");
    load("master");
  }

  @FXML
  private void checkChange(ActionEvent event) {
    if (check.isSelected()) {
      load("Master");
      string = getClass().getResource("pics/").toString() + "master/" + cb.getValue() + ".png";
    } else {
      load("Forms");
      string = getClass().getResource("pics/").toString() + "forms/" + cb.getValue() + ".png";
    }
    System.out.println(string);
    img = new Image(string);
    iv.setImage(img);
    showPage(ae);
  }

  private void load(String folder) {
    ArrayList al = new ArrayList();
    Object[] lines = null;
    File dir = new File("C://T5 Extra/TASForms/src/tasforms/pics/" + folder + "/");
    System.out.println("dir = " + dir.toString());
    try {
      lines = dir.list();
      int count = 0;
      for (int i = 0; i < lines.length; i++) {
        if (lines[i].toString().endsWith(".png")) {
          count += 1;
          System.out.println(lines[i].toString().substring(0, lines[i].toString().length() - 4));
          al.add(lines[i].toString().substring(0, lines[i].toString().length() - 4));
        }
      }
      int siz = al.size();
      lines = al.toArray();
      System.out.println("setCombo(" + folder + ")");
      Object[] strings = lines;
      cb.getItems().clear();
      for (int i = 0; i < strings.length; i++) {
        oal.add(strings[i].toString());
        cb.getItems().add(strings[i].toString());
      }
      cb.getItems().sort(null);
      cb.getSelectionModel().select(0);
    } catch (Exception e) {
      System.out.println(e);
    }
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
