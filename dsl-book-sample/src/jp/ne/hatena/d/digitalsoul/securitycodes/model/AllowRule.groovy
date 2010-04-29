package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public class AllowRule extends AdmissionRule {

    AllowRule(RuleElement element) {
        super(element)
    }
    
    AdmissionRuleResult canAdmit(Employee e){
        element.eval(e) ? AdmissionRuleResult.ADMIT : AdmissionRuleResult.NO_OPTION
    }

}
