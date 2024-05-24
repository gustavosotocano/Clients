package com.gml.util;

import com.gml.dto.ClienteDto;
import com.gml.entity.Client;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
public class Utils {
    public static Client toClient(ClienteDto clienteDto) {
        return Client.builder()
                .sharedKey(clienteDto.getSharedKey())
                .bussinessId(clienteDto.getBussinessId())
                .email(clienteDto.getEmail())
                .phone(clienteDto.getPhone())
                .added(clienteDto.getAdded())
                .started(clienteDto.getStarted())
                .ended(clienteDto.getEnded())

                .build();
    }

    public static Client toClientUpdate(ClienteDto from ,Client to) {
        to.setEnded(from.getEnded());
        to.setEmail(from.getEmail());
        to.setPhone(from.getPhone());
        to.setStarted(from.getStarted());
        to.setBussinessId(from.getBussinessId());
        to.setUpdated(new Date());
        return to;
    }

    public static ClienteDto toClientDto(Client client) {


        return ClienteDto.builder()
                .sharedKey(client.getSharedKey())
                .bussinessId(client.getBussinessId())
                .email(client.getEmail())
                .phone(client.getPhone())
                .added(changeFormatDate(client.getAdded()))
                .started(changeFormatDate(client.getStarted()))
                .ended(client.getEnded())

                .build();
    }

    private static Date changeFormatDate(Date date) {

        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM-dd-yyyy");

        try {
            date = outputFormat.parse(date.toString());
            log.error("Parsed Date: {}", date);
        } catch (ParseException e) {
            log.error("Error parsing date: {} " , e.getMessage());
        }

        return date;
    }
}
