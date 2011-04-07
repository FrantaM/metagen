package net.ftlines.metagen.processor.model;

import java.util.Collection;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;

import net.ftlines.metagen.processor.util.Optional;


public class ModelExt {
	private ModelExt() {

	}

	public static ElementExt of(Element e) {
		if (e == null) {
			return null;
		}
		if (e instanceof TypeElement) {
			return of((TypeElement) e);
		} else {
			return new ElementExt(e);
		}
	}

	public static TypeElementExt of(TypeElement e) {

		return e != null ? new TypeElementExt(e) : null;
	}

	public static boolean hasAnnotation(Element e, String annotationType) {
		for (AnnotationMirror mirror : e.getAnnotationMirrors()) {
			String typeName = ((TypeElement) mirror.getAnnotationType()
					.asElement()).getQualifiedName().toString();
			if (typeName.equals(annotationType)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasAnyAnnotation(Element e,
			Collection<String> annotationTypes) {
		return hasAnyAnnotation(e,
				annotationTypes.toArray(new String[annotationTypes.size()]));
	}

	public static boolean hasAnyAnnotation(Element e, String... annotationTypes) {
		for (AnnotationMirror mirror : e.getAnnotationMirrors()) {
			String typeName = ((TypeElement) mirror.getAnnotationType()
					.asElement()).getQualifiedName().toString();

			for (String annotationType : annotationTypes) {
				if (typeName.equals(annotationType)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Optional<TypeElement> findTopLevelType(Element e) {
		while (e != null && !isTopLevelType(e)) {
			e = e.getEnclosingElement();
		}
		return Optional.of((TypeElement) e);
	}

	public static boolean isTopLevelType(Element e) {
		if (!ElementKind.CLASS.equals(e.getKind())) {
			return false;
		}

		TypeElement te = (TypeElement) e;
		if (!NestingKind.TOP_LEVEL.equals(te.getNestingKind())) {
			return false;
		}

		return true;
	}
}
