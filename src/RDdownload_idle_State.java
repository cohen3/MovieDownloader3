import java.lang.reflect.Method;

public class RDdownload_idle_State implements IRegionStates, Observer {

    CompositeOnInternetOn state;
    int region;

    public RDdownload_idle_State(CompositeOnInternetOn state, int region) {
        this.state = state;
        this.region = region;
    }

    @Override
    public void fileRequest() {

    }

    @Override
    public void downloadAborted() {

    }

    @Override
    public void downloadError() {

    }

    @Override
    public void errorFixed() {

    }

    @Override
    public void movieOn() {

    }

    @Override
    public void restartMovie() {

    }

    @Override
    public void holdMovie() {

    }

    @Override
    public void movieOff() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void Exit() {
        System.out.println("exit download_idle state");
    }

    @Override
    public void Enter() {
        MDownloader.d = false;
        System.out.println("enter download_idle state");
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void Do() { }

    public void when()
    {
        if(!MDownloader.d) return;
        state.setState(state.getState("download", region), region);
    }

    @Override
    public void update(Event e) {
        try {
            Method m = this.getClass().getMethod(e.name());
            m.invoke(this);
        }
        catch(Exception e1) { this.handleEvent(e); }//System.out.println("Invalid Event!"); }
    }
}
