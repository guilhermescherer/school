package com.personal.school.service.impl;

import com.google.gson.Gson;
import com.personal.school.exception.NotFoundException;
import com.personal.school.model.Address;
import com.personal.school.service.AddressService;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.personal.school.utils.ConverterUtils.getStringByBufferedReader;

public class DefaultAddressService implements AddressService {

    private static final String URL = "https://viacep.com.br/ws/%s/json";

    @Override
    public Address getAddressByZipCode(String zipCode) {
        String callUrl = String.format(URL, zipCode);
        try {
            URL url = new URL(callUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String json = getStringByBufferedReader(response);

            final ViaCep viaCep = getViaCep(zipCode, json);

            return getAddress(viaCep);
        } catch (Exception e) {
            throw new NotFoundException("Not found CEP " + zipCode);
        }
    }

    private Address getAddress(ViaCep viaCep) {
        Address address = new Address();
        address.setZipCode(viaCep.getCep());
        address.setCity(viaCep.getLocalidade());
        address.setCountry(viaCep.getUf());
        return address;
    }

    private ViaCep getViaCep(String zipCode, String json) {
        Gson gson = new Gson();
        final ViaCep viaCep = gson.fromJson(json, ViaCep.class);

        if (viaCep.isError() != null && viaCep.isError()) {
            throw new NotFoundException("Not found CEP " + zipCode);
        }

        return viaCep;
    }

    @Getter
    @Setter
    private class ViaCep {
        private String uf;
        private String localidade;
        private String cep;
        private Boolean erro;

        private Boolean isError(){
            return erro;
        }
    }
}
