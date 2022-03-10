package ui;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    final Validator validator = new Validator();


    @Test
    void idValidator(){
        boolean result = validator.validateId(UUID.fromString("5bd8a8c4-904d-4de9-9c72-89547728b602"));

        assertThat(result).isTrue()
                          .isNotNull();
    }


    @Test
    public void checkCityNameValidator() {
        boolean result = validator.cityNameValidation("Wawa");

        assertThat(result).isNotNull()
                            .isTrue();
    }

    @Test
    public void checkCityNameValidatorEmpty() {
        boolean result = validator.cityNameValidation("");

        assertThat(result).isFalse();
    }


    @Test
    public void checkCountryNameValidator(){
        boolean result = validator.countryNameValidation("Polska");

        assertThat(result).isNotNull()
                          .isTrue();
    }

    @Test
    public void checkCountryNameValidatorEmpty(){
        boolean result = validator.countryNameValidation("");

        assertThat(result).isFalse();
    }

    @Test
    public void checkRegionNameValidator(){
        boolean result = validator.regionNameValidation("Mazowieckie");

        assertThat(result).isNotNull()
                          .isTrue();
    }


    @Test
    public void checkRegionNameValidatorEmpty(){
        boolean result = validator.regionNameValidation("");

        assertThat(result).isFalse();
    }

    @Test
    public void checkLongitudeValidator(){
        boolean result = validator.validateLongitude("22.2");

        assertThat(result).isNotNull()
                .isTrue();
    }

    @Test
    public void checkLongitudeValidatorOverTheLimit(){
        boolean result = validator.validateLongitude("-181");

        assertThat(result).isNotNull()
                .isFalse();
    }

    @Test
    public void checkLatitudeValidator(){
        boolean result = validator.validateLatitude("89.87");

        assertThat(result).isNotNull()
                          .isTrue();
        }

    @Test
    public void checkLatitudeValidatorOverLimit(){
        boolean result = validator.validateLatitude("91.34");

        assertThat(result).isNotNull()
                           .isFalse();
    }

    }
