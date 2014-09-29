/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.com.sigis.rfid.conversor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cgonzalez
 */
public class HexCodes {

    private final SimpleStringProperty id;
    private final SimpleStringProperty type;
    private final SimpleStringProperty code;
    private static int numResulCodes;


    public HexCodes(String id, String type, String code) {
        this.id = new SimpleStringProperty(id);
        this.type = new SimpleStringProperty(type);
        this.code = new SimpleStringProperty(code);
        HexCodes.numResulCodes++;
    }


    public HexCodes(String type, String code) {
        this.id = new SimpleStringProperty(String.valueOf(HexCodes.numResulCodes + 1));
        this.type = new SimpleStringProperty(type);
        this.code = new SimpleStringProperty(code);
        HexCodes.numResulCodes++;
    }


    public void setId(String value) {
        this.id.set(value);
    }


    public String getId() {
        return this.id.get();
    }


    public StringProperty idProperty() {
        return this.id;
    }


    public void setType(String value) {
        this.type.set(value);
    }


    public String getType() {
        return this.type.get();
    }

  
    public StringProperty typeProperty() {
        return this.type;
    }

 
    public void setCode(String value) {
        this.code.set(value);
    }

 
    public String getCode() {
        return this.code.get();
    }

 
    public StringProperty codeProperty() {
        return this.code;
    }
}
