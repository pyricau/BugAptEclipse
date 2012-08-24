package com.bug.apt.processing;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Generates a new empty class for each type "SomeType" annotated with
 * {@link Generate}. The generated class is named "generated.GeneratedSomeType"
 */
@SupportedAnnotationTypes("com.bug.apt.processing.Generate")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SimpleProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		for (TypeElement annotation : annotations) {
			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
			for (Element annotatedElement : annotatedElements) {
				Filer filer = processingEnv.getFiler();
				String generatedClassSimpleName = "Generated" + annotatedElement.getSimpleName().toString();
				try {
					JavaFileObject file = filer.createSourceFile("generated/" + generatedClassSimpleName, annotatedElement);
					file.openWriter() //
							.append("package generated;\n" //
									+ "\n" //
									+ "public class " + generatedClassSimpleName + " {\n" //
									+ "\n" //
									+ "}\n") //
							.close();
				} catch (IOException e) {
					Messager messager = processingEnv.getMessager();
					messager.printMessage(Diagnostic.Kind.ERROR, "IOException: " + e);
				}
			}
		}

		return true;
	}
}
