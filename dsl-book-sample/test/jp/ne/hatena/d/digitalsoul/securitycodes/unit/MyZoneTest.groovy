package jp.ne.hatena.d.digitalsoul.securitycodes.unit;

import groovy.util.GroovyTestCase;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.*;
import jp.ne.hatena.d.digitalsoul.securitycodes.dsl.*;

public class MyZoneTest extends GroovyTestCase {

    /* モデルのテスト */
    
    void testMinimumGrade() {
        MinimumGradeExpr expr = new MinimumGradeExpr(Grade.SENIOR_PROGRAMMER)
        assertTrue expr.eval(new Employee(grade:Grade.SENIOR_PROGRAMMER))
        assertTrue expr.eval(new Employee(grade:Grade.DIRECTOR))
        assertFalse expr.eval(new Employee(grade:Grade.JUNIOR_PROGRAMMER))
    }

    void testDepartmentExpr() {
        def expr = new DepartmentExpr("A")
        assertTrue expr.eval(new Employee(department:"A"))
        assertFalse expr.eval(new Employee(department:"B"))
    }

    void testAndExpr() {
        def expr = new AndExpr(new MinimumGradeExpr(Grade.SENIOR_PROGRAMMER), 
                new DepartmentExpr("A"))
        assertTrue expr.eval(new Employee(grade:Grade.SENIOR_PROGRAMMER, department:"A"))
        assertFalse expr.eval(new Employee(grade:Grade.JUNIOR_PROGRAMMER, department:"A"))
        assertFalse expr.eval(new Employee(grade:Grade.SENIOR_PROGRAMMER, department:"B"))
    }

    void testAllowRule(){
        def rule = new AllowRule(new DepartmentExpr("A"))
        assertEquals AdmissionRuleResult.ADMIT, rule.canAdmit(new Employee(department:"A"))
        assertEquals AdmissionRuleResult.NO_OPTION, rule.canAdmit(new Employee(department:"B"))
        
    }

    void testRefusalRule(){
        def rule = new RefusalRule(new DepartmentExpr("A"))
        assertEquals AdmissionRuleResult.NO_OPTION, rule.canAdmit(new Employee(department:"B"))
        assertEquals AdmissionRuleResult.REFUSE, rule.canAdmit(new Employee(department:"A"))        
    }
   
    void testZone() {
        Zone myZone = new Zone()
        myZone.addRule(new AllowRule(
                new AndExpr(
                        new MinimumGradeExpr(Grade.SENIOR_PROGRAMMER),
                        new DepartmentExpr("K9"))))

        assertTrue myZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER, 
                department:"K9"))

        assertFalse myZone.willAdmit(new Employee(grade:Grade.JUNIOR_PROGRAMMER,
                department:"K9"))

        assertFalse myZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER,
                department:"J1"))

    }

    /** DSLのテスト */

    void testZoneBuilder() {
        
        def testAllowZone = new Zone()
        new TestAllowZone().build(testAllowZone)

        assertTrue testAllowZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER,
                department:"K9"))
        assertFalse testAllowZone.willAdmit(new Employee(grade:Grade.JUNIOR_PROGRAMMER,
                department:"K9"))
        assertFalse testAllowZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER,
                department:"J9"))

                
        def testRefusalZone = new Zone()
        new TestRefusalZone().build(testRefusalZone)

        assertTrue testRefusalZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER,
                department:"K9"))
        assertFalse testRefusalZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER,
                department:"J9"))
    }

    void testMyZone() {

        def myZone = new Zone()
        new MyZone().build(myZone)

        assertTrue myZone.willAdmit(new Employee(grade:Grade.SENIOR_PROGRAMMER,
                department:"MF"))
        assertTrue myZone.willAdmit(new Employee(grade:Grade.DIRECTOR))
        assertFalse myZone.willAdmit(new Employee(grade:Grade.DIRECTOR, 
                department:"Finance"))
        assertFalse myZone.willAdmit(new Employee(grade:Grade.DIRECTOR, 
                department:"Audit"))
        
    }
}
