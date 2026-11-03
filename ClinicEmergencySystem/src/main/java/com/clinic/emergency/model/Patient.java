package com.clinic.emergency.model;

import java.time.LocalDateTime;

public record Patient(int patientId, String name, int age,
                      TriageLevel triageLevel, LocalDateTime registeredAt) {}
