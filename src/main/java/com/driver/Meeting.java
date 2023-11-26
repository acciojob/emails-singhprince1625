package com.driver;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Meeting {
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static int maxMeetings(List<Meeting> meetings) {
        
        // Sort meetings by end time
        Collections.sort(meetings, Comparator.comparing(meeting -> meeting.endTime));

        int maxMeetings = 0;
        LocalTime lastEndTime = LocalTime.MIN;

        // Iterate through sorted meetings and attend non-overlapping ones
        for (Meeting meeting : meetings) {
            if (meeting.startTime.isAfter(lastEndTime) || meeting.startTime.equals(lastEndTime)) {
                // Attend the meeting
                lastEndTime = meeting.endTime;
                maxMeetings++;
            }
        }

        return maxMeetings;
    }

}
