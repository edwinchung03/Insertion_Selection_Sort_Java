public class EventItem {
    public static String location;
    public static String eventName;
    public static int startTime;
    public static int endTime;


    public EventItem(String location, String eventName, int startTime, int endTime) {
        EventItem.location = location;
        EventItem.eventName = eventName;
        EventItem.startTime = startTime;
        EventItem.endTime = endTime;
    }
}
