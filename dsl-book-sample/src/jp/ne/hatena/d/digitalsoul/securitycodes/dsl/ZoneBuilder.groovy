package jp.ne.hatena.d.digitalsoul.securitycodes.dsl;

import jp.ne.hatena.d.digitalsoul.securitycodes.model.AllowRule;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.Grade;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.MinimumGradeExpr;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.RefusalRule;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.RuleElement;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.DepartmentExpr;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.AndExpr;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.Zone;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.Grade;


public abstract class ZoneBuilder {

    Zone zone

    void build(Zone zone) {
        this.zone = zone
        doBuild()
    }

    abstract void doBuild()

    void allow(RuleElement... rules) {
        zone.addRule(new AllowRule(new AndExpr(rules)))
    }

    void refuse(RuleElement rule) {
        zone.addRule(new RefusalRule(new AndExpr(rule)))
    }

    RuleElement gradeAtLeast(Grade grade) {
        new MinimumGradeExpr(grade)
    }

    RuleElement department(department) {
        new DepartmentExpr(department)
    }
}
