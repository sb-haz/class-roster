package main.com.sg.classroster.controller;

import main.com.sg.classroster.dao.ClassRosterDao;
import main.com.sg.classroster.dto.Student;
import main.com.sg.classroster.service.ClassRosterDataValidationException;
import main.com.sg.classroster.service.ClassRosterDuplicateIdException;
import main.com.sg.classroster.service.ClassRosterPersistenceException;
import main.com.sg.classroster.service.ClassRosterServiceLayer;
import main.com.sg.classroster.ui.ClassRosterView;

import java.util.List;

public class ClassRosterController {

    private ClassRosterView view;
    //private ClassRosterDao data;
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws ClassRosterPersistenceException {

        boolean keepGoing = true;
        int menuSelection;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listStudents() throws ClassRosterPersistenceException {
        List<Student> studentList = service.getAllStudents();
        view.displayDisplayAllBanner();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }

    // Unknown and Exit
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}