package jp.ne.hatena.d.digitalsoul.missgrant.dsl;

import jp.ne.hatena.d.digitalsoul.missgrant.model.Command;
import jp.ne.hatena.d.digitalsoul.missgrant.model.Event;
import jp.ne.hatena.d.digitalsoul.missgrant.model.StateMachineController;
import jp.ne.hatena.d.digitalsoul.missgrant.model.StateMachine;


public abstract class StateMachineBuilder {

    StateMachineController controller

    def states = [:] as Map<String, StateBuilder>

    void build(StateMachineController controller) {
        this.controller = controller
        defineStateMachine()
    }

    abstract void defineStateMachine()

    Event event(String name, String code) {
        new Event(name:name, code:code)
    }

    Command command(String name, String code ) {
        new Command(name:name, code:code)
    }

    StateBuilder state(String name) {
        if(!states.containsKey(name)) { states[name] = new StateBuilder(name) }
        states[name]
    }

    void defaultState(String name) {
        controller.stateMachine = new StateMachine(defaultState:states[name].state)
    }

    void startState(String name) {
        controller.currentState = states[name].state
    }

}
