package dao;

import entity.Appointment;
import entity.Patient;
import entity.Doctor;
import exception.PatientNumberNotFoundException;

import java.util.List;

public interface IHospitalService {
    Appointment getAppointmentById(int appointmentId);
    List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    boolean scheduleAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean cancelAppointment(int appointmentId);

    // New methods for adding patients and doctors
    boolean addPatient(Patient patient);
    boolean addDoctor(Doctor doctor);
}
