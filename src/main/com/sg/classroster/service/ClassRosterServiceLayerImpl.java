package main.com.sg.classroster.service;

import main.com.sg.classroster.dao.ClassRosterDao;
import main.com.sg.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    ClassRosterDao data;

    public ClassRosterServiceLayerImpl(ClassRosterDao data) {
        this.data = data;
    }

    @Override
    public void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {

        // Check if student already exists (duplicate check)
        if (data.getStudent(student.getStudentId()) != null){
            throw new ClassRosterDuplicateIdException("ERROR: Could not create student"
            + "Student ID " + student.getStudentId() + " already exists.");
        }

        // All required fields check
        validateStudentData(student);

        // If no exceptions run, add to file
        data.addStudent(student.getStudentId(), student);
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return data.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return data.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        return data.removeStudent(studentId);
    }

    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException("ERROR: All fields [First name, Last name, Cohort] are required");
        }
    }
}
