package com.example.testezipdin.service;

import com.example.testezipdin.config.JwtService;
import com.example.testezipdin.dto.ReleaseNotesUpdateRequest;
import com.example.testezipdin.entity.Release;
import com.example.testezipdin.dto.ReleaseCreateRequest;
import com.example.testezipdin.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReleaseService {
    @Autowired
    private ReleaseRepository repository;

    @Autowired
    private JwtService jwtService;

    public Map<String, Object> create(ReleaseCreateRequest req) {
        Release release = new Release();
        release.setSystem(req.system().trim());
        release.setVersion(req.version());
        release.setCommits(req.commits());
        release.setNotes(req.notes());
        release.setUser(req.user());
        release.setReleasedAt(LocalDateTime.now());

        Release saved = repository.save(release);
        return Map.of("id", saved.getId(), "message", "Release criado com sucesso.");
    }

    public Map<String, Object> getById(Long id) {
        Release release = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Release não encontrado"));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Release listado com sucesso.");
        response.put("id", release.getId());
        response.put("system", release.getSystem());
        response.put("version", release.getVersion());
        response.put("commits", release.getCommits());
        response.put("notes", release.getNotes());
        response.put("user", release.getUser());
        response.put("userUpdate", release.getUserUpdate());
        response.put("releasedAt", release.getReleasedAt());

        return response;
    }

    public Map<String, Object> updateNotes(Long id, ReleaseNotesUpdateRequest req, String token) {
        Release release = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Release não encontrado"));

        release.setNotes(req.notes());
        release.setUserUpdate(jwtService.extractUsername(token));
        repository.save(release);

        return Map.of("message", "Release atualizado com sucesso.");
    }

    public Map<String, Object> delete(Long id) {
        Release release = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Release não encontrado"));

        release.setDeletedAt(LocalDateTime.now());
        repository.save(release);

        return Map.of("message", "Release deletado com sucesso.");
    }

    public List<Release> test() {
        return repository.findAll();
    }
}

