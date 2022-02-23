package com.personal.school.converter.impl;

import com.personal.school.converter.AbstractConverter;
import com.personal.school.converter.Converter;
import com.personal.school.form.PeopleForm;
import com.personal.school.model.Address;
import com.personal.school.model.People;
import com.personal.school.utils.FormatterUtils;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.function.Function;

import static com.personal.school.utils.FormatterUtils.getDefaultDateFormatter;
import static com.personal.school.utils.PropertyUtils.getNullProperties;
import static com.personal.school.utils.SetterUtils.setter;

public class PeopleConverter implements AbstractConverter<PeopleForm, People> {

    private final Converter<PeopleForm, Address> addressConverter;

    public PeopleConverter() {
        this.addressConverter = new AddressConverter();
    }

    @Override
    public People convert(People target, PeopleForm source) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));

        final Function<String, String> cpf = FormatterUtils::getCpfUnformat;
        final Function<String, LocalDate> birthDate = b -> LocalDate.parse(b, getDefaultDateFormatter());
        final Function<String, Address> address = a -> addressConverter.convert(source);

        setter(target::setCpf, source.getCpf(), cpf);
        setter(target::setBirthDate,  source.getBirthDate(), birthDate);
        setter(target::setAddress, source.getAddress(), address);

        return target;
    }
}
