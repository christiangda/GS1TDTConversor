/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.com.sigis.rfid.conversor;

/**
 *
 * @author cgonzalez
 */
public enum CompanyPrefixLength {
    _1Character(1),
    _2Characters(2),
    _3Characters(3),
    _4Characters(4),
    _5Characters(5),
    _6Characters(6),
    _7Characters(7),
    _8Characters(8),
    _9Characters(9);

    private final int value;

    private CompanyPrefixLength(final int value) {
        this.value = value;
    }   
}