package com.clinic.emergency.service;

import com.clinic.emergency.dao.AdmissionDAO;
import com.clinic.emergency.dao.PatientDAO;
import com.clinic.emergency.dao.WardDAO;
import com.clinic.emergency.exception.BedUnavailableException;
import com.clinic.emergency.model.Admission;
import com.clinic.emergency.model.Patient;
import com.clinic.emergency.model.TriageLevel;
import com.clinic.emergency.model.Ward;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClinicService {
    private final PatientDAO patientDAO;
    private final WardDAO wardDAO;
    private final AdmissionDAO admissionDAO;

    public ClinicService(PatientDAO patientDAO, WardDAO wardDAO, AdmissionDAO admissionDAO) {
        this.patientDAO = patientDAO;
        this.wardDAO = wardDAO;
        this.admissionDAO = admissionDAO;
    }

    public int registerPatient(String name, int age, TriageLevel triageLevel) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Patient name cannot be empty.");
        if (age < 0 || age > 125)
            throw new IllegalArgumentException("Enter a valid patient age (0-125).");
        if (triageLevel == null)
            throw new IllegalArgumentException("Triage level is required.");
        return patientDAO.create(name.trim(), age, triageLevel);
    }

    public List<Patient> getWaitingPatients() {
        return patientDAO.findWaitingPatients();
    }

    public List<Ward> getAllWards() {
        return wardDAO.findAll();
    }

    public List<Admission> getAllAdmissions() {
        return admissionDAO.findAll();
    }

    @Transactional
    public int admitPatient(int patientId, int wardId, String doctor) {
        patientDAO.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + patientId));

        if (doctor == null || doctor.isBlank())
            throw new IllegalArgumentException("Attending doctor is required.");
        if (admissionDAO.hasActiveAdmission(patientId))
            throw new IllegalStateException("Patient already has an active admission.");

        // Lock the ward row to prevent two requests from allocating its last bed.
        Ward ward = wardDAO.findByIdForUpdate(wardId)
                .orElseThrow(() -> new IllegalArgumentException("Ward not found: " + wardId));

        if (ward.availableBeds() <= 0)
            throw new BedUnavailableException("No beds available in " + ward.wardName() + ".");

        // These database changes are committed together or rolled back together.
        if (wardDAO.incrementOccupiedBeds(wardId) != 1)
            throw new BedUnavailableException("Bed allocation failed. Please try again.");

        return admissionDAO.create(patientId, wardId, doctor.trim());
    }

    @Transactional
    public void dischargePatient(int admissionId) {
        Admission admission = admissionDAO.findActiveByIdForUpdate(admissionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Active admission not found: " + admissionId));

        if (admissionDAO.discharge(admissionId) != 1)
            throw new IllegalStateException("Could not discharge admission.");

        if (wardDAO.decrementOccupiedBeds(admission.wardId()) != 1)
            throw new IllegalStateException("Ward bed count could not be safely updated.");
    }
}
