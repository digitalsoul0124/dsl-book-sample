package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public class RefusalRule extends AdmissionRule {

    RefusalRule(RuleElement element) {
        super(element)
    }
    
    AdmissionRuleResult canAdmit(Employee e){
        element.eval(e) ? AdmissionRuleResult.REFUSE : AdmissionRuleResult.NO_OPTION
    }

}