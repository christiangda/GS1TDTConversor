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
public enum TagLength {
    _32bits(32),
    _64bits(64),
    _96bits(96),
    _128bits(128),
    _160bits(160),
    _192bits(192),
    _170bits(170),
    _256bits(256);

    private final int value;

    private TagLength(final int value) {
        this.value = value;
    }   
}
