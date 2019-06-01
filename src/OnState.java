import java.lang.reflect.Method;

public class OnState implements IMachineState {

    // as state variables
    MDownloader machine;

    // as object variables
    ICompositeOnState internetoff;
    ICompositeOnState interneton;

    ICompositeOnState current;


    public OnState(MDownloader machine) {
        this.machine = machine;
        internetoff = new CompositeOnInternetOff(this);
        interneton = new CompositeOnInternetOn(this);
        current = null;
    }

    @Override
    public void turnOn() { }

    @Override
    public void turnOff() {
        machine.setState(machine.getOffState());
    }

    @Override
    public void Do() {
        current.Do();
    }

    @Override
    public void Enter() {
        System.out.println("enter on state");
        setState(internetoff);
    }

    @Override
    public void Exit() {
        System.out.println("exit on state");
    }

    @Override
    public void handleEvent(Event e) {
        try {
            Method m = current.getClass().getMethod(e.name());
            m.invoke(current);
        }
        catch(Exception e1) { current.handleEvent(e); }//System.out.println("Invalid Event!"); }
    }

    @Override
    public void stop() {
        current.stop();
    }

    // as object methods

    public void setState(ICompositeOnState s)
    {
        if(current != null) current.Exit();
        current = s;
        current.Enter();
    }

    public ICompositeOnState getInternetoff() {
        return internetoff;
    }

    public ICompositeOnState getInterneton() {
        return interneton;
    }
}
