package jp.ne.hatena.d.digitalsoul.missgrant.model;

public class State {

    String name
    def transitions = [:] as Map<String, Transition>
    def actions = [] as List<Command>

    void addTransition(Event trigger, State target) {
        transitions.put trigger.code, new Transition(source:this, trigger:trigger, target:target)
    }

    void addAction(Command action) {
        actions.add action
    }

    boolean hasTransition(String commandCode) {
        transitions.containsKey commandCode
    }

    State targetState(String commandCode) {
        transitions[commandCode].target
    }

    void executeActions(CommandChannel commandChannel) {
        actions.each { commandChannel.send it.code }
    }

}
