package jp.ne.hatena.d.digitalsoul.promotion.dsl;

import jp.ne.hatena.d.digitalsoul.promotion.model.Condition;
import jp.ne.hatena.d.digitalsoul.promotion.model.Itinerary;
import jp.ne.hatena.d.digitalsoul.promotion.model.PromotionRule;

public class RuleBuilder {

    Itinerary itinerary = new Itinerary()

    PromotionRule rule

    RuleBuilder getScore() {
        rule = new PromotionRule()
        itinerary.rules.add rule
        this
    }

    void addCondition(Condition condition) {
        rule.conditions.add condition
    }

    // value of score
    def propertyMissing(String name) { 
        if('_' != name.charAt(0)) {
            throw new IllegalStateException()
        }
        
        rule.score = Integer.valueOf(name.substring(1))

        new ConditionBuilder(ruleBuilder:this)
    }

}
