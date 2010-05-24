package jp.ne.hatena.d.digitalsoul.promotion.dsl;

import jp.ne.hatena.d.digitalsoul.promotion.model.Itinerary;

public class RuleEvaluator {

    private RuleBuilder builder = new RuleBuilder()

    void rule(String rule) {
        Eval.x(builder, polish(rule))
    }

    String polish(String rule) {
        String result = rule.replaceAll(" ", ".")
        result = result.replaceAll("score.", "score._")
        result = "x." + result
        println result
        result
    }

    Itinerary getItinerary() {
        builder.itinerary
    }

}
