package com.personal.school.service.impl;

import com.google.gson.Gson;
import com.personal.school.exception.NotFoundException;
import com.personal.school.model.Address;
import com.personal.school.service.AddressService;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultAddressService implements AddressService {

    private static final String URL = "https://viacep.com.br/ws/%s/json";

    @Override
    public Address getAddressByZipCode(String zipCode) {
        String url = String.format(URL, zipCode);
        try {
            final URI uri = new URI(url);

            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final ViaCep viaCep = getViaCep(zipCode, response.body());

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
