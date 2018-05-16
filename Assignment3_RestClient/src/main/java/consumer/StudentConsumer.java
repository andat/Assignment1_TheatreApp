package consumer;

import client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;
import consumerContracts.IStudentConsumer;
import model.request.LoginModel;
import model.request.PasswordModel;
import model.request.StudentRequestModel;
import org.apache.http.entity.StringEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentConsumer implements IStudentConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Student> getAllStudents(LoginModel credentials){
        String url = "/students";
        List<Student> students = new ArrayList<>();
        try {
            String response = HttpClient.getRequest(url, credentials);
            Student[] studentArr = objectMapper.readValue(response, Student[].class);
            students = Arrays.asList(studentArr);
        } catch (IOException e){
            e.printStackTrace();
        }
       return students;
    }

    public boolean addStudent(StudentRequestModel stud, LoginModel credentials){
        boolean added = false;
        String url = "/students";
        try {
            String body = objectMapper.writeValueAsString(stud);
            added = HttpClient.postRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return added;
    }

    public boolean editStudent(StudentRequestModel stud, int id, LoginModel credentials){
        boolean edited = false;
        String url = "/students/" + id;
        try{
            String body = objectMapper.writeValueAsString(stud);
            edited = HttpClient.putRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return edited;
    }

    @Override
    public boolean deleteStudent(int id, LoginModel credentials) {
        String url = "/students/" + id;
        boolean deleted = false;
        try{
            deleted = HttpClient.deleteRequest(url, credentials);
        } catch(IOException e){
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public boolean changePassword(String password, LoginModel credentials) {
        boolean changed = false;
        //TODO put username
        int id = -1;
        String url = "/students/" + id + "/password";
        try{
            String body = objectMapper.writeValueAsString(new PasswordModel(password));
            changed = HttpClient.putRequest(url, new StringEntity(body), credentials);
        } catch (IOException e){
            e.printStackTrace();
        }
        return changed;
    }
}
