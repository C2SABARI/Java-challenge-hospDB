package mainmod;

import dao.HospitalServiceImpl;
import entity.Appointment;
import entity.Patient;
import entity.Doctor;
import exception.PatientNumberNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        HospitalServiceImpl service = new HospitalServiceImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Hospital Management System");

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Get Appointment by ID");
            System.out.println("5. Get Appointments for Patient");
            System.out.println("6. Get Appointments for Doctor");
            System.out.println("7. Update Appointment");
            System.out.println("8. Cancel Appointment");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add Patient
                        System.out.print("Enter Patient ID: ");
                        int patientId = sc.nextInt();
                        sc.nextLine();  // consume newline
                        System.out.print("Enter First Name: ");
                        String firstName = sc.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = sc.nextLine();
                        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                        String dob = sc.nextLine();
                        System.out.print("Enter Gender (M/F): ");
                        String gender = sc.nextLine();
                        System.out.print("Enter Contact Number: ");
                        String contact = sc.nextLine();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        Patient patient = new Patient(patientId, firstName, lastName, dob, gender, contact, address);
                        boolean patientAdded = service.addPatient(patient);
                        System.out.println(patientAdded ? "Patient Added." : "Failed to Add Patient.");
                        break;

                    case 2:
                        // Add Doctor
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = sc.nextInt();
                        sc.nextLine();  // consume newline
                        System.out.print("Enter First Name: ");
                        String doctorFirstName = sc.nextLine();
                        System.out.print("Enter Last Name: ");
                        String doctorLastName = sc.nextLine();
                        System.out.print("Enter Specialization: ");
                        String specialization = sc.nextLine();
                        System.out.print("Enter Contact Number: ");
                        String doctorContact = sc.nextLine();

                        Doctor doctor = new Doctor(doctorId, doctorFirstName, doctorLastName, specialization, doctorContact);
                        boolean doctorAdded = service.addDoctor(doctor);
                        System.out.println(doctorAdded ? "Doctor Added." : "Failed to Add Doctor.");
                        break;

                    case 3:
                        // Schedule Appointment
                        System.out.print("Enter Patient ID: ");
                        int pid = sc.nextInt();
                        System.out.print("Enter Doctor ID: ");
                        int did = sc.nextInt();
                        sc.nextLine();  // consume newline
                        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();

                        Appointment a = new Appointment(0, pid, did, date, desc);
                        boolean scheduled = service.scheduleAppointment(a);
                        System.out.println(scheduled ? "Appointment Scheduled." : "Failed to schedule.");
                        break;

                    case 4:
                        // Get Appointment by ID
                        System.out.print("Enter Appointment ID: ");
                        int apptId = sc.nextInt();
                        Appointment appt = service.getAppointmentById(apptId);
                        System.out.println(appt != null ? appt : "No appointment found.");
                        break;

                    case 5:
                        // Get Appointments for Patient
                        System.out.print("Enter Patient ID: ");
                        int patientId2 = sc.nextInt();
                        try {
                            List<Appointment> plist = service.getAppointmentsForPatient(patientId2);
                            if (plist.isEmpty()) {
                                System.out.println("No appointments found.");
                            } else {
                                plist.forEach(System.out::println);
                            }
                        } catch (PatientNumberNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 6:
                        // Get Appointments for Doctor
                        System.out.print("Enter Doctor ID: ");
                        int doctorId2 = sc.nextInt();
                        List<Appointment> dlist = service.getAppointmentsForDoctor(doctorId2);
                        dlist.forEach(System.out::println);
                        break;

                    case 7:
                        // Update Appointment
                        System.out.print("Enter Appointment ID to update: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter new Patient ID: ");
                        int newPid = sc.nextInt();
                        System.out.print("Enter new Doctor ID: ");
                        int newDid = sc.nextInt();
                        sc.nextLine();  // consume newline
                        System.out.print("Enter new Date (YYYY-MM-DD): ");
                        String newDate = sc.nextLine();
                        System.out.print("Enter new Description: ");
                        String newDesc = sc.nextLine();
                        Appointment update = new Appointment(updateId, newPid, newDid, newDate, newDesc);
                        boolean updated = service.updateAppointment(update);
                        System.out.println(updated ? "Updated successfully." : "Update failed.");
                        break;

                    case 8:
                        // Cancel Appointment
                        System.out.print("Enter Appointment ID to cancel: ");
                        int cancelId = sc.nextInt();
                        boolean canceled = service.cancelAppointment(cancelId);
                        System.out.println(canceled ? "Cancelled successfully." : "Cancel failed.");
                        break;

                    case 9:
                        // Exit
                        System.out.println("Exiting... Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
    }
}
