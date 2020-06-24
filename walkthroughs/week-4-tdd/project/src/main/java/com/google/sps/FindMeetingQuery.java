// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public final class FindMeetingQuery {
    public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
        //special cases
        if (request.getAttendees().size() == 0) 
            return Arrays.asList(TimeRange.WHOLE_DAY);
        if (request.getDuration() > TimeRange.WHOLE_DAY.duration())
            return Arrays.asList();

        //get attendee event timeranges in order by end time
        List<TimeRange> busy = new ArrayList<>();
        for (Event e : events){
            busy.add(e.getWhen());
        }
        busy.sort(TimeRange.ORDER_BY_END);

        for (int i = 1; i < busy.size(); i++){
            //eliminate nested events
            if (busy.get(i).contains(busy.get(i-1))){
                busy.remove(i-1);
                System.out.println("NESTED!");
                i--;
            }

            //eliminate overlapping events
            else if (busy.get(i).overlaps(busy.get(i-1))){
                busy.set(i, TimeRange.fromStartEnd(busy.get(i-1).start(), 
                    busy.get(i).start() + busy.get(i).duration(), false));
                busy.remove(i-1);
                i--;
            }

        }


        //make meetings
        List<TimeRange> times = new ArrayList<TimeRange>();
        int start = TimeRange.START_OF_DAY;
        for (TimeRange b : busy){
            times.add(TimeRange.fromStartEnd(start, b.start(), false));
            start = b.start() + b.duration();
        }
        times.add(TimeRange.fromStartEnd(start, TimeRange.END_OF_DAY, true));
        return times;
    }
}