package steps;

import utils.DbUtils;
import utils.GlobalVariables;

import java.util.List;
import java.util.Map;

public class DbUtilTester {
    public static void main(String[] args) {
        GlobalVariables.tableData = DbUtils.getTableDataAsList("select emp_firstname, emp_middle_name,emp_lastname from hs_hr_employees ;");
        System.out.println(GlobalVariables.tableData);
    }
}
