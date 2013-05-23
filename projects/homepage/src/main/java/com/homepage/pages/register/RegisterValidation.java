package com.homepage.pages.register;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.Validatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;

import com.homepage.pages.RegisterValidatable;

public class RegisterValidation extends AbstractValidator {

	public RegisterValidation() {
		
	}

	@Override
	protected void onValidate(IValidatable validatable) {
		System.out.println(validatable.getValue());
	}

}
