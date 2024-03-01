public class DailyScheduler {
    private static final int maxItems = 100;
    private EventItem[] dailySchedule;
    private int numItems;

    public DailyScheduler() {
        dailySchedule = new EventItem[maxItems];
        numItems = 0;
    }

    public void sortDescendingByEventName() {
        for (int i = 1; i < numItems; i++) {
            EventItem key = dailySchedule[i];
            int j = i - 1;
            while (j >= 0 && dailySchedule[j].eventName.compareTo(key.eventName) < 0) {
                dailySchedule[j + 1] = dailySchedule[j];
                j = j - 1;
            }
            dailySchedule[j + 1] = key;
        }
    }

    public void sortAscendingByStartTime()
    {for (int i = 1; i < numItems; i++) {
            EventItem key = dailySchedule[i];
            int j = i - 1;
            while (j >= 0 && dailySchedule[j].startTime > key.startTime) {
                dailySchedule[j + 1] = dailySchedule[j];
                j = j - 1;
            }
            dailySchedule[j + 1] = key;
        }
    }

    public void sortDescendingByLength() {
        // Selection sort for descending order by length (endTime - startTime)
        for (int i = 0; i < numItems - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < numItems; j++) {
                int lengthJ = dailySchedule[j].endTime - dailySchedule[j].startTime;
                int lengthMaxIdx = dailySchedule[maxIdx].endTime - dailySchedule[maxIdx].startTime;
                if (lengthJ > lengthMaxIdx) {
                    maxIdx = j;
                }
            }
            EventItem temp = dailySchedule[maxIdx];
            dailySchedule[maxIdx] = dailySchedule[i];
            dailySchedule[i] = temp;
        }
    }

    public String printDayScheduleByStartTime() {
        sortAscendingByStartTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numItems; i++) {
            EventItem item = dailySchedule[i];
            sb.append("Location: ").append(item.location)
                    .append(", Event: ").append(item.eventName)
                    .append(", Start Time: ").append(item.startTime)
                    .append(", End Time: ").append(item.endTime).append("\n");
        }
        return sb.toString();
    }

    public String printDayScheduleByLongestTime() {
        sortDescendingByLength();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numItems; i++) {
            EventItem item = dailySchedule[i];
            sb.append("Location: ").append(item.location)
                    .append(", Event: ").append(item.eventName)
                    .append(", Start Time: ").append(item.startTime)
                    .append(", End Time: ").append(item.endTime).append("\n");
        }
        return sb.toString();
    }

    public int binarySearch(String eventName) {
        int low = 0;
        int high = numItems - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compareResult = eventName.compareTo(dailySchedule[mid].eventName);

            if (compareResult < 0) {
                low = mid + 1;
            } else if (compareResult > 0) {
                high = mid - 1;
            } else {
                return mid; // eventName found
            }
        }

        return -1; // eventName not found
    }

    public boolean addEventItem(EventItem item) {
        if (numItems < maxItems) {
            if (binarySearch(item.eventName) == -1){
                dailySchedule[numItems] = item;
                numItems++;
                sortDescendingByEventName();
            }
            return true;
        }
        return false;
    }
}
