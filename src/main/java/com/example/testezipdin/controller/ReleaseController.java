package com.example.testezipdin.controller;

import com.example.testezipdin.dto.ReleaseCreateRequest;
import com.example.testezipdin.dto.ReleaseNotesUpdateRequest;
import com.example.testezipdin.entity.Release;
import com.example.testezipdin.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReleaseCreateRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotes(
            @PathVariable Long id,
            @RequestBody ReleaseNotesUpdateRequest req,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(service.updateNotes(id, req, token.replace("Bearer ", "")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/test")
    public List<Release> test() {
        return service.test();
    }

}
