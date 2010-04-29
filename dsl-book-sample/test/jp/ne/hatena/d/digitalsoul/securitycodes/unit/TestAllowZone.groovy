package jp.ne.hatena.d.digitalsoul.securitycodes.unit

import jp.ne.hatena.d.digitalsoul.securitycodes.dsl.ZoneBuilder;
import jp.ne.hatena.d.digitalsoul.securitycodes.model.Grade;

public class TestAllowZone  extends ZoneBuilder{

    void doBuild() {
        allow(
                gradeAtLeast(Grade.SENIOR_PROGRAMMER),
                department("K9"))
    }
    
}