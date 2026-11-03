package com.clinic.emergency.console;

import com.clinic.emergency.model.Admission;
import com.clinic.emergency.model.Patient;
import com.clinic.emergency.model.TriageLevel;
import com.clinic.emergency.model.Ward;
import com.clinic.emergency.service.ClinicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class ClinicConsole implements CommandLineRunner {
    private final ClinicService clinicService;

    public ClinicConsole(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n=== CLINIC EMERGENCY BED & OPD TRIAGE SYSTEM ===");
            boolean running = true;
            while (running) {
                printMenu();
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> registerPatient(scanner);
                        case "2" -> showWaitingPatients();
                        case "3" -> showWards();
                        case "4" -> admitPatient(scanner);
                        case "5" -> dischargePatient(scanner);
                        case "6" -> showAdmissions();
                        case "7" -> showOccupancy();
                        case "0" -> {
                            running = false;
                            System.out.println("Goodbye!");
                        }
                        default -> System.out.println("Invalid option.");
                    }
                } catch (Exception ex) {
                    System.out.println("Operation failed: " + ex.getMessage());
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--------------- MAIN MENU ----------------");
        System.out.println("1. Register patient");
        System.out.println("2. View priority waiting queue");
        System.out.println("3. View wards and bed availability");
        System.out.println("4. Admit patient");
        System.out.println("5. Discharge patient");
        System.out.println("6. View admission history");
        System.out.println("7. Ward occupancy analytics");
        System.out.println("0. Exit");
    }

    private void registerPatient(Scanner scanner) {
        System.out.print("Patient name: ");
        String name = scanner.nextLine();
        int age = readInt(scanner, "Patient age: ");
        TriageLevel level = readTriageLevel(scanner);
        int id = clinicService.registerPatient(name, age, level);
        System.out.println("Patient registered. ID: " + id);
    }

    private void showWaitingPatients() {
        List<Patient> patients = clinicService.getWaitingPatients();
        System.out.println("\n--- PRIORITY WAITING QUEUE ---");
        if (patients.isEmpty()) {
            System.out.println("No waiting patients.");
            return;
        }
        patients.forEach(p -> System.out.printf(
                "ID: %d | %s | Age: %d | %s | Registered: %s%n",
                p.patientId(), p.name(), p.age(), p.triageLevel(), p.registeredAt()));
    }

    private void showWards() {
        System.out.println("\n--- WARD AVAILABILITY ---");
        clinicService.getAllWards().forEach(w -> System.out.printf(
                "ID: %d | %-12s | Total: %d | Occupied: %d | Available: %d%n",
                w.wardId(), w.wardName(), w.totalBeds(), w.occupiedBeds(), w.availableBeds()));
    }

    private void admitPatient(Scanner scanner) {
        showWaitingPatients();
        int patientId = readInt(scanner, "Patient ID to admit: ");
        showWards();
        int wardId = readInt(scanner, "Ward ID: ");
        System.out.print("Attending doctor: ");
        String doctor = scanner.nextLine();
        int admissionId = clinicService.admitPatient(patientId, wardId, doctor);
        System.out.println("Admission successful. Admission ID: " + admissionId);
    }

    private void dischargePatient(Scanner scanner) {
        showAdmissions();
        int id = readInt(scanner, "Active admission ID to discharge: ");
        clinicService.dischargePatient(id);
        System.out.println("Patient discharged. Bed released.");
    }

    private void showAdmissions() {
        System.out.println("\n--- ADMISSION HISTORY ---");
        List<Admission> admissions = clinicService.getAllAdmissions();
        if (admissions.isEmpty()) {
            System.out.println("No admissions found.");
            return;
        }
        admissions.forEach(a -> System.out.printf(
                "Admission: %d | Patient: %s (ID %d) | Ward: %s | Doctor: %s | Status: %s%n",
                a.admissionId(), a.patientName(), a.patientId(),
                a.wardName(), a.attendingDoctor(), a.status()));
    }

    private void showOccupancy() {
        System.out.println("\n--- WARD OCCUPANCY ANALYTICS ---");
        for (Ward w : clinicService.getAllWards()) {
            System.out.printf("%-12s | Occupied: %d/%d | Available: %d | Utilization: %.2f%%%n",
                    w.wardName(), w.occupiedBeds(), w.totalBeds(),
                    w.availableBeds(), w.utilizationPercentage());
        }
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private TriageLevel readTriageLevel(Scanner scanner) {
        while (true) {
            System.out.print("Triage level (CRITICAL / URGENT / NORMAL): ");
            try {
                return TriageLevel.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid triage level. Try again.");
            }
        }
    }
}
