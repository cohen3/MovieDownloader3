public interface IMachineState {
    void turnOn();
    void turnOff();
    void Do();
    void Enter();
    void Exit();
    void handleEvent(Event e);
}
