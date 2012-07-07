package com.bug.apt;

import com.bug.apt.other.SomeBean_;

/*
 * The problem only occurs when you do a Project > Clean.
 * 
 * It occurs at the following line: Class<?> test = SomeBean_.class;
 */
public class SomeClass {

	/*
	 * If you comment the following line, no error will occur.
	 * 
	 * If you replace Constants.VALUE with its value (42), no error will occur.
	 */
	@SomeAnnotation(Constants.VALUE)
	String test;

	private void someMethod() {
		/*
		 * Triggers a compilation error, if @SomeAnnotation has a reference to
		 * Constants.VALUE. When doing Project > Clean, it seems that both
		 * Constants.VALUE and com.bug.apt.other.SomeBean_ have compilation
		 * errors for half a second, then the error on Constants.VALUE
		 * disappears and you're left with an error on the following line.
		 */
		Class<?> test = SomeBean_.class;

		/*
		 * This never triggers a compilation error. Using Fully Qualified names
		 * solves the problems, which seems to show that the problems lies in
		 * import declarations.
		 */
		Class<?> test2 = com.bug.apt.other.SomeBean_.class;
	}
}
