package de.edgesoft.refereemanager.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;

import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.controller.editdialogs.EditDialogTraineeController;
import de.edgesoft.refereemanager.model.AppModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.FontWeight;
import jfxtras.scene.control.LocalTimeTextField;


/**
 * Annotation for matching FXML fields to JAXB fields.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of TT-Schiri: Referee Manager.
 *
 * TT-Schiri: Referee Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TT-Schiri: Referee Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TT-Schiri: Referee Manager. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.14.0
 * @since 0.13.0
 */
public class JAXBMatchUtils {

	/**
	 * Set field value with data of data classes.
	 *
	 * The for-loop for all fields cannot be called here, because the access
	 * to the field object is forbidden for private fields.
	 * In order to maintain separation of concerns I call the
	 * loop and the get method in the calling class.
	 * {@link EditDialogTraineeController#setData(de.edgesoft.refereemanager.model.PersonModel)}
	 *
	 * @param theFXMLField fxml field
	 * @param theFieldObject object of fxml field
	 * @param theModel data model object
	 * @param theDataClasses data classes
	 */
	@SuppressWarnings("unchecked")
	public static void setField(final Field theFXMLField, final Object theFieldObject, final ModelClass theModel, final List<Class<?>> theDataClasses) {

		assert (theFXMLField != null) : "FXML field must not be null.";
		assert (theFieldObject != null) : String.format("Field object must not be null, field is '%s', maybe the field is declared in the controller but not in the fxml file?", theFXMLField.getName());
		assert (theModel != null) : "Model must not be null.";
		assert (theDataClasses != null) : "Data classes must not be null.";

    	for (Class<?> theClass : theDataClasses) {

    		if (theClass.isInstance(theModel)) {

	    		for (Field theJAXBField : theClass.getDeclaredFields()) {

	    			if (JAXBMatchUtils.isMatch(theFXMLField, theJAXBField)) {

	    				String sFieldName = theFXMLField.getAnnotation(JAXBMatch.class).jaxbfield();

	    				try {

	    					if (theFieldObject instanceof TextInputControl) {

	    						String sValue = null;

	    						if (getGetterMethod(theClass, sFieldName).getReturnType() == String.class) {
	        						sValue = (String) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						} else {

		    						StringProperty sTemp = (StringProperty) getGetterMethod(theClass, sFieldName).invoke(theModel);
		    						sValue = (sTemp == null) ? null : sTemp.getValue();

	    						}

	    						((TextInputControl) theFieldObject).setText(sValue);

	    					} else if (theFieldObject instanceof DatePicker) {

	    						SimpleObjectProperty<LocalDate> objTemp = (SimpleObjectProperty<LocalDate>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						((DatePicker) theFieldObject).setValue((objTemp == null) ? null : objTemp.getValue());

	    					} else if (theFieldObject instanceof LocalTimeTextField) {

	    						SimpleObjectProperty<LocalTime> objTemp = (SimpleObjectProperty<LocalTime>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						((LocalTimeTextField) theFieldObject).setLocalTime((objTemp == null) ? null : objTemp.getValue());

	    					} else if (theFieldObject instanceof ComboBox<?>) {

	    						ModelClassExt objTemp = (ModelClassExt) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						((ComboBox<ModelClassExt>) theFieldObject).setValue(objTemp);

	    					} else if (theFieldObject instanceof ListView<?>) {

	    						List<?> lstTemp = (List<?>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						((ListView<ModelClassExt>) theFieldObject).setItems(FXCollections.observableArrayList((List<ModelClassExt>) lstTemp));

	    					} else if (theFieldObject instanceof ListCRUDController) {

	    						List<?> lstTemp = (List<?>) getGetterMethod(theClass, sFieldName).invoke(theModel);

	    						// @todo Remove if generation error of JAXB for lists of referenced elements is fixed.
	    						if (!lstTemp.isEmpty()) {
	    							if (lstTemp.get(0) instanceof JAXBElement<?>) {
	    	    						lstTemp = (List<?>) getListGetterMethod(theClass, sFieldName).invoke(theModel);
	    							}
	    						}

	    						((ListCRUDController<ModelClassExt>) theFieldObject).setItems(FXCollections.observableArrayList((List<ModelClassExt>) lstTemp));

	    					} else if (theFieldObject instanceof CheckBox) {

	    						BooleanProperty bTemp = (BooleanProperty) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						((CheckBox) theFieldObject).setSelected((bTemp == null) ? false : bTemp.getValue());

	    					} else if (theFieldObject instanceof Spinner<?>) {

	    						SimpleIntegerProperty objTemp = (SimpleIntegerProperty) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						((Spinner<Integer>) theFieldObject).getValueFactory().setValue((objTemp == null) ? 0 : objTemp.getValue());

	    					}

	    				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    					e.printStackTrace();
	    				}

	    			}

	    		}
    		}

		}

	}

	/**
	 * Clears field value.
	 *
	 * The for-loop for all fields cannot be called here, because the access
	 * to the field object is forbidden for private fields.
	 * In order to maintain separation of concerns I call the
	 * loop and the get method in the calling class.
	 * {@link EditDialogTraineeController#setData(de.edgesoft.refereemanager.model.PersonModel)}
	 *
	 * @param theFieldObject object of fxml field
	 */
	@SuppressWarnings("unchecked")
	public static void clearField(final Object theFieldObject) {

		assert (theFieldObject != null) : String.format("Field object must not be null, maybe the field is declared in the controller but not in the fxml file?");


		if (theFieldObject instanceof TextInputControl) {

			((TextInputControl) theFieldObject).setText(null);

		} else if (theFieldObject instanceof DatePicker) {

			((DatePicker) theFieldObject).setValue(null);

		} else if (theFieldObject instanceof LocalTimeTextField) {

			((LocalTimeTextField) theFieldObject).setLocalTime(null);

		} else if (theFieldObject instanceof ComboBox<?>) {

			((ComboBox<ModelClassExt>) theFieldObject).setValue(null);

		} else if (theFieldObject instanceof ListView<?>) {

			((ListView<ModelClassExt>) theFieldObject).setItems(null);

		} else if (theFieldObject instanceof ListCRUDController) {

			((ListCRUDController<ModelClassExt>) theFieldObject).setItems(null);

		} else if (theFieldObject instanceof CheckBox) {

			((CheckBox) theFieldObject).setSelected(false);

		} else if (theFieldObject instanceof Spinner<?>) {

			((Spinner<Integer>) theFieldObject).getValueFactory().setValue(0);

		}

	}

	/**
	 * Get field values, fill model object.
	 *
	 * The for-loop for all fields cannot be called here, because the access
	 * to the field object is forbidden for private fields.
	 * In order to maintain separation of concerns I call the
	 * loop and the get method in the calling class.
	 * {@link EditDialogTraineeController#setData(de.edgesoft.refereemanager.model.PersonModel)}
	 *
	 * @param theFXMLField fxml field
	 * @param theFieldObject object of fxml field
	 * @param theModel data model object
	 * @param theDataClasses data classes
	 */
	@SuppressWarnings("unchecked")
	public static void getField(final Field theFXMLField, final Object theFieldObject, final ModelClass theModel, final List<Class<?>> theDataClasses) {

		assert (theFXMLField != null) : "FXML field must not be null.";
		assert (theFieldObject != null) : String.format("Field object must not be null, field is '%s', maybe the field is declared in the controller but not in the fxml file?", theFXMLField.getName());
		assert (theModel != null) : "Model must not be null.";
		assert (theDataClasses != null) : "Data classes must not be null.";

    	for (Class<?> theClass : theDataClasses) {

    		if (theClass.isInstance(theModel)) {

	    		for (Field theJAXBField : theClass.getDeclaredFields()) {

	    			if (JAXBMatchUtils.isMatch(theFXMLField, theJAXBField)) {

	    				String sFieldName = theFXMLField.getAnnotation(JAXBMatch.class).jaxbfield();

	    				try {

	    					if (theFieldObject instanceof TextInputControl) {

	    						String sValue = ((TextInputControl) theFieldObject).getText();

	    						if (getGetterMethod(theClass, sFieldName).getReturnType() == String.class) {

	    							getSetterMethod(theClass, sFieldName, String.class).invoke(theModel, sValue);

	    						} else {

	        						if (sValue == null) {
	    								getSetterMethod(theClass, sFieldName, SimpleStringProperty.class).invoke(theModel, (SimpleStringProperty) null);
	        						} else {
	        							SimpleStringProperty sTemp = (SimpleStringProperty) getGetterMethod(theClass, sFieldName).invoke(theModel);
	        							if (sTemp == null) {
	        								sTemp = new SimpleStringProperty();
	        							}
	        							sTemp.setValue(sValue);
	        							getSetterMethod(theClass, sFieldName, SimpleStringProperty.class).invoke(theModel, sTemp);
	        						}

	    						}

	    					} else if (theFieldObject instanceof DatePicker) {

	    						LocalDate dteValue = ((DatePicker) theFieldObject).getValue();

	    						if (dteValue == null) {
									getSetterMethod(theClass, sFieldName, SimpleObjectProperty.class).invoke(theModel, (SimpleObjectProperty<?>) null);
	    						} else {
	    							SimpleObjectProperty<LocalDate> dteTemp = (SimpleObjectProperty<LocalDate>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    							if (dteTemp == null) {
	    								dteTemp = new SimpleObjectProperty<>();
	    							}
	    							dteTemp.setValue(dteValue);
	    							getSetterMethod(theClass, sFieldName, SimpleObjectProperty.class).invoke(theModel, dteTemp);
	    						}

	    					} else if (theFieldObject instanceof LocalTimeTextField) {

	    						LocalTime tmeValue = ((LocalTimeTextField) theFieldObject).getLocalTime();

	    						if (tmeValue == null) {
									getSetterMethod(theClass, sFieldName, SimpleObjectProperty.class).invoke(theModel, (SimpleObjectProperty<?>) null);
	    						} else {
	    							SimpleObjectProperty<LocalTime> tmeTemp = (SimpleObjectProperty<LocalTime>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    							if (tmeTemp == null) {
	    								tmeTemp = new SimpleObjectProperty<>();
	    							}
	    							tmeTemp.setValue(tmeValue);
	    							getSetterMethod(theClass, sFieldName, SimpleObjectProperty.class).invoke(theModel, tmeTemp);
	    						}

	    					} else if (theFieldObject instanceof ComboBox<?>) {

	    						ModelClassExt mdlTemp = ((ComboBox<ModelClassExt>) theFieldObject).getValue();
	    						getSetterMethod(theClass, sFieldName, ModelClassExt.class).invoke(theModel, mdlTemp);

	    					} else if (theFieldObject instanceof ListView<?>) {

	    						List<ModelClassExt> lstItems = (List<ModelClassExt>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						lstItems.clear();
	    						lstItems.addAll(((ListView<ModelClassExt>) theFieldObject).getItems());

	    					} else if (theFieldObject instanceof ListCRUDController) {

	    						List<ModelClassExt> lstItems = (List<ModelClassExt>) getGetterMethod(theClass, sFieldName).invoke(theModel);
	    						lstItems.clear();
	    						lstItems.addAll(((ListCRUDController<ModelClassExt>) theFieldObject).getItems());

	    					} else if (theFieldObject instanceof CheckBox) {

								getSetterMethod(theClass, sFieldName, BooleanProperty.class).invoke(theModel, new SimpleBooleanProperty(((CheckBox) theFieldObject).isSelected()));

	    					} else if (theFieldObject instanceof Spinner<?>) {

								getSetterMethod(theClass, sFieldName, IntegerProperty.class).invoke(theModel, new SimpleIntegerProperty(((Spinner<Integer>) theFieldObject).getValue()));

	    					}

	    				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    					e.printStackTrace();
	    				}

	    			}

	    		}

    		}

		}

	}

	/**
	 * Does fxml field match jaxb field?
	 *
	 * Match ignores case, which is needed because of the generated names of JAXB, but is slightly wrong.
	 *
	 * @param theFXMLField fxml field
	 * @param theJAXBField jaxb field
	 *
	 * @return does fxml field match jaxb field?
	 */
	public static boolean isMatch(final Field theFXMLField, final Field theJAXBField) {

		assert (theFXMLField != null) : "FXML field must not be null.";
		assert (theJAXBField != null) : "JAXB field must not be null.";

		if (theFXMLField.getAnnotation(JAXBMatch.class) == null) {
			return false;
		}

		return (theFXMLField.getAnnotation(JAXBMatch.class).jaxbclass() == theJAXBField.getDeclaringClass()) &&
				theFXMLField.getAnnotation(JAXBMatch.class).jaxbfield().equalsIgnoreCase(theJAXBField.getName());

	}

	/**
	 * Getter method for the field in the class.
	 *
	 * @param theClass class
	 * @param theJAXBFieldName jaxb field
	 * @return getter method
	 *
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Method getGetterMethod(final Class<?> theClass, final String theJAXBFieldName) throws NoSuchMethodException, SecurityException {

		assert (theClass != null) : "Class must not be null.";
		assert (theJAXBFieldName != null) : "JAXB field name must not be null.";

		return theClass.getDeclaredMethod(getGetter(theJAXBFieldName));

	}

	/**
	 * Setter method for the field in the class.
	 *
	 * {@link Class#getDeclaredMethod(String, Class...)} does not support
	 * polymorphism for parameter classes, thus the complicated implementation.
	 *
	 * @param theClass class
	 * @param theJAXBFieldName jaxb field
	 * @return setter method
	 *
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Method getSetterMethod(final Class<?> theClass, final String theJAXBFieldName, final Class<?> theParameterClass) throws NoSuchMethodException, SecurityException {

		assert (theClass != null) : "Class must not be null.";
		assert (theJAXBFieldName != null) : "JAXB field name must not be null.";
		assert (theParameterClass != null) : "Parameter class must not be null.";

		for (Method method : theClass.getDeclaredMethods()) {
			if (method.getName().equals(getSetter(theJAXBFieldName)) && (method.getParameterCount() == 1)) {
				Class<?>[] params = method.getParameterTypes();
				if (theParameterClass.isAssignableFrom(params[0])) {
					return method;
				}
			}
		}

		throw new NoSuchMethodException(String.format("%s.%s(%s)", theClass.getCanonicalName(), getSetter(theJAXBFieldName), theParameterClass.getCanonicalName()));

	}

	/**
	 * Getter for the field.
	 *
	 * @todo Is there a built-in Java method (jaxb) for this?
	 *
	 * @param theJAXBFieldName jaxb field
	 *
	 * @return getter name
	 */
	public static String getGetter(final String theJAXBFieldName) {

		assert (theJAXBFieldName != null) : "JAXB field name must not be null.";

		return String.format("get%s", getSuffix(theJAXBFieldName));

	}

	/**
	 * Setter for the field.
	 *
	 * @todo Is there a built-in Java method (jaxb) for this?
	 *
	 * @param theJAXBFieldName jaxb field
	 *
	 * @return setter name
	 */
	public static String getSetter(final String theJAXBFieldName) {

		assert (theJAXBFieldName != null) : "JAXB field name must not be null.";

		return String.format("set%s", getSuffix(theJAXBFieldName));

	}

	/**
	 * Access suffix for the field.
	 *
	 * @todo Is there a built-in Java method (jaxb) for this?
	 *
	 * @param theJAXBFieldName jaxb field
	 *
	 * @return access suffix
	 */
	public static String getSuffix(final String theJAXBFieldName) {

		assert (theJAXBFieldName != null) : "JAXB field name must not be null.";

		return String.format("%s%s", theJAXBFieldName.substring(0, 1).toUpperCase(), theJAXBFieldName.substring(1));

	}

	/**
	 * Mark required fields.
	 *
	 * @param theFXMLField fxml field
	 * @param theFieldObject object of fxml field
	 * @param theDataClasses data classes
	 */
	public static void markRequired(final Field theFXMLField, final Object theFieldObject, final List<Class<?>> theDataClasses) {

		assert (theFXMLField != null) : "FXML field must not be null.";

    	for (Class<?> theClass : theDataClasses) {

    		for (Field theJAXBField : theClass.getDeclaredFields()) {

    			if ((theJAXBField.getAnnotation(XmlElement.class) != null) && theJAXBField.getAnnotation(XmlElement.class).required()) {

	    			if (JAXBMatchUtils.isMatch(theFXMLField, theJAXBField)) {

	    				try {

	    					// not checking directly for Labeled to avoid unneccessary boldness (i.e. for buttons)
	    					if ((theFieldObject instanceof Label) || (theFieldObject instanceof CheckBox)) {

								((Labeled) theFieldObject).setFont(FontUtils.getDerived(((Labeled) theFieldObject).getFont(), FontWeight.BOLD));

	    					}

	    				} catch (IllegalArgumentException | SecurityException e) {
	    					e.printStackTrace();
	    				}

	    			}

    			}

    		}

		}

	}

	/**
	 * Getter method for the field in the class as list.
	 *
	 * @todo Remove if generation error of JAXB for lists of referenced elements is fixed.
	 *
	 * @param theClass class
	 * @param theJAXBFieldName jaxb field
	 * @return getter method
	 *
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private static Method getListGetterMethod(final Class<?> theClass, final String theJAXBFieldName) throws NoSuchMethodException, SecurityException {

		assert (theClass != null) : "Class must not be null.";
		assert (theJAXBFieldName != null) : "JAXB field name must not be null.";

		String sModelClass = String.format("%s.%sModel", AppModel.class.getPackage().getName(), theClass.getSimpleName());

		Class<? extends ModelClassExt> clsModel = null;
		try {
			clsModel = (Class<? extends ModelClassExt>) Class.forName(sModelClass);
		} catch (ClassNotFoundException e) {
			throw new NoSuchMethodException(e.getMessage());
		}

		return getGetterMethod(clsModel, String.format("%sList", theJAXBFieldName));

	}

}

/* EOF */
