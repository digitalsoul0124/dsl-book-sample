package jp.ne.hatena.d.digitalsoul.missgrant.dsl;

import jp.ne.hatena.d.digitalsoul.missgrant.model.Command;
import jp.ne.hatena.d.digitalsoul.missgrant.model.Event;
import jp.ne.hatena.d.digitalsoul.missgrant.model.State;

public class StateBuilder implements IAbstractEventBuilder, IStateBuilder {

    State state

    Event trigger
    
    public StateBuilder(String name) {
        state = new State(name:name)
    }

    IAbstractEventBuilder actions(Command... actions) {
        actions.each { state.addAction(it) }
        this
    }

    IStateBuilder when(Event trigger) {
        this.trigger = trigger
        this
    }

    IAbstractEventBuilder then(StateBuilder target) {
        state.addTransition(trigger, target.state)
        this
    }
}
