package cz.cvut.fel.x33eja.chalupa.eshop.action.exception;

import java.io.IOException;

import javax.ejb.ApplicationException;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@ApplicationException(rollback = true)
public class FileSaveException extends IOException {
	public FileSaveException(String message) {
		super(message);
	}
}
