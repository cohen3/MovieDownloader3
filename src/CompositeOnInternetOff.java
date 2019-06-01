public class CompositeOnInternetOff implements ICompositeOnState {

    OnState on;

    public CompositeOnInternetOff(OnState on) {
        this.on = on;
    }

    @Override
    public void internetOff() { }

    @Override
    public void internetOn() {
        on.setState(on.getInterneton());
    }

    @Override
    public void Do() { }

    @Override
    public void Enter() {
        System.out.println("enter internetOff state");
    }

    @Override
    public void Exit() {
        System.out.println("exit internetOff state");
    }

    @Override
    public void handleEvent(Event e) { }
}
