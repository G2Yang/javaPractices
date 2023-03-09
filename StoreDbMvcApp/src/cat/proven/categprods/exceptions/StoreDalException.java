/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.categprods.exceptions;

/**
 *Exception to catch data access layer errors in Store application
 * @author dax
 */
public class StoreDalException extends Exception {
    
    private int errorCode;

    public StoreDalException(String message, int errorCode) {
        super(message);
        this.errorCode= errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StoreDalException{");
        sb.append("errorCode=").append(errorCode);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
