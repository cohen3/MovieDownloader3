import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class RDdownload_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;
    public int s = 0;
    public static boolean internet = true;
    public static AtomicInteger p = new AtomicInteger(0);

    public RDdownload_State(CompositeOnInternetOn state, int region) {
        this.state = state;
        this.region = region;
        p = new AtomicInteger(0);
    }

    @Override
    public void Do()
    {
        //System.out.println("do "+(p.intValue() < 100)+", "+internet);
//        while(p.intValue() < 100 && internet)
////        {
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            s += MDownloader.speed;
////            p.set((int)(((double)s/(double)MDownloader.size)*100.0));
////            System.out.println(((double)s/(double)MDownloader.size)+" / 100 = "+ (s/MDownloader.size)/100.0+"\n"+p.intValue());
////        }
////        if(internet) {
////            s = 0;
////            CompositeOnInternetOn.po++;
////            handleEvent(Event.when);
////        }
        s += MDownloader.speed;
        p.set((int)(((double)s/(double)MDownloader.size)*100.0));
        if(p.intValue() == 20) System.out.println("done 20%, can now watch movie");
        if(p.intValue() >= 100) when();
    }

    @Override
    public void fileRequest() {

    }

    @Override
    public void downloadAborted() {
        CompositeOnInternetOn.po--;
        s = 0;
        p.set(0);
        EventListener.disk += MDownloader.size;
        state.setState(state.getState("download_idle", region), region);
    }

    @Override
    public void downloadError() {
        internet = false;
        //EventListener.disk += MDownloader.size;
        state.setState(state.getState("fix", region), region);
    }

    public void when() {
        CompositeOnInternetOn.po++;
        p.set(0);
        s = 0;
        state.setState(state.getState("download_idle", region), region);
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
        System.out.println("exit download state");
    }

    @Override
    public void Enter() {
        System.out.println("enter download state");
        internet = true;
        Thread t = new Thread( () -> Do());
        t.start();
    }

    @Override
    public void handleEvent(Event e) {
        try {
            Method m = this.getClass().getMethod(e.name());
            m.invoke(this);
        } catch (Exception e1) {
            //this.handleEvent(e);
            if(e == Event.continue_op)
                continue_op();
        }//System.out.println("Invalid Event!"); }
    }

    private void continue_op()
    {
        internet = true;
        Thread t = new Thread( () -> Do());
        t.start();
    }
}
