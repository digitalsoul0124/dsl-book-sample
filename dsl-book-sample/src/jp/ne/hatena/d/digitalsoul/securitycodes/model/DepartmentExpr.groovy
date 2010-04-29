package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public class DepartmentExpr implements RuleElement {

    def department

    DepartmentExpr(department) {
        this.department = department
    }

    boolean eval(Employee e) {
        e.department.equals department 
    }

}
