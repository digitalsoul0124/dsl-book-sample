package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public class Zone {

    def rules = [] 

    void addRule(AdmissionRule rule) {
        rules.add rule
    }

    boolean willAdmit(Employee e) {
        boolean result = false
        for(rule in rules) { 
            switch(rule.canAdmit(e)) {
                case AdmissionRuleResult.ADMIT:
                    result = true
                    break
                case AdmissionRuleResult.REFUSE:
                    return false
                case AdmissionRuleResult.NO_OPTION:
                    break
                default:
                    throw new IllegalStateException()
            }
            
        }
        result
    }

}
