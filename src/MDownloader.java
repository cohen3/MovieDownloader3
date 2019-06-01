import java.lang.reflect.Method;

public class MDownloader implements Observer{

    IMachineState offState;
    IMachineState onState;

    IMachineState current;
    public boolean doJob;
    public static boolean d = false;
    public static int size = 60;
    public static double speed = 1;

    public MDownloader() {
        offState = new OffState(this);
        onState = new OnState(this);
        setState(offState);
        doJob = true;
    }

    public IMachineState getOffState() {
        return offState;
    }

    public IMachineState getOnState() {
        return onState;
    }

    public void setState(IMachineState s) {
        if(current != null) current.Exit();
        current = s;
        current.Enter();
    }

    public void handleEvent(Event e)
    {
        try {
            Method m = current.getClass().getMethod(e.name());
            m.invoke(current);
        }
        catch(Exception e1) { current.handleEvent(e); }//System.out.println("Invalid Event!"); }
    }

    public void Do()
    {
        while(doJob)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current.Do();
        }
    }

    @Override
    public void update(Event e) {
        this.handleEvent(e);
    }

    public void Exit() {
        doJob = false;
    }
}
