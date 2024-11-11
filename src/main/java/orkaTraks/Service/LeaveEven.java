package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.LeaveEvent;
import orkaTraks.Repo.LeaveEventRepo;

import java.util.List;

@Service
public class LeaveEven {
    @Autowired
    private LeaveEventRepo leaveEventRepo;

    public List<LeaveEvent> saveLeaveDetail(List<LeaveEvent> leaveEvent) {
        return leaveEventRepo.saveAll(leaveEvent);
    }
}
