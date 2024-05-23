package org.example.controllers;

import org.example.dto.Day;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/test")
public class ExampleControllerSecuredByAnnotation {

    @GetMapping("/test1")
    public ResponseEntity<String> test1() {

     return ResponseEntity.status(HttpStatus.CREATED).body("test1");
    }

    /*
        @EnableGlobalMethodSecurity(securedEnabled = true) permet de spécifier le role nécéssaire directement
        au niveau de la route

     */

    @GetMapping("/test2")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> test2() {
        return ResponseEntity.status(HttpStatus.CREATED).body("test2");
    }

    @GetMapping("/test3")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
    public ResponseEntity<String> test3() {
        return ResponseEntity.status(HttpStatus.CREATED).body("test3");
    }


}
