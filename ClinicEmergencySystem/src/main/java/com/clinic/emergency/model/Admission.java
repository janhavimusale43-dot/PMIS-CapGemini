package com.clinic.emergency.model;

import java.time.LocalDateTime;

public record Admission(int admissionId, int patientId, String patientName,
                        int wardId, String wardName, String attendingDoctor,
                        String status, LocalDateTime admittedAt, LocalDateTime dischargedAt) {}
