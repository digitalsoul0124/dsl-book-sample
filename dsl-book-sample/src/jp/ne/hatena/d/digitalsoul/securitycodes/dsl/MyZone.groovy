package jp.ne.hatena.d.digitalsoul.securitycodes.dsl;

import jp.ne.hatena.d.digitalsoul.securitycodes.model.Grade;

public class MyZone extends ZoneBuilder {
    
    void doBuild() {
        allow(
                gradeAtLeast(Grade.SENIOR_PROGRAMMER),
                department("MF"))
        refuse(department("Finance"))
        refuse(department("Audit"))
        allow(
                gradeAtLeast(Grade.DIRECTOR))
    }
}
