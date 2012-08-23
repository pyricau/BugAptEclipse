package com.bug.apt;

/*
 * The import triggers a compilation error, if @SuppressWarnings has a reference to
 * Integer.MAX_VALUE: "The import com.bug.apt.other.SomeBean_ cannot be resolved"
 */
import com.bug.apt.other.SomeBean_;

@SuppressWarnings("unused")
public class SomeClass {

	/*
	 * The problem only occurs when you do a Project > Clean. It doesn't occur
	 * when saving the file without cleaning (which removes
	 * com.bug.apt.other.SomeBean_)
	 * 
	 * If you comment "@SuppressWarnings("" + Integer.MAX_VALUE)", no error will
	 * occur.
	 * 
	 * If you replace Integer.MAX_VALUE with its value, no error will occur.
	 * 
	 * This seems to be because @SuppressWarnings fires the Eclipse annotation
	 * processing, which sees a reference that needs to be resolved
	 * (Integer.MAX_VALUE), and therefore tries to resolve imports, where it
	 * finds import com.bug.apt.other.SomeBean_ and cannot resolve it.
	 * 
	 * This can happen with any annotation processor that generates code.
	 */
	@SuppressWarnings("" + Integer.MAX_VALUE)
	// @SuppressWarnings("" + 2147483647)
	Object none;

}
