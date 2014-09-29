package ve.com.sigis.rfid.conversor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.xml.bind.JAXBException;
import org.epcglobalinc.tdt.LevelTypeList;
import org.fosstrak.tdt.TDTEngine;

//Necesarios para manejar las constantes y enumeraciones
public class FXMLController implements Initializable {

    //Necesary for conversion
    private Map<String, String> params;
    TDTEngine tdt;
    static final Logger logger = Logger.getLogger(FXMLController.class.getName());
    String fromCodeInitial;
    //save the result of convertion
    String outpuStringCode;
    String outpuStringCodeHex;
    URL u1;
    URL u2;

    @FXML //toCode ComboBox
    private ComboBox<String> toCode;
    private final ObservableList<String> toCodeData = FXCollections.observableArrayList();

    @FXML //tagLength
    private ComboBox<String> tagLength;
    private final ObservableList<String> tagLengthData = FXCollections.observableArrayList();

    @FXML //companyPrefixLength
    private ComboBox<String> companyPrefixLength;
    private final ObservableList<String> companyPrefixLengthData = FXCollections.observableArrayList();

    @FXML //codeFilter
    private ComboBox<String> codeFilter;
    private final ObservableList<String> codeFilterData = FXCollections.observableArrayList();

    @FXML //converTo
    private Button converTo;

    @FXML //fromCode
    private TextField fromCode;

    @FXML //resultCodesTable
    private TableView<ResultCodes> resultCodesTable;
    private final ObservableList<ResultCodes> resultCodesTableData = FXCollections.observableArrayList();

    @FXML //idResultCodesCol
    private TableColumn<ResultCodes, String> idResultCodesCol;

    @FXML //typeResultCodesCol
    private TableColumn<ResultCodes, String> typeResultCodesCol;

    @FXML //valueResultCodesCol
    private TableColumn<ResultCodes, String> valueResultCodesCol;

    @FXML //hexCodesTable
    private TableView<HexCodes> hexCodesTable;
    private final ObservableList<HexCodes> hexCodesTableData = FXCollections.observableArrayList();

    @FXML //idHexCodesCol
    private TableColumn<HexCodes, String> idHexCodesCol;

    @FXML //typeHexCodesCol
    private TableColumn<HexCodes, String> typeHexCodesCol;

    @FXML //valueHexCodesCol
    private TableColumn<HexCodes, String> valueHexCodesCol;

    @Override //Show values in comboBox toCode
    public void initialize(URL url, ResourceBundle rb) {

        fromCodeInitial = "gtin=00037000302414;serial=1041970";
        params = new HashMap<>();

        //URL auxiliary = getClass().getResource("/auxiliary/ManagerTranslation.xml");
        URL auxiliary = null;
        try {
            auxiliary = new File("/home/cgonzalez/NetBeansProjects/auxiliary/ManagerTranslation.xml").toURI().toURL();
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        //URL schemes = getClass().getResource("/schemes/");
        URL schemes = null;
        try {
            schemes = new File("/home/cgonzalez/NetBeansProjects/schemes").toURI().toURL();
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

//        System.out.println(auxiliary.toExternalForm());
//        System.out.println(schemes.toExternalForm());
        try {
            tdt = new TDTEngine(auxiliary, schemes);
            //tdt = new TDTEngine();
        } catch (IOException | JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        //
        //
        //
        //Filling ComboBox ToCode:
        for (LevelTypeList Code : LevelTypeList.values()) {
            toCodeData.addAll(Code.name());
        }
        toCode.setItems(toCodeData);
        toCode.setValue(LevelTypeList.BINARY.name());

        // Define rendering of the list of values in ComboBox drop down. 
        toCode.setCellFactory((ListView<String> comboBox) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
        });

        //show values in comboBox tagLength:
        for (TagLength tag : TagLength.values()) {
            tagLengthData.addAll(tag.name().substring(1));
        }
        tagLength.setItems(tagLengthData);
        tagLength.setValue(TagLength._96bits.name().substring(1));

        // Define rendering of the list of values in ComboBox drop down. 
        tagLength.setCellFactory((comboBox) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
        });

        //Rellenar el Combo de companyPrefixLength:
        for (CompanyPrefixLength Length : CompanyPrefixLength.values()) {
            companyPrefixLengthData.addAll(Length.name().substring(1));
        }
        companyPrefixLength.setItems(companyPrefixLengthData);
        companyPrefixLength.setValue(CompanyPrefixLength._7Characters.name().substring(1));

        // Define rendering of the list of values in ComboBox drop down. 
        companyPrefixLength.setCellFactory((comboBox) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
        });

        //Rellenar el Combo de codeFilter:
        for (Filter filter : Filter.values()) {
            codeFilterData.addAll(filter.name().substring(1));
        }
        codeFilter.setItems(codeFilterData);
        codeFilter.setValue(Filter._3.name().substring(1));

        // Define rendering of the list of values in ComboBox drop down. 
        codeFilter.setCellFactory((ListView<String> comboBox) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
        });

        //Default values
        fromCode.setText(fromCodeInitial);
        //Set default values for resultCodesTable
        resultCodesTable.setItems(resultCodesTableData);
        idResultCodesCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeResultCodesCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        valueResultCodesCol.setCellValueFactory(cellData -> cellData.getValue().codeProperty());

        //Set default values for resultCodesTable
        hexCodesTable.setItems(hexCodesTableData);
        idHexCodesCol.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeHexCodesCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        valueHexCodesCol.setCellValueFactory(cellData -> cellData.getValue().codeProperty());

        // RULES for Selection
        //tagLength is only necesary for binary or tag-encoding
        toCode.setOnAction((event) -> {
            String selected = toCode.getSelectionModel().getSelectedItem();
            if (!selected.equalsIgnoreCase(LevelTypeList.BINARY.name()) && !selected.equalsIgnoreCase(LevelTypeList.TAG_ENCODING.name())) {
                tagLength.setDisable(true);
            } else {
                tagLength.setDisable(false);
            }
        });

        //When the conversion happen
        converTo.setOnMouseClicked((MouseEvent event) -> {
            //First check the textField fromCode is not empty, If it is, send a WARNING mgs.
            if (fromCode.getText().length() == 0) {
                fromCode.setText("WARNING: This Field is empty, please put your code here!");
            }

            //Get the value from tagLength, codeFilter and companyPrefixLength
            if (tagLength.isDisabled() == false) {
                String tagLengthString = tagLength.getSelectionModel().getSelectedItem();
                String tagLengthValue = tagLengthString.substring(0, tagLengthString.length() - 4);
                //
                if (!params.containsKey("taglength")) {
                    params.put("taglength", tagLengthValue);
                }
            }

            if (codeFilter.isDisabled() == false) {
                String codeFilterValue = codeFilter.getSelectionModel().getSelectedItem();
                //
                if (!params.containsKey("filter")) {
                    params.put("filter", codeFilterValue);
                }
            }

            if (companyPrefixLength.isDisabled() == false) {
                String companyPrefixLengthString = companyPrefixLength.getSelectionModel().getSelectedItem();
                String companyPrefixLengthValue = companyPrefixLengthString.substring(0, companyPrefixLengthString.length() - 10);
                //
                if (!params.containsKey("gs1companyprefixlength")) {
                    params.put("gs1companyprefixlength", companyPrefixLengthValue);
                }
            }

            //output convertion code
            outpuStringCode = tdt.convert(fromCode.getText(), params, LevelTypeList.valueOf(toCode.getSelectionModel().getSelectedItem()));

            //for every convert, always convert to BINARY and then to HEX, is easier to do that
            outpuStringCodeHex = tdt.bin2hex(tdt.convert(fromCode.getText(), params, LevelTypeList.BINARY));
            System.out.println(outpuStringCodeHex);

            resultCodesTableData.add(new ResultCodes(toCode.getSelectionModel().getSelectedItem(), outpuStringCode));
            hexCodesTableData.add(new HexCodes("Hex", outpuStringCodeHex));

        });

        //Clean the WARNING TEXT!
        fromCode.setOnMouseClicked((event) -> {
            if (fromCode.getText().compareToIgnoreCase("WARNING: This Field is empty, please put your code here!") == 0) {
                fromCode.setText("");
            }
        });

    }
}
