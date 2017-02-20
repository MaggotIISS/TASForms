/*
 To change this license header, choose License Headers in Project Properties.
 To change this template folder, choose Tools | Templates
 and open the template in the editor.
 */
package tasforms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**

 @author Mark Ferguson
 */
public class TASFormsController implements Initializable {

  private String T5 = "C://T5";

  @FXML
  private ComboBox<String> cb;
  @FXML
  private CheckBox check;
  @FXML
  private ImageView iv;
  @FXML
  private Label ref;
  @FXML
  private TextArea ta;

  private ObservableList<String> oal = FXCollections.observableArrayList();
  private Image img;
  private String[] forms = {"585"};
  private String[] masters = {"246"};
  private String string;
  private ActionEvent ae;
  private String[] names;
  private String CRLF = "\n";
  private String FS = File.separator;
  private String[] listing;
  private File dir;
  private File file;
  private String oldValue = "";
  private FileChooser fc;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    fc = new FileChooser();
    String stringa = getClass().getResource("pics/master/039.png").toString();
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
    oldValue = cb.getValue();
    try {
      createNames();
      loadText(dir + FS + cb.getValue() + ".txt");
    } catch (IOException ex) {
      Logger.getLogger(TASFormsController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void createNames() throws IOException {
    names = new String[cb.getItems().size()];
    dir = new File(ref.getText());
    listing = dir.list();
//    ta.setText(makeFiles(".png", ".txt"));
    makeFiles(".png", ".txt");
  }

  @FXML
  private void setOldValue(MouseEvent event) throws IOException {
    System.out.println("setOldValue");
    oldValue = cb.getValue();
    saveText(dir + FS + oldValue + ".txt");
  }

  private String makeFiles(String from, String to) throws IOException {
    System.out.println("makeFiles from " + from + " to " + to);
    String lines = "";
    for (int i = 0; i < listing.length; i++) {
      if (listing[i].endsWith(from)) {
        file = new File(dir + FS + listing[i].replace(from, to));
        if (!file.exists()) {
          file.createNewFile();
          loadText(listing[i].replace(from, to));
        }

        lines += listing[i].replace(from, to).substring(0, listing[i].length()) + CRLF;
      }
    }
//    ta.setText("");
    return lines;
  }

  @FXML
  private void checkChange(ActionEvent event) throws IOException {
    try {
      oldValue = dir + FS + cb.getValue();
      if (check.isSelected()) {
        load("Master");
        string = getClass().getResource("pics/").toString() + "master/" + cb.getValue() + ".png";
      } else {
        try {
          load("Forms");
          string = getClass().getResource("pics/").toString() + "forms/" + cb.getValue() + ".png";
        } catch (Exception e) {
          System.out.println(e);
        }
      }
      System.out.println(string);
      img = new Image(string);
      iv.setImage(img);
      showPage(ae);
      createNames();
      loadText(dir + FS + cb.getValue() + ".txt");
    } catch (Exception e) {

    }
  }

  private void load(String folder) {
    ArrayList al = new ArrayList();
    Object[] lines = null;
    File dir = new File("C://T5 Extra/TASForms/src/tasforms/pics/" + folder.toLowerCase() + "/");
//    System.out.println("dir = " + dir.toString());
    try {
      lines = dir.list();
      int count = 0;
      for (int i = 0; i < lines.length; i++) {
        if (lines[i].toString().endsWith(".png")) {
          count += 1;
//          System.out.println(lines[i].toString().substring(0, lines[i].toString().length() - 4));
          al.add(lines[i].toString().substring(0, lines[i].toString().length() - 4));
        }
      }
      int siz = al.size();
      lines = al.toArray();
      System.out.println("load(" + folder + ")");
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
    ref.setText(dir.toString());
  }

  private void saveText(String name) throws IOException {
    System.out.println("saveText(" + name + ")");
    PrintWriter pw = new PrintWriter(new FileWriter(name));
    pw.println(ta.getText().trim());
    pw.close();
//    saveNames();
  }

  @FXML
  private void scroll(ScrollEvent event) {
    try {
      double s = event.getDeltaY();
      if (s < 0) {
        System.out.println("DOWN");
        cb.getSelectionModel().selectNext();
      }
      if (s > 0) {
        System.out.println("UP");
        cb.getSelectionModel().selectPrevious();
      }
    } catch (Exception e) {
      ta.appendText(e.toString() + CRLF);
    }
  }

  private void scrollCombo(ScrollEvent event) {
    double s = event.getDeltaY();
    if (s < 0) {
      System.out.println("DOWN");
      cb.getSelectionModel().selectNext();
    }
    if (s > 0) {
      System.out.println("UP");
      cb.getSelectionModel().selectPrevious();
    }
  }

  @FXML
  private void showPage(ActionEvent event) throws IOException {
    System.out.println("showPage");
    if (check.isSelected()) {
      string = getClass().getResource("pics/").toString() + "master/" + cb.getValue() + ".png";
    } else {
      string = getClass().getResource("pics/").toString() + "forms/" + cb.getValue() + ".png";
    }
//    System.out.println(string);
    img = new Image(string);
    iv.setImage(img);
//    ref.setText(string);
    loadText(dir + FS + cb.getValue() + ".txt");
  }

  private void loadText(String name) throws FileNotFoundException, IOException {
    try {
      System.out.println("loadText(from " + name + ")");
      BufferedReader br = new BufferedReader(new FileReader(name));
      String text = "";
      oldValue = cb.getValue();
      ta.setText("");
      while ((text = br.readLine()) != null) {
        ta.appendText(text + CRLF);
      }
      File f = new File(dir + FS + name);
    } catch (Exception e) {

    }
  }

  @FXML
  private void taClick(MouseEvent event) {
    if (event.getClickCount() > 1) {
      ta.setText("");
    }
  }

  @FXML
  private void refClick(MouseEvent event) {
    try {
      System.out.println("refClick()");
      File f = null;
      fc.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png"),
        new ExtensionFilter("Text Files", "*.txt")
      );
      f = fc.showOpenDialog(null);

      System.out.println(f.toString());
      int last = f.toString().lastIndexOf(FS) + 1;
      String folder = f.toString().substring(0, last);
      System.out.println("folder = " + folder);
      String filename = f.toString().substring(folder.length());
      System.out.println("filename = " + filename);
      dir = new File(folder);
      System.out.println("dir = " + dir);
      ref.setText(folder);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @FXML
  private void copyPic(MouseEvent event) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Resource File");
    fileChooser.getExtensionFilters().addAll(
      new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
      new ExtensionFilter("PNG Files", "*.png")
    );
    ExtensionFilter filter = fileChooser.getExtensionFilters().get(0);
    fileChooser.setSelectedExtensionFilter(filter);
    fileChooser.setInitialFileName(ta.getText().trim());
    File selectedFile = fileChooser.showSaveDialog(null);
    if (selectedFile != null) {
      File froma = new File(ref.getText() + FS + cb.getValue().trim() + ".png");
      Path from = froma.toPath();
      File toa = selectedFile;
      Path to = toa.toPath();
      StandardCopyOption options = StandardCopyOption.REPLACE_EXISTING;
      try {
        Files.copy(from, to, options);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }
}
