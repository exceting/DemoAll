package demo.spring.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/get/student")
    public Response<Student> getStudent() {
        Response<Student> response = new Response<>();
        Student student = new Student();
        student.setId(1);
        student.setAge(18);
        student.setName("Tom");
        response.setCode(0);
        response.setData(student);
        response.setMessage("success");
        return response;
    }

    @Bean
    public String hhhhh() {
        return "hhhhh";
    }

    static class Response<T> {
        private int code;
        private T data;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    static class Student {

        private int id;

        private int age;

        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
