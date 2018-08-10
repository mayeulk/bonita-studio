/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.validation;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.namespace.SpaceType;
import org.w3._1999.xhtml.DeferType;

/**
 * A sample validator interface for {@link org.w3._1999.xhtml.ScriptType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ScriptTypeValidator {
	boolean validate();

	boolean validateMixed(FeatureMap value);
	boolean validateCharset(String value);
	boolean validateDefer(DeferType value);
	boolean validateId(String value);
	boolean validateSpace(SpaceType value);
	boolean validateSrc(String value);
	boolean validateType(String value);
}