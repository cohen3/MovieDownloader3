# Description
This project was based on 2 design patterns:

**_Observer_**: where the Event listener is the observable and MDownloader is the observer
also some state are observers and observables

**_State_**: each machine state is a state, a composite states also act as objects with states of their own (nested states)


# Variable Control
you may change the disk capacity at any time by using the command:
disk \<number\>

Example:
to set the disk to 150 type: disk 150

Exceptions: negative number will set the disk to the default value of 100


you may select the size of the file on the fileRequest event by using: fileRequest \<number\>

Example: to start downloading a file with the size of 5: fileRequest 5

Exceptions: negative number will cause the event to be ignored

# Events
    turnOn          - turns the machine on
    turnOff         - turns the machine off
    internetOn      - start connection on state
    internetOff     - start connection off state
    fileRequest     - set a new file to download usage: fileRequest <size>
    downloadAborted - stop a download
    downloadError   - throw an error while downloading
    errorFixed      - fix thrown error
    movieOn         - start a movie (only after 20% download)
    restartMovie    - restart a movie from the beginning
    holdMovie       - pause a movie (set hold state)
    movieOff        - stop a movie
    resume          - resume a movie when holded

# States
    Machine States:
        OnState             - composite state
        OffState            - simple state
        
    OnState:        (Composite State)
        InternetOff         - simple State
        InternetOn          - Parallel State
        
    InsternetOn:    (Parallel State)
        Region 1:   (listening to file request events)
            Listen          - simple state, waiting for files to add the queue
        Region 2:   (Download region)
            download_idle   - simple state, reading from queue
            download        - simple state, downloading the file
            fix             - simple state, when error detected, trying to fix state
        region 3:   (watch movie)
            watch_idle      - simple state, waiting for movieOn event to play the file
            watch           - simple state, playing the movie
            hold            - simple state, pause the movie due to error or hold event
        Region 4:   (user points)
            beginner        - simple state, set user to beginner state
            advanced        - simple state, set user to advanced state
            pro             - simple state, set user to pro state
    
# Variables

    d       :boolean    - indicates if download is in progress
    speed   :double     - download speed (as set from user state)
    p       :double     - download progress in percentage
    po      :int        - user current points (may go negative)
    t       :int        - movie progress
    disk    :int        - disk space, can be set by using: disk <size>
    size    :int        - indicated the file size