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
public enum Filter {
    _1(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7);

    private final int value;

    private Filter(final int value) {
        this.value = value;
    }   
}
