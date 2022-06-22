package main.com.sg.classroster.dao;

import main.com.sg.classroster.dto.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student) {
        Student prevStudent = students.put(studentId, student);
        return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public Student getStudent(String studentId) {
        return null;
    }

    @Override
    public Student removeStudent(String studentId) {
        return null;
    }
}
