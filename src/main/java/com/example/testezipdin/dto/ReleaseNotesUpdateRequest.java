package com.example.testezipdin.dto;


import jakarta.validation.constraints.NotBlank;

public record ReleaseNotesUpdateRequest(@NotBlank String notes) {}
