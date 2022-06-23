package main.com.sg.classroster.controller;

import main.com.sg.classroster.dao.ClassRosterDao;
import main.com.sg.classroster.dao.ClassRosterDaoFileImpl;
import main.com.sg.classroster.dto.Student;
import main.com.sg.classroster.ui.ClassRosterView;
import main.com.sg.classroster.ui.UserIO;
import main.com.sg.classroster.ui.UserIOConsoleImpl;

import java.util.List;

public class ClassRosterController {

    private ClassRosterView view = new ClassRosterView();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {

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

    private void createStudent() {
        Student newStudent = view.getNewStudentInfo();
        view.displayCreateStudentBanner();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
    }

    private void listStudents(){
        List<Student> studentList = dao.getAllStudents();
        view.displayDisplayAllBanner();
        view.displayStudentList(studentList);
    }

    private void viewStudent(){
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent(){
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
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