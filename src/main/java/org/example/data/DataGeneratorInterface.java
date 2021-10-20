package org.example.data;

import org.example.model.ProjectRequestPayload;
import org.example.model.TaskRequestPayload;

public interface DataGeneratorInterface {
    ProjectRequestPayload getProjectData ();
    TaskRequestPayload getTaskData();
}
