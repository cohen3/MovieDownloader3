import java.util.Scanner;
import java.lang.reflect.Method;

public class EventListener implements Observable{

    //TODO: thread for event listener
    //TODO: thread for the rest of the system
    static Observer md = new MDownloader();
    public static int filesize = 1;
    public static int disk = 100;
    public static Event e = Event.turnOff;

    public void system()
    {
        //main thread will be the listener to the events
        Scanner scanner = new Scanner(System.in);
        String event = "";
        while(true)
        {
            Event e = null;
            System.out.print("Type in an event that you want: ");
            event = scanner.nextLine();
            String[] s = event.split(" ");
            if(s[0].equals("fileRequest") && s.length < 2) continue;
            if(s.length > 1)
            {
                if(!s[0].equals("fileRequest")) continue;
                event = s[0];
                MDownloader.size = Integer.parseInt(s[1]);
                System.out.println( event +" "+ MDownloader.size);
            }
            if(event.equals("exit")) break;
            for(Event e1 : Event.values()) {
                if (e1.name().equals(event)) {
                    e = e1;
                    break;
                }
            }
            if(e == null){ System.out.println("Invalid Event"); continue; }
            notify(e);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) { }
        }
    }

    public void notify(Event e)
    {
        md.update(e);
    }

    public static void main(String[] args)
    {
        //MDownloader m = new MDownloader();
        //md = new MDownloader();
        EventListener d = new EventListener();
        MDownloader mdr = (MDownloader)md;
        Thread t = new Thread( () -> mdr.Do());
        Thread listener = new Thread( () -> d.system());
        listener.start();
        t.start();
        try {
            listener.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        mdr.Exit();
//        System.out.println("Sending turnOn event....");
//        mdr.setState(Event.turnOn);
//        System.out.println("Sending turnOn event....");
//        mdr.setState(Event.turnOn);
//        System.out.println("Sending turnOff event....");
//        mdr.setState(Event.turnOff);
    }
}
