package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public class MinimumGradeExpr implements RuleElement {

    Grade grade
    
    MinimumGradeExpr(Grade grade){
        this.grade = grade
    }

    boolean eval(Employee e){
        e.grade >= grade
    }

}
