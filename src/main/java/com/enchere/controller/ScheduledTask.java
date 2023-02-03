package com.enchere.controller;

import com.enchere.model.enchere.Enchere;
import com.enchere.service.enchere.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.enchere.util.ControllerUtil.returnSuccess;

@Component
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private EnchereService service;

    @Scheduled(fixedRate = 60000)
    public void scheduleTaskWithFixedRate() {
//        service.begin();
//        List<Enchere> encheres = service.getJusteTermine();
    }
}

