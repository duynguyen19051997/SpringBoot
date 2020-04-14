package duynguyen.loginsocialjpa.validator;

import duynguyen.loginsocialjpa.dao.AppUserDAO;
import duynguyen.loginsocialjpa.entity.AppUser;
import duynguyen.loginsocialjpa.form.AppUserForm;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

@Component
public class AppUserValidator implements Validator {
    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private AppUserDAO appUserDAO;

    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }

    public void validate(Object target, Errors errors) {

        AppUserForm form = (AppUserForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");

        if (errors.hasErrors()) {
            return;
        }

        if (!emailValidator.isValid(form.getEmail())) {

            errors.rejectValue("email", "", "Email is not valid");
            return;
        }

        AppUser userAccount = appUserDAO.findUserByUsername(form.getUserName());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getUserId())) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            }
        }

        userAccount = appUserDAO.findUserByEmail(form.getEmail());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getUserId())) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            }
        }
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return null;
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }
}
