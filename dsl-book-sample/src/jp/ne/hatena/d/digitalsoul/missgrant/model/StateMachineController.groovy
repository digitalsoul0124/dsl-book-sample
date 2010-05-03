package jp.ne.hatena.d.digitalsoul.missgrant.model;

public class StateMachineController {

    StateMachine stateMachine
    State currentState

    CommandChannel commandChannel = new CommandChannel()

    void handle(String commandCode) {
        if(currentState.hasTransition(commandCode)) {
            transitionTo currentState.targetState(commandCode)
        }
        // ignore unknown command
    }

    private void transitionTo(State targetState) {
        currentState = targetState
        currentState.executeActions commandChannel
    }
}
