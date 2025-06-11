package com.example.testezipdin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record ReleaseCreateRequest(
        @NotBlank String system,
        @NotBlank String version,
        @NotEmpty List<String> commits,
        String notes,
        @NotBlank String user
) {}
