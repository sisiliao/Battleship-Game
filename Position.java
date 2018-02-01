/*--------------------------------------------------------
Name(s) and ID(s): Li Sun(40017648),Siyun Liao(25658306);
COMP249 Section PP
Assignment #1
Due Date: February 4, 2017
--------------------------------------------------------*/
package assignment1;

/**
 * @author Li Sun(40017648),Siyun Liao(25658306);
 */

public class Position {
	private char element;
	private char owner;
	private boolean called;

	/**
	 * default constructor
	 */
	public Position() {
		element = '_';
		owner = 'n'; // n stands for nothing is there
		called = false;
	}

	/**
	 * constructor
	 * 
	 * @param element
	 *            char
	 * @param owner
	 *            char
	 * @param called
	 *            boolean
	 */
	public Position(char element, char owner, boolean called) {
		this.element = element;
		this.owner = owner;
		this.called = called;
	}

	/**
	 * @return chess element
	 */
	public char getElement() {
		return element;
	}

	/**
	 * @param element
	 *            char
	 */
	public void setElement(char element) {
		this.element = element;
	}

	/**
	 * @return char owner
	 */
	public char getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            char
	 */
	public void setOwner(char owner) {
		this.owner = owner;
	}

	/**
	 * @return boolean called
	 */
	public boolean getCalled() {
		return called;
	}

	/**
	 * @param called
	 *            boolean variable
	 */
	public void setCalled(boolean called) {
		this.called = called;
	}

	// toString method
	public String toString() {
		return ("element: " + element + " owner: " + owner + " called: " + called);
	}

}
