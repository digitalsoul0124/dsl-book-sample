package jp.ne.hatena.d.digitalsoul.promotion.dsl;

import jp.ne.hatena.d.digitalsoul.promotion.model.Condition;
import jp.ne.hatena.d.digitalsoul.promotion.model.EqualityCondition;

public class ConditionBuilder {

    RuleBuilder ruleBuilder

    String conditionName

    Condition condition

    // when
    ConditionBuilder getWhen() {
        this
    }

    // from
    ConditionBuilder getFrom() {
        conditionName = "from"
        this
    }

    // brand
    ConditionBuilder getBrand() {
        conditionName = "brand"
        this
    }

    // equals
    ConditionBuilder getEquals() {
        condition = new EqualityCondition()
        this
    }

    // and
    ConditionBuilder getAnd() {
        conditionName = null
        condition = null
        this
    }

    // value of condition
    def propertyMissing(String value) {
        condition.name = conditionName
        condition.value = value
        ruleBuilder.addCondition(condition)
        this
    }

}
