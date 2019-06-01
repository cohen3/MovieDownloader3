public class OffState implements IMachineState {

    MDownloader machine;

    public OffState(MDownloader machine) {
        this.machine = machine;
    }

    @Override
    public void turnOn() {
        machine.setState(machine.getOnState());
    }

    @Override
    public void turnOff() { }

    @Override
    public void Do() { }

    @Override
    public void Enter() {
        System.out.println("enter off state");
    }

    @Override
    public void Exit() {
        System.out.println("exit off state");
    }

    @Override
    public void handleEvent(Event e) { }

    @Override
    public void stop() { }
}
