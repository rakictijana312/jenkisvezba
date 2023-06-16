package utils;

import org.json.JSONObject;

public class apiPayloadConstants {

    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Whooptie\",\n" +
                "  \"emp_lastname\": \"Movie\",\n" +
                "  \"emp_middle_name\": \"blueCheese\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2021-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}";
        return createEmployeePayload;
    }

    /*
    * another way to upload a payload
    */

    public static String createEmployeeBody(){

        /*
         * We imported a dependency for JSONObject
         */

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Whooptie");
        obj.put("emp_lastname", "Movie");
        obj.put("emp_middle_name", "blueCheese");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2021-07-10");
        obj.put("emp_status", "Employee");
        obj.put("emp_job_title", "Cloud Consultant");

        return obj.toString();

    }

    public static String createEmployeeBodyMoreDynamic(String firstName, String lastName, String middleName, String gender, String employeeBday,
                                                       String employeeStatus, String employeeJobTitle){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", employeeBday);
        obj.put("emp_status", employeeStatus);
        obj.put("emp_job_title", employeeJobTitle);

        return obj.toString();
    }
}
