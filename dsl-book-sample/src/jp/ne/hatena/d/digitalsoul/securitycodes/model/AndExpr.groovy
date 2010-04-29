package jp.ne.hatena.d.digitalsoul.securitycodes.model;

public class AndExpr implements RuleElement {

    def rules

    AndExpr(RuleElement... rules) {
        this.rules = rules
    }

    boolean eval(Employee e) {
        boolean result = true
        rules.each { if(!it.eval(e)){ result = false } }
        result
    }

   

}
