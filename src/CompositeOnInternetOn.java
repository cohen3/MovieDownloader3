import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

public class CompositeOnInternetOn implements ICompositeOnState {

    // as a state variables
    OnState on;

    // as an object variables
    HashMap<String, IRegionStates>[] region;
    IRegionStates[] region_current;

    // operation variables
    boolean first = true;
    public static int po = 0;
    public boolean DoJob = true;

    public CompositeOnInternetOn(OnState on) {
        this.on = on;
        region = new HashMap[4];
        region[0] = new HashMap<>();
        region[1] = new HashMap<>();
        region[2] = new HashMap<>();
        region[3] = new HashMap<>();

        RDdownload_idle_State ds = new RDdownload_idle_State(this, 1);

        // region 0 - listen
        region[0].put("listen", new RLlisten_state(this, 0, ds));
        // region 1 - download
        region[1].put("download_idle", ds);
        region[1].put("download", new RDdownload_State(this, 1));
        region[1].put("fix", new RDfix_State(this, 1));
        // region 2 - watch
        region[2].put("watch_idle", new RWwatch_idle_State(this, 2));
        region[2].put("watch", new RWwatch_State(this, 2));
        region[2].put("hold", new RWhold_State(this, 2));
        // region 3 - hold
        region[3].put("beginner", new RPbeginner_State(this, 3));
        region[3].put("advanced", new RPadvanced_State(this, 3));
        region[3].put("pro", new RPpro_State(this, 3));

        // region current state
        region_current = new IRegionStates[4];
        region_current[0] = region_current[1] = region_current[2] = region_current[3] = null;
        first = true;
    }

    // methods as state
    @Override
    public void internetOff() {
        RDdownload_State.internet = false;
        on.setState(on.getInternetoff());
    }

    @Override
    public void internetOn() { }

    @Override
    public void Do() {
        while(DoJob)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            region_current[0].Do();
            region_current[1].Do();
            region_current[2].Do();
            region_current[3].Do();
        }
    }

    public IRegionStates getCurrentStateInRegion(int region) {
        return region_current[region];
    }

    @Override
    public void Enter() {
        System.out.println("enter internetOn state");
        if(first){
            first = false;
            setState(region[0].get("listen"),0);
            setState(region[1].get("download_idle"),1);
            setState(region[2].get("watch_idle"),2);
            setState(region[3].get("beginner"),3);
        }
        else
        {
            region_current[0].handleEvent(Event.continue_op);
            region_current[1].handleEvent(Event.continue_op);
            region_current[2].handleEvent(Event.continue_op);
            region_current[3].handleEvent(Event.continue_op);
        }
        DoJob = true;
    }

    @Override
    public void Exit() {
        DoJob = false;
        System.out.println("exit internetOn state");
    }

    public void stop(){ DoJob = false; }

    @Override
    public void handleEvent(Event e) {
        for(IRegionStates r : region_current) {
            try {
                Method m = r.getClass().getMethod(e.name());
                m.invoke(r);
            } catch (Exception e1) {
                r.handleEvent(e);
            }//System.out.println("Invalid Event!"); }
        }
    }

    // methods as object

    public void setState(IRegionStates s, int region)
    {
        if(region_current[region] != null) region_current[region].Exit();
        region_current[region] = s;
        region_current[region].Enter();
    }

    public IRegionStates getState(String name, int r)
    {
        return region[r].get(name);
    }
}
