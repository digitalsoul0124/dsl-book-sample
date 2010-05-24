package jp.ne.hatena.d.digitalsoul.promotion.dsl;

import jp.ne.hatena.d.digitalsoul.promotion.model.Condition;
import jp.ne.hatena.d.digitalsoul.promotion.model.Itinerary;
import jp.ne.hatena.d.digitalsoul.promotion.model.PromotionRule;

public class RuleBuilder {

    Itinerary itinerary = new Itinerary()

    PromotionRule rule

    // score
    RuleBuilder getScore() {
        rule = new PromotionRule()
        itinerary.rules.add rule
        this
    }

    void addCondition(Condition condition) {
        rule.conditions.add condition
    }

    // value of score
    def propertyMissing(String value) { 
        if('_' != value.charAt(0)) {
            throw new IllegalStateException()
        }
        
        rule.score = Integer.valueOf(value.substring(1))

        new ConditionBuilder(ruleBuilder:this)
    }

}
