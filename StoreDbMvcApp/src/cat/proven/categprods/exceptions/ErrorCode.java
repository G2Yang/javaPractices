/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.categprods.exceptions;

/**
 *
 * @author dax
 */
public enum ErrorCode {
    DB_NO_CONNECTION(-10),
    DB_SQL_SYNTAX(-11);
    private final int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
