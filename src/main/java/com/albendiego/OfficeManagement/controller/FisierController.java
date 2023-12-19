package com.albendiego.OfficeManagement.controller;

import com.albendiego.OfficeManagement.service.CertificatUrbanismService;
import com.albendiego.OfficeManagement.service.FiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class FisierController {
    public final FiserService fisierService;
    public final CertificatUrbanismService certificatUrbanismService;


}
