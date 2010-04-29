package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public abstract class AdmissionRule {

    RuleElement element

    AdmissionRule(RuleElement element){
        this.element = element
    }

    abstract AdmissionRuleResult canAdmit(Employee e)

}
