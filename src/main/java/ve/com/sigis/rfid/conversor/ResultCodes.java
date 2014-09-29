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
 * @author Christian González
 */
public class ResultCodes {

    private final SimpleStringProperty id;
    private final SimpleStringProperty type;
    private final SimpleStringProperty code;
    private static int numResulCodes;


    public ResultCodes(String id, String type, String code) {
        this.id = new SimpleStringProperty(id);
        this.type = new SimpleStringProperty(type);
        this.code = new SimpleStringProperty(code);
        ResultCodes.numResulCodes++;
    }


    public ResultCodes(String type, String code) {
        this.id = new SimpleStringProperty(String.valueOf(ResultCodes.numResulCodes + 1));
        this.type = new SimpleStringProperty(type);
        this.code = new SimpleStringProperty(code);
        ResultCodes.numResulCodes++;
    }


    public void setId(String value) {
        this.id.set(value);
    }

    /**
     *
     * @return
     */
    public String getId() {
        return this.id.get();
    }

    /**
     *
     * @return
     */
    public StringProperty idProperty() {
        return this.id;
    }

    /**
     *
     * @param value
     */
    public void setType(String value) {
        this.type.set(value);
    }

    /**
     *
     * @return
     */
    public String getType() {
        return this.type.get();
    }

    /**
     *
     * @return
     */
    public StringProperty typeProperty() {
        return this.type;
    }

    /**
     *
     * @param value
     */
    public void setCode(String value) {
        this.code.set(value);
    }

    /**
     *
     * @return
     */
    public String getCode() {
        return this.code.get();
    }

    /**
     *
     * @return
     */
    public StringProperty codeProperty() {
        return this.code;
    }
}
